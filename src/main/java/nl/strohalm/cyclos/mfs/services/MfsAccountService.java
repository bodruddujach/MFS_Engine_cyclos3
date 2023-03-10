package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.dao.members.ElementDAO;
import nl.strohalm.cyclos.entities.access.Channel;
import nl.strohalm.cyclos.entities.access.MemberUser;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.SystemAccount;
import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.entities.members.RegisteredMember;
import nl.strohalm.cyclos.mfs.entities.MFSLedgerAccount;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.middleware.CyclosMiddleware;
import nl.strohalm.cyclos.mfs.models.accounts.AcRegRequest;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.accounts.ChangePinRequest;
import nl.strohalm.cyclos.mfs.models.accounts.CheckPinRequest;
import nl.strohalm.cyclos.mfs.models.accounts.LoginResponse;
import nl.strohalm.cyclos.mfs.models.accounts.RegResponse;
import nl.strohalm.cyclos.mfs.models.accounts.StatementParams;
import nl.strohalm.cyclos.mfs.models.accounts.UpdateAccountRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletInfoResponse;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementDetail;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementRequest;
import nl.strohalm.cyclos.mfs.models.accounts.WalletStatementResp;
import nl.strohalm.cyclos.mfs.models.enums.TransactionType;
import nl.strohalm.cyclos.mfs.models.transactions.Response;
import nl.strohalm.cyclos.mfs.utils.MfsConstant;
import nl.strohalm.cyclos.services.access.AccessServiceImpl;
import nl.strohalm.cyclos.services.access.exceptions.BlockedCredentialsException;
import nl.strohalm.cyclos.services.access.exceptions.CredentialsAlreadyUsedException;
import nl.strohalm.cyclos.services.access.exceptions.InvalidCredentialsException;
import nl.strohalm.cyclos.services.accounts.AccountDateDTO;
import nl.strohalm.cyclos.services.accounts.AccountServiceLocal;
import nl.strohalm.cyclos.services.accounts.MemberAccountHandler;
import nl.strohalm.cyclos.services.elements.ElementServiceLocal;
import nl.strohalm.cyclos.services.transactions.PaymentServiceLocal;
import nl.strohalm.cyclos.utils.TransactionHelper;
import nl.strohalm.cyclos.utils.Transactional;
import nl.strohalm.cyclos.utils.validation.ValidationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;

import static nl.strohalm.cyclos.mfs.exceptions.ErrorConstants.*;


@Service
public class MfsAccountService {
  public static final Logger logger = LogManager.getLogger(MfsAccountService.class);

  @Autowired
  MemberAccountHandler memberAccountHandler;
  @Autowired
  AccountServiceLocal accountServiceLocal;
  @Autowired
  PaymentServiceLocal paymentServiceLocal;
  @Autowired
  ElementDAO elementDAO;

  @Autowired
  TransactionHelper transactionHelper;

  @Autowired
  AccessServiceImpl accessService;

  @Autowired
  CyclosMiddleware cyclosMiddleware;
  @Autowired
  ElementServiceLocal elementServiceLocal;
  @Autowired
  MfsTxnTypeService mfsTxnTypeService;

  @Autowired
  LedgerService ledgerService;

  public RegResponse processRegistration(final AcRegRequest regRequest) {

    RegResponse regResponse = new RegResponse();
    final Member member = cyclosMiddleware.getMemberFromRequest(regRequest);
    // todo handle merchant/agent/distributor registration
    RegisteredMember registeredMember = transactionHelper.maybeRunInNewTransaction(new Transactional<RegisteredMember>() {
      @Override
      public RegisteredMember afterCommit(final RegisteredMember registeredMember) {
        return registeredMember;
      }

      @Override
      public RegisteredMember doInTransaction(final TransactionStatus status) {
        return (RegisteredMember) elementServiceLocal.register(member, false);
      }
    }, true);
    regResponse.setStatus("SUCCESS");
    regResponse.setWalletNo(registeredMember.getUsername());
    return regResponse;
  }


  public LoginResponse loginUser(CheckPinRequest checkPinRequest) {
    LoginResponse response = new LoginResponse();
    try {
      MemberUser user = cyclosMiddleware.getMemberUser(checkPinRequest.getWalletNo());
      if (user == null) {
        throw new MFSCommonException(INVALID_PIN, ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.UNAUTHORIZED);
      }
      Channel channel = new Channel();
      channel.setInternalName("rest");
      channel.setCredentials(Channel.Credentials.PIN);
      accessService.checkCredentials(channel, user, checkPinRequest.getPin(), null, null);
      response.setWalletNo(user.getUsername());

    } catch (final BlockedCredentialsException e) {
      logger.error("Login failed for user: " + checkPinRequest.getWalletNo());
      throw new MFSCommonException(ErrorConstants.BLOCKED_PIN, ERROR_MAP.get(ErrorConstants.BLOCKED_PIN), HttpStatus.UNAUTHORIZED);
    } catch (final InvalidCredentialsException e) {
      logger.error("Login failed for user: " + checkPinRequest.getWalletNo());
      throw new MFSCommonException(ErrorConstants.INVALID_PIN, ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.UNAUTHORIZED);
    }
    return response;
  }

  public BalanceResponse getCurrentBalance(final String walletNo) {
    BalanceResponse balanceResult = new BalanceResponse();
    final Member member = cyclosMiddleware.getMember(walletNo);
    if (member == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    Account account = null;
    for (final Account ac : accountServiceLocal.getAccounts(member)) {
      account = ac;
    }
    if (account == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    AccountDateDTO dtoParams = new AccountDateDTO(account);
    balanceResult.setBalance(accountServiceLocal.getBalance(dtoParams));
    balanceResult.setStatus(MfsConstant.STATUS_SUCCESS);
    balanceResult.setAvailableBalance(balanceResult.getBalance());
    return balanceResult;
  }

  public WalletInfoResponse getWalletInfo(final String walletNo) {
    final Member member = cyclosMiddleware.getMember(walletNo);
    if (member == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    Account account = null;
    for (final Account ac : accountServiceLocal.getAccounts(member)) {
      account = ac;
    }
    if (account == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    return cyclosMiddleware.getWalletInfoResponse(member, account);
  }


  public Response activateWallet(String walletNo) throws Exception {
    Response response = updateWalletStatus(walletNo, MemberAccount.Status.ACTIVE);
    // todo agent commission
    return response;
  }

  public Response updateWalletStatus(String walletNo, MemberAccount.Status status) throws Exception {
    Response response = new Response();
    response.setStatus(MfsConstant.STATUS_FAILED);

    final Member member = cyclosMiddleware.getMember(walletNo);
    if (member == null) {
      throw new MFSCommonException(ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    Account account = null;
    for (final Account ac : accountServiceLocal.getAccounts(member)) {
      account = ac;
    }
    MemberAccount memberAccount = null;
    if (account instanceof MemberAccount) {
      memberAccount = (MemberAccount) account;
    }
    assert memberAccount != null;
    if (memberAccount.getStatus() == status) {
      throw new MFSCommonException(INVALID_AC_STATUS, ERROR_MAP.get(ErrorConstants.INVALID_AC_STATUS) + ":" + status, HttpStatus.BAD_REQUEST);
    }
    if (status == MemberAccount.Status.ACTIVE && memberAccount.getStatus() != MemberAccount.Status.PENDING) {
      throw new MFSCommonException(INVALID_AC_STATUS, ERROR_MAP.get(ErrorConstants.INVALID_AC_STATUS) + ":" + status, HttpStatus.BAD_REQUEST);
    }
    try {
      memberAccountHandler.updateAccountStatus(memberAccount, status);
      if (MemberAccount.Status.ACTIVE.equals(status)) {
        member.setActivationDate(Calendar.getInstance());
        elementDAO.update(member);
      } else {
        member.setActivationDate(null);
        elementDAO.update(member);
      }
      response.setStatus(MfsConstant.STATUS_SUCCESS);
      response.setMessage("Wallet status updated successfully");
    } catch (EntityNotFoundException e) {
      logger.error("Entity not found with wallet: " + walletNo, e);
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      logger.error("Status update failed for wallet: " + walletNo, e);
      throw new MFSCommonException(ErrorConstants.INTERNAL_ERROR, ERROR_MAP.get(ErrorConstants.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  public Response updateWalletInformation(UpdateAccountRequest updateAccountRequest) throws Exception {
    Response response = new Response();
    Member member = cyclosMiddleware.getMember(updateAccountRequest.getWalletNo());
    if (member == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    member.setName(updateAccountRequest.getName());
    elementDAO.update(member);
    response.setStatus(MfsConstant.STATUS_SUCCESS);
    response.setMessage("Wallet updated successfully");
    return response;
  }


  public WalletStatementResp walletStatement(WalletStatementRequest statementRequest) {
    if (statementRequest.getPageSize() == null || statementRequest.getPageSize() <= 0) {
      statementRequest.setPageSize(100);
    }
    Account account = null;
    logger.info("Statement Request: " + statementRequest);
    if (org.apache.commons.lang.StringUtils.isNotEmpty(statementRequest.getWalletNo())) {
      account = cyclosMiddleware.getAccount(statementRequest.getWalletNo());
    }
    if (org.apache.commons.lang.StringUtils.isNotEmpty(statementRequest.getLedgerCode())) {
      MFSLedgerAccount ledgerAccount = ledgerService.getLedgerByCode(statementRequest.getLedgerCode());
      if (MFSLedgerAccount.LedgerType.MEMBER.name().equalsIgnoreCase(ledgerAccount.getType())) {
        throw new MFSCommonException(STATEMENT_NOT_SUPPORTED, ERROR_MAP.get(STATEMENT_NOT_SUPPORTED), HttpStatus.BAD_REQUEST);
      }
      account = new SystemAccount();
      account.setId(ledgerAccount.getAccountId());
    }

    StatementParams query = new StatementParams();
    query.setAccountNo(account.getId());
    query.setBeginDate(statementRequest.getBeginDate());
    query.setEndDate(statementRequest.getEndDate());
    query.setTxnId(statementRequest.getTxnId());
    query.setCurrentPage(statementRequest.getCurrentPage());
    query.setPageSize(statementRequest.getPageSize());
    query.setReverseOrder(statementRequest.getReverseOrder());
    if (statementRequest.getTxnTypes() != null && statementRequest.getTxnTypes().size() > 0) {
      query.setTxnTypes(new ArrayList<String>());
      for (TransactionType type : statementRequest.getTxnTypes()) {
        query.getTxnTypes().add(type.name());
      }
    }
    WalletStatementResp response = paymentServiceLocal.searchStatement(query);
    for (WalletStatementDetail statementDetail : response.getWalletStatementDetailList()) {
      MfsTxnType mfsTxnType = mfsTxnTypeService.findByCoreId(statementDetail.getTypeId().longValue());
      TransactionType type = null;
      if (mfsTxnType != null) {
        type = TransactionType.valueOf(mfsTxnType.getName());
      }

      statementDetail.setTxnType(type != null ? type.name() : "");

      if (!StringUtils.isEmpty(statementRequest.getWalletNo())) {
        if (statementRequest.getWalletNo().equalsIgnoreCase(statementDetail.getFromWallet())) {
          statementDetail.setActionType(WalletStatementDetail.TxnActionType.DEBIT);
        } else {
          statementDetail.setActionType(WalletStatementDetail.TxnActionType.CREDIT);
        }
      }
      if (!StringUtils.isEmpty(statementRequest.getLedgerCode())) {
        if (account.getId().intValue() == statementDetail.getFromAcId()) {
          statementDetail.setActionType(WalletStatementDetail.TxnActionType.DEBIT);
        } else {
          statementDetail.setActionType(WalletStatementDetail.TxnActionType.CREDIT);
        }
      }

    }
    response.setStatus(MfsConstant.STATUS_SUCCESS);
    return response;
  }

  public Response changePin(ChangePinRequest changePinRequest, boolean isReset) {
    if (!isReset) isValidPin(changePinRequest.getOldPin());
    isValidPin(changePinRequest.getNewPin());
    try {
      MemberUser user = cyclosMiddleware.getMemberUser(changePinRequest.getWalletNo());
      if (user == null) {
        throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
      }
      Channel channel = new Channel();
      channel.setInternalName("rest");
      if (!isReset) accessService.checkCredentials(channel, user, changePinRequest.getOldPin(), null, null);
      try {
        accessService.changePin(user, changePinRequest.getNewPin());
        Response response = new Response(MfsConstant.STATUS_SUCCESS, "Change Pin Successful");
        return response;
      } catch (final ValidationException e) {
        throw new MFSCommonException(ErrorConstants.INVALID_PIN, ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.BAD_REQUEST);
      } catch (final CredentialsAlreadyUsedException e) {
        throw new MFSCommonException(ErrorConstants.PIN_ALREADY_USED, ERROR_MAP.get(ErrorConstants.PIN_ALREADY_USED), HttpStatus.BAD_REQUEST);
      }
    } catch (final BlockedCredentialsException e) {
      throw new MFSCommonException(ErrorConstants.BLOCKED_PIN, ERROR_MAP.get(ErrorConstants.BLOCKED_PIN), HttpStatus.BAD_REQUEST);
    } catch (final InvalidCredentialsException e) {
      throw new MFSCommonException(ErrorConstants.INVALID_PIN, ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.BAD_REQUEST);
    }

  }

  public Response resetPin(ChangePinRequest changePinRequest) {
    return changePin(changePinRequest, true);
  }

  private void isValidPin(String pin) {
    if (StringUtils.isEmpty(pin) || pin.length() < 4) {
      throw new MFSCommonException(ErrorConstants.INVALID_PIN, ERROR_MAP.get(ErrorConstants.INVALID_PIN), HttpStatus.BAD_REQUEST);
    }
  }
}
