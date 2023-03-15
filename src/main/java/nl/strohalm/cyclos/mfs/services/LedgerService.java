package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.AccountOwner;
import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.SystemAccount;
import nl.strohalm.cyclos.entities.accounts.transactions.PaymentFilter;
import nl.strohalm.cyclos.entities.accounts.transactions.PaymentFilterQuery;
import nl.strohalm.cyclos.mfs.dao.MFSLedgerAccountDAO;
import nl.strohalm.cyclos.mfs.entities.MFSLedgerAccount;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.models.accounts.BalanceResponse;
import nl.strohalm.cyclos.mfs.models.accounts.GetMfsTransactionsDTO;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnSummaryRequest;
import nl.strohalm.cyclos.mfs.models.ledgers.LedgerTxnSummaryResponse;
import nl.strohalm.cyclos.mfs.utils.MfsConstant;
import nl.strohalm.cyclos.services.accounts.AccountDTO;
import nl.strohalm.cyclos.services.accounts.AccountDateDTO;
import nl.strohalm.cyclos.services.accounts.AccountServiceLocal;
import nl.strohalm.cyclos.services.transactions.TransactionSummaryVO;
import nl.strohalm.cyclos.services.transfertypes.PaymentFilterServiceLocal;
import nl.strohalm.cyclos.utils.Period;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
public class LedgerService {
  @Autowired
  private MFSLedgerAccountDAO mfsLedgerAccountDAO;
  @Autowired
  AccountServiceLocal accountServiceLocal;
  @Autowired
  PaymentFilterServiceLocal paymentFilterServiceLocal;

  public MFSLedgerAccount loadMFSLedgerAccount(Long mFSLedgerAccountId) {
    return mfsLedgerAccountDAO.load(mFSLedgerAccountId);
  }

  @Transactional
  public MFSLedgerAccount saveMFSLedgerAccount(MFSLedgerAccount mFSLedgerAccount) {
    return mfsLedgerAccountDAO.insert(mFSLedgerAccount);
  }

  @Transactional
  public MFSLedgerAccount updateMFSLedgerAccount(MFSLedgerAccount mFSLedgerAccount) {
    return mfsLedgerAccountDAO.update(mFSLedgerAccount);
  }

  @Transactional
  public int deleteMFSLedgerAccount(Long mFSLedgerAccountId) {
    return mfsLedgerAccountDAO.delete(mFSLedgerAccountId);
  }

  public MFSLedgerAccount getLedgerByCode(String ledgerCode) {
    MFSLedgerAccount mfsLedgerAccount = mfsLedgerAccountDAO.findByCode(ledgerCode);
    if (mfsLedgerAccount == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    return mfsLedgerAccount;
  }

  public List<MFSLedgerAccount> getAllLedgerAc() {
    return mfsLedgerAccountDAO.getAllLedgerAc();
  }

  public List<MFSLedgerAccount> searchAccount(String ledgerCode) {
    return mfsLedgerAccountDAO.searchAccount(ledgerCode);
  }

  public BalanceResponse ledgerBalance(String ledgerCode, Calendar date) {
    MFSLedgerAccount mfsLedgerAccount = mfsLedgerAccountDAO.findByCode(ledgerCode);
    if (mfsLedgerAccount == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    BalanceResponse balanceResponse = new BalanceResponse();
    if (MFSLedgerAccount.LedgerType.SYSTEM.name().equalsIgnoreCase(mfsLedgerAccount.getType())) {
      Account account = new SystemAccount();
      account.setId(mfsLedgerAccount.getAccountId());
      AccountDateDTO dtoParams = new AccountDateDTO(account);
      if (dtoParams != null) {
        dtoParams.setDate(date);
      }
      balanceResponse.setBalance(accountServiceLocal.getBalance(dtoParams));
      balanceResponse.setStatus(MfsConstant.STATUS_SUCCESS);
      balanceResponse.setAvailableBalance(balanceResponse.getBalance());
    } else {
      // get all balance of that type

    }

    return balanceResponse;
  }

  public LedgerTxnSummaryResponse getLedgerTransactionSummary(LedgerTxnSummaryRequest request) {
    MFSLedgerAccount mfsLedgerAccount = mfsLedgerAccountDAO.findByCode(request.getLedgerCode());
    if (mfsLedgerAccount == null) {
      throw new MFSCommonException(ErrorConstants.ACCOUNT_NOT_FOUND, ErrorConstants.ERROR_MAP.get(ErrorConstants.ACCOUNT_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    LedgerTxnSummaryResponse response = new LedgerTxnSummaryResponse();
    TransactionSummaryVO result =  new TransactionSummaryVO();
    List<PaymentFilter> paymentFilters = null;
    if (MFSLedgerAccount.LedgerType.SYSTEM.name().equalsIgnoreCase(mfsLedgerAccount.getType())) {
      Account account = new SystemAccount();
      account.setId(mfsLedgerAccount.getAccountId());
      AccountDTO acParams = new AccountDTO(account);
      Account sysAccount = accountServiceLocal.getAccount(acParams);
      GetMfsTransactionsDTO dtoParam = new GetMfsTransactionsDTO();
      if (StringUtils.isNotBlank(request.getPaymentFilter())) {
        PaymentFilterQuery pfQuery = new PaymentFilterQuery();
        pfQuery.setAccount(sysAccount);
        pfQuery.setName(request.getPaymentFilter());
        paymentFilters = paymentFilterServiceLocal.search(pfQuery);
      }
      dtoParam.setPaymentFilters(paymentFilters);
      dtoParam.setAccount(sysAccount);
      dtoParam.setPeriod(Period.day(request.getDate()));
      dtoParam.setIncludeChargebacks(request.isIncludeChargebacks());
      dtoParam.setRootOnly(request.isRootOnlyTransactions());
      result = request.isCredits() ? accountServiceLocal.getCreditsMFS(dtoParam) : accountServiceLocal.getDebitsMFS(dtoParam);
    } else {
      // get all balance of that type
    }
    response.setTxnCount(result.getCount());
    response.setAmount(result.getAmount());
    return response;
  }
}
