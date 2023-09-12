package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.entities.accounts.AccountTypeQuery;
import nl.strohalm.cyclos.entities.accounts.Currency;
import nl.strohalm.cyclos.entities.accounts.SystemAccount;
import nl.strohalm.cyclos.entities.accounts.SystemAccountType;
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
import nl.strohalm.cyclos.services.accounts.AccountTypeServiceLocal;
import nl.strohalm.cyclos.services.accounts.SystemAccountTypeQuery;
import nl.strohalm.cyclos.services.transactions.TransactionSummaryVO;
import nl.strohalm.cyclos.services.transfertypes.PaymentFilterServiceLocal;
import nl.strohalm.cyclos.utils.Period;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
  @Autowired
  AccountTypeServiceLocal accountTypeServiceLocal;
  

  public MFSLedgerAccount loadMFSLedgerAccount(Long mFSLedgerAccountId) {
    return mfsLedgerAccountDAO.load(mFSLedgerAccountId);
  }

  @Transactional
  public MFSLedgerAccount saveMFSLedgerAccount(MFSLedgerAccount mfsLedgerAccount) {
    if ("detail".equalsIgnoreCase(mfsLedgerAccount.getNature())) {
      SystemAccountTypeQuery query = new SystemAccountTypeQuery();
      query.setName(mfsLedgerAccount.getName());
      query.setDescription(mfsLedgerAccount.getDescription());
      List<SystemAccountType> systemAcoountTypes = (List<SystemAccountType>) accountTypeServiceLocal.search(query);
      if (CollectionUtils.isEmpty(systemAcoountTypes)) {
        AccountType systemAccountType = new SystemAccountType();
        Currency currency = new Currency();
        currency.setId(1L);
        systemAccountType.setCurrency(currency);
        systemAccountType.setName(mfsLedgerAccount.getName());
//        systemAccountType.setDescription(mfsLedgerAccount.getDescription());
        SystemAccountType savedSystemAccountType = (SystemAccountType) accountTypeServiceLocal.save(systemAccountType);
        SystemAccount systemAccount = savedSystemAccountType.getAccount();
        mfsLedgerAccount.setAccountId(systemAccount.getId());
      } else {
        SystemAccount existingSystemAccount = systemAcoountTypes.get(0).getAccount();
        mfsLedgerAccount.setAccountId(existingSystemAccount.getId());
      }
    }
    mfsLedgerAccount.setType("SYSTEM");
    return mfsLedgerAccountDAO.insert(mfsLedgerAccount);
  }

  @Transactional
  public MFSLedgerAccount updateMFSLedgerAccount(MFSLedgerAccount mfsLedgerAccount) {
    MFSLedgerAccount ledgerToBeUpdated = mfsLedgerAccountDAO.load(mfsLedgerAccount.getId());
    if ("detail".equalsIgnoreCase(ledgerToBeUpdated.getNature())) {
      SystemAccount account = accountServiceLocal.load(ledgerToBeUpdated.getAccountId(), Account.Relationships.TYPE);
      AccountType systemAccountTypeToBeUpdated = account.getType();
      systemAccountTypeToBeUpdated.setName(mfsLedgerAccount.getName());
      systemAccountTypeToBeUpdated.setDescription(mfsLedgerAccount.getDescription());
      accountTypeServiceLocal.save(systemAccountTypeToBeUpdated);
    }
    ledgerToBeUpdated.setCode(mfsLedgerAccount.getCode());
    ledgerToBeUpdated.setActive(mfsLedgerAccount.isActive());
    ledgerToBeUpdated.setName(mfsLedgerAccount.getName());
    ledgerToBeUpdated.setDescription(mfsLedgerAccount.getDescription());
    return mfsLedgerAccountDAO.update(ledgerToBeUpdated);
  }

  @Transactional
  public int deleteMFSLedgerAccount(Long mfsLedgerAccountId) {
    MFSLedgerAccount ledgerToBeUpdated = mfsLedgerAccountDAO.load(mfsLedgerAccountId);
    if ("detail".equalsIgnoreCase(ledgerToBeUpdated.getNature())) {
      SystemAccount account = accountServiceLocal.load(ledgerToBeUpdated.getAccountId(), Account.Relationships.TYPE);
      AccountType systemAccountTypeToBeUpdated = account.getType();
      accountTypeServiceLocal.remove(systemAccountTypeToBeUpdated.getId());
    }
    return mfsLedgerAccountDAO.delete(mfsLedgerAccountId);
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
    if (mfsLedgerAccount.getAccountId() != null && MFSLedgerAccount.LedgerType.SYSTEM.name().equalsIgnoreCase(mfsLedgerAccount.getType())) {
      Account account = new SystemAccount();
      account.setId(mfsLedgerAccount.getAccountId());
      AccountDateDTO dtoParams = new AccountDateDTO(account);
      if (date != null) {
        dtoParams.setDate(date);
      }
      balanceResponse.setBalance(accountServiceLocal.getBalance(dtoParams));
    } else {
      balanceResponse.setBalance(calculateLegerBalance(mfsLedgerAccount, date));
    }
    balanceResponse.setStatus(MfsConstant.STATUS_SUCCESS);
    balanceResponse.setAvailableBalance(balanceResponse.getBalance());
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

  private BigDecimal calculateLegerBalance(MFSLedgerAccount rootLeger, Calendar date) {
      if (rootLeger == null) {
          return BigDecimal.ZERO;
      }
      if (rootLeger.isActive() && rootLeger.isLastLevel() && rootLeger.getAccountId()!= null && "detail".equalsIgnoreCase(rootLeger.getNature())) {
          BalanceResponse balanceResponse = new BalanceResponse();
          Account account = new SystemAccount();
          account.setId(rootLeger.getAccountId());
          AccountDateDTO dtoParams = new AccountDateDTO(account);
          if (date != null) {
              dtoParams.setDate(date);
          }
          balanceResponse.setBalance(accountServiceLocal.getBalance(dtoParams));
          balanceResponse.setStatus(MfsConstant.STATUS_SUCCESS);
          balanceResponse.setAvailableBalance(balanceResponse.getBalance());
          return balanceResponse.getBalance();
      }
      BigDecimal rootLedgerBalance = BigDecimal.ZERO;
      for (MFSLedgerAccount childLeger: rootLeger.getChildLedgers()) {
          if (rootLeger.isActive()) {
              rootLedgerBalance = rootLedgerBalance.add(calculateLegerBalance(childLeger, date));
          }
      }
      return rootLedgerBalance;
  }

}
