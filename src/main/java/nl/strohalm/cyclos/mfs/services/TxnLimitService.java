package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.dao.accounts.transactions.TransferDAO;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;
import nl.strohalm.cyclos.entities.accounts.MemberAccountType;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.entities.groups.Group;
import nl.strohalm.cyclos.entities.groups.MemberGroup;
import nl.strohalm.cyclos.entities.members.Element;
import nl.strohalm.cyclos.entities.members.Member;
import nl.strohalm.cyclos.mfs.dao.MfsAccountGenericLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.MfsAccountTxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.MfsGenericLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.MfsTxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.TxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.entities.MfsAccountGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTypeGroup;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig.LimitSubject;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.models.transactions.AccountGenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.AccountLimitData;
import nl.strohalm.cyclos.mfs.models.transactions.AccountTxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.GenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateAccountGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateAccountTxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.WalletLimitData;
import nl.strohalm.cyclos.mfs.models.transactions.WalletLimitResponse;
import nl.strohalm.cyclos.services.fetch.FetchServiceLocal;
import nl.strohalm.cyclos.services.transactions.TransactionSummaryVO;
import nl.strohalm.cyclos.utils.Period;
import nl.strohalm.cyclos.utils.RelationshipHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class TxnLimitService {

    @Autowired
    private FetchServiceLocal fetchService;

    @Autowired
    private TransferDAO transferDao;

    @Autowired
    private TxnLimitConfigDAO txnLimitConfigDAO;

    @Autowired
    private MfsGenericLimitConfigDAO mfsGenericLimitConfigDAO;

    @Autowired
    private MfsTxnLimitConfigDAO mfsTxnLimitConfigDAO;

    @Autowired
    private MfsAccountGenericLimitConfigDAO mfsAccountGenericLimitConfigDAO;

    @Autowired
    private MfsAccountTxnLimitConfigDAO mfsAccountTxnLimitConfigDAO;

//    @Transactional
//    public TxnLimitConfig loadTxnLimitConfig(Long txnLimitConfigId) {
//        return txnLimitConfigDAO.load(txnLimitConfigId);
//    }

    @Transactional
    public TxnLimitResponse loadMfsTxnLimitConfig(Long txnLimitConfigId) {
        return convertToTxnLimitResponse(mfsTxnLimitConfigDAO.load(txnLimitConfigId, MfsTxnLimitConfig.Relationships.TransferType));
    }

    @Transactional
    public List<MfsTxnLimitConfig> loadAllMfsTxnLimitConfigs() {
        return mfsTxnLimitConfigDAO.getAllMfsTxnLimitConfigs();
    }

    @Transactional
    public List<MfsTxnLimitConfig> loadAllMfsTxnLimitConfigsByStatus(boolean enabled) {
        return mfsTxnLimitConfigDAO.getMfsTxnLimitConfigsByStatus(enabled);
    }

//    @Transactional
//    public TxnLimitConfig saveTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
//        return txnLimitConfigDAO.insert(txnLimitConfig);
//    }

    @Transactional
    public TxnLimitResponse saveMfsTxnLimitConfig(MfsTxnLimitConfig txnLimitConfig) {
        txnLimitConfig.setCreatedAt(Calendar.getInstance());
        List<MfsTxnLimitConfig> existingConfigs = mfsTxnLimitConfigDAO.loadMfsTxnLimitConfigByTransferTypeAndGroupAndApplyOn(txnLimitConfig.getTransferType(), txnLimitConfig.getGroup(), txnLimitConfig.getApplyOn());
        if (!CollectionUtils.isEmpty(existingConfigs)) {
          throw new MFSCommonException(ErrorConstants.TXN_LIMIT_CONFIGURATION_EXISTS, String.format("Transaction Limit Configuration Already Exists For TransferType: %s, ApplyOn: %s", txnLimitConfig.getTransferType().getName(), txnLimitConfig.getApplyOn()), HttpStatus.BAD_REQUEST);
        }
        MfsTxnLimitConfig persisitedConfig = mfsTxnLimitConfigDAO.insert(txnLimitConfig);
        return convertToTxnLimitResponse(persisitedConfig);
    }

//    @Transactional
//    public TxnLimitConfig updateTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
//        System.out.println(txnLimitConfig.toString());
//        return txnLimitConfigDAO.update(txnLimitConfig);
//    }

    @Transactional
    public TxnLimitResponse updateMfsTxnLimitConfig(UpdateTxnLimitRequest txnLimitConfigReq) {
        MfsTxnLimitConfig dbTxnLimitConfig = mfsTxnLimitConfigDAO.load(txnLimitConfigReq.getId(), MfsTxnLimitConfig.Relationships.TransferType);
        dbTxnLimitConfig.setLastModifiedDate(Calendar.getInstance());
        convertToMfsTxnLimitConfig(txnLimitConfigReq, dbTxnLimitConfig);
        MfsTxnLimitConfig persistedConfig = mfsTxnLimitConfigDAO.update(dbTxnLimitConfig);
        return convertToTxnLimitResponse(persistedConfig);
    }

//    @Transactional
//    public int deleteTxnLimitConfig(Long txnLimitConfigId) {
//        return mfsTxnLimitConfigDAO.delete(txnLimitConfigId);
//    }

    @Transactional
    public int deleteMfsTxnLimitConfig(Long txnLimitConfigId) {
        return mfsTxnLimitConfigDAO.delete(txnLimitConfigId);
    }

    @Transactional
    public MfsGenericLimitConfig loadGenericLimitConfigByName(String name) {
        MfsGenericLimitConfig gLimitConfig = mfsGenericLimitConfigDAO.loadByName(name);
        if (gLimitConfig == null) {
            throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Not Found"), HttpStatus.NOT_FOUND);
        }
        return gLimitConfig;
    }

    @Transactional
    public MfsGenericLimitConfig loadGenericLimitConfig(Long genericLimitConfigId) {
        return mfsGenericLimitConfigDAO.load(genericLimitConfigId);
    }

    @Transactional
    public List<MfsGenericLimitConfig> loadAllMfsGenericLimitConfigs() {
        return mfsGenericLimitConfigDAO.getAllMfsGenericLimitConfigs();
    }

    @Transactional
    public List<MfsGenericLimitConfig> loadAllMfsGenericLimitConfigsByStatus(boolean enabled) {
        return mfsGenericLimitConfigDAO.getMfsGLienericmitConfigsByStatus(enabled);
    }

    @Transactional
    public MfsGenericLimitConfig loadGenericLimitConfigByNameAndEnabled(String name, boolean enabled) {
        MfsGenericLimitConfig gLimitConfig = mfsGenericLimitConfigDAO.loadByNameAndEnabled(name, enabled);
        if (gLimitConfig == null) {
            throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Name or Status is Invalid"), HttpStatus.NOT_FOUND);
        }
        return gLimitConfig;
    }

    @Transactional
    public GenericLimitResponse saveMfsGenericLimitConfig(MfsGenericLimitConfig genericLimitConfig) {
    	genericLimitConfig.setCreatedAt(Calendar.getInstance());
        MfsGenericLimitConfig existingConfig = mfsGenericLimitConfigDAO.loadByName(genericLimitConfig.getName());
        if (existingConfig != null) {
          throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Configuration Already Exists For Name: %s", genericLimitConfig.getName()), HttpStatus.BAD_REQUEST);
        }
        MfsGenericLimitConfig persisitedConfig = mfsGenericLimitConfigDAO.insert(genericLimitConfig);
        return convertToGenericLimitResponse(persisitedConfig);
    }

    @Transactional
    public GenericLimitResponse updateMfsGenericLimitConfig(UpdateGenericLimitRequest genericLimitConfigReq) {
        MfsGenericLimitConfig dbgenericLimitConfig = mfsGenericLimitConfigDAO.load(genericLimitConfigReq.getId());
        dbgenericLimitConfig = fetchService.fetch(dbgenericLimitConfig, MfsGenericLimitConfig.Relationships.MFS_TRANSACTION_LIMIT_CONFIGS);
        dbgenericLimitConfig.setLastModifiedDate(Calendar.getInstance());
        dbgenericLimitConfig.setName(genericLimitConfigReq.getName());
        dbgenericLimitConfig.setDescription(genericLimitConfigReq.getDescription());
        dbgenericLimitConfig.setMaxAmountPerDay(genericLimitConfigReq.getMaxAmountPerDay());
        dbgenericLimitConfig.setMaxNumberOfTxnPerDay(genericLimitConfigReq.getMaxNumberOfTxnPerDay());
        dbgenericLimitConfig.setMaxAmountPerMonth(genericLimitConfigReq.getMaxAmountPerMonth());
        dbgenericLimitConfig.setMaxNumberOfTxnPerMonth(genericLimitConfigReq.getMaxNumberOfTxnPerMonth());
        dbgenericLimitConfig.setEnable(genericLimitConfigReq.isEnable());
        MfsGenericLimitConfig persistedConfig = mfsGenericLimitConfigDAO.update(dbgenericLimitConfig);
//        if (!persistedConfig.isEnable()) {
//            persistedConfig = fetchService.fetch(persistedConfig, MfsGenericLimitConfig.Relationships.MFS_TRANSACTION_LIMIT_CONFIGS);
//            Collection<? extends MfsTxnLimitConfig> asosiatedLimits = persistedConfig.getMfsTransactionLimitConfigs();
//            if (!CollectionUtils.isEmpty(asosiatedLimits)) {
//              for (MfsTxnLimitConfig limitConfig: asosiatedLimits) {
//                limitConfig.setLastModifiedDate(Calendar.getInstance());
//                limitConfig.setGenericLimit(null);
//                mfsTxnLimitConfigDAO.update(limitConfig);
//              }
//            }
//        }
        return convertToGenericLimitResponse(persistedConfig);
    }

    @Transactional
    public int deleteMfsAccountGenericLimitConfig(Long accountGenericLimitConfigId) {
        return mfsAccountGenericLimitConfigDAO.delete(accountGenericLimitConfigId);
    }

    //Acccount Limit Related Data
    @Transactional
    public AccountTxnLimitResponse loadMfsAccountTxnLimitConfig(Long accountTxnLimitConfigId) {
        return convertToAccountTxnLimitResponse(mfsAccountTxnLimitConfigDAO.load(accountTxnLimitConfigId, MfsAccountTxnLimitConfig.Relationships.TransferType));
    }

    @Transactional
    public List<MfsAccountTxnLimitConfig> loadAllMfsAccountTxnLimitConfigsByAccount(Account account) {
        return mfsAccountTxnLimitConfigDAO.getAllMfsAccountTxnLimitConfigsByAccount(account);
    }

    @Transactional
    public List<MfsAccountTxnLimitConfig> loadAllMfsAccountTxnLimitConfigsByStatusAndAccount(boolean enabled, Account account) {
        return mfsAccountTxnLimitConfigDAO.getMfsAccountTxnLimitConfigsByStatusAndAccount(enabled, account);
    }

    @Transactional
    public AccountTxnLimitResponse saveMfsAccountTxnLimitConfig(MfsAccountTxnLimitConfig accountTxnLimitConfig) {
    	accountTxnLimitConfig.setCreatedAt(Calendar.getInstance());
        List<MfsAccountTxnLimitConfig> existingConfigs = mfsAccountTxnLimitConfigDAO.loadMfsAccountTxnLimitConfigByTransferTypeAndAccountAndApplyOn(accountTxnLimitConfig.getTransferType(), accountTxnLimitConfig.getAccount(), accountTxnLimitConfig.getApplyOn());
        if (!CollectionUtils.isEmpty(existingConfigs)) {
          throw new MFSCommonException(ErrorConstants.TXN_LIMIT_CONFIGURATION_EXISTS, String.format("Transaction Limit Configuration Already Exists For TransferType: %s, ApplyOn: %s", accountTxnLimitConfig.getTransferType().getName(), accountTxnLimitConfig.getApplyOn()), HttpStatus.BAD_REQUEST);
        }
        MfsAccountTxnLimitConfig persisitedConfig = mfsAccountTxnLimitConfigDAO.insert(accountTxnLimitConfig);
        return convertToAccountTxnLimitResponse(persisitedConfig);
    }

    @Transactional
    public AccountTxnLimitResponse updateMfsAccountTxnLimitConfig(UpdateAccountTxnLimitRequest accountTxnLimitConfigReq) {
        MfsAccountTxnLimitConfig dbAccountTxnLimitConfig = mfsAccountTxnLimitConfigDAO.load(accountTxnLimitConfigReq.getId(), MfsAccountTxnLimitConfig.Relationships.TransferType);
        dbAccountTxnLimitConfig.setLastModifiedDate(Calendar.getInstance());
        convertToMfsAccountTxnLimitConfig(accountTxnLimitConfigReq, dbAccountTxnLimitConfig);
        MfsAccountTxnLimitConfig persistedConfig = mfsAccountTxnLimitConfigDAO.update(dbAccountTxnLimitConfig);
        return convertToAccountTxnLimitResponse(persistedConfig);
    }

    @Transactional
    public int deleteMfsAccountTxnLimitConfig(Long accountTxnLimitConfigId) {
        return mfsAccountTxnLimitConfigDAO.delete(accountTxnLimitConfigId);
    }

    @Transactional
    public MfsAccountGenericLimitConfig loadAccountGenericLimitConfigByAccountAndName(Account account, String name) {
        MfsAccountGenericLimitConfig accountGenericLimitConfig = mfsAccountGenericLimitConfigDAO.loadByAccountAndName(account, name);
        if (accountGenericLimitConfig == null) {
            throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Not Found"), HttpStatus.NOT_FOUND);
        }
        return accountGenericLimitConfig;
    }

    @Transactional
    public MfsAccountGenericLimitConfig loadAccountGenericLimitConfig(Long accountGenericLimitConfigId) {
        return mfsAccountGenericLimitConfigDAO.load(accountGenericLimitConfigId);
    }

    @Transactional
    public List<MfsAccountGenericLimitConfig> loadAllMfsAccountGenericLimitConfigsByAccount(Account account) {
        return mfsAccountGenericLimitConfigDAO.getAllMfsAccountGenericLimitConfigsByAccount(account);
    }

    @Transactional
    public List<MfsAccountGenericLimitConfig> loadAllMfsAccountGenericLimitConfigsByAccountAndStatus(Account account, boolean enabled) {
        return mfsAccountGenericLimitConfigDAO.getMfsAccountGenericLimitConfigsByAccountAndStatus(account, enabled);
    }

    @Transactional
    public MfsAccountGenericLimitConfig loadAccountGenericLimitConfigByNameAndEnabled(Account account, String name, boolean enabled) {
        MfsAccountGenericLimitConfig accountGenericLimitConfig = mfsAccountGenericLimitConfigDAO.loadByAccountAndNameAndEnabled(account, name, enabled);
        if (accountGenericLimitConfig == null) {
            throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Name or Status is Invalid"), HttpStatus.NOT_FOUND);
        }
        return accountGenericLimitConfig;
    }

    @Transactional
    public AccountGenericLimitResponse saveMfsAccountGenericLimitConfig(MfsAccountGenericLimitConfig accougenericLimitConfig) {
    	accougenericLimitConfig.setCreatedAt(Calendar.getInstance());
        MfsGenericLimitConfig existingConfig = mfsGenericLimitConfigDAO.loadByName(accougenericLimitConfig.getName());
        if (existingConfig != null) {
          throw new MFSCommonException(ErrorConstants.INVALID_GENERIC_LIMIT_CONFIGURATION, String.format("Generic Limit Configuration Already Exists For Name: %s", accougenericLimitConfig.getName()), HttpStatus.BAD_REQUEST);
        }
        MfsAccountGenericLimitConfig persisitedConfig = mfsAccountGenericLimitConfigDAO.insert(accougenericLimitConfig);
        return convertToAccountGenericLimitResponse(persisitedConfig);
    }
 
    @Transactional
    public AccountGenericLimitResponse updateMfsAccountGenericLimitConfig(UpdateAccountGenericLimitRequest accountGenericLimitConfigReq) {
        MfsAccountGenericLimitConfig dbAccountGenericLimitConfig = mfsAccountGenericLimitConfigDAO.load(accountGenericLimitConfigReq.getId());
        dbAccountGenericLimitConfig = fetchService.fetch(dbAccountGenericLimitConfig, MfsGenericLimitConfig.Relationships.MFS_TRANSACTION_LIMIT_CONFIGS);
        dbAccountGenericLimitConfig.setLastModifiedDate(Calendar.getInstance());
        dbAccountGenericLimitConfig.setName(accountGenericLimitConfigReq.getName());
        dbAccountGenericLimitConfig.setDescription(accountGenericLimitConfigReq.getDescription());
        dbAccountGenericLimitConfig.setMaxAmountPerDay(accountGenericLimitConfigReq.getMaxAmountPerDay());
        dbAccountGenericLimitConfig.setMaxNumberOfTxnPerDay(accountGenericLimitConfigReq.getMaxNumberOfTxnPerDay());
        dbAccountGenericLimitConfig.setMaxAmountPerMonth(accountGenericLimitConfigReq.getMaxAmountPerMonth());
        dbAccountGenericLimitConfig.setMaxNumberOfTxnPerMonth(accountGenericLimitConfigReq.getMaxNumberOfTxnPerMonth());
        dbAccountGenericLimitConfig.setEnable(accountGenericLimitConfigReq.isEnable());
        MfsAccountGenericLimitConfig persistedConfig = mfsAccountGenericLimitConfigDAO.update(dbAccountGenericLimitConfig);
//        if (!persistedConfig.isEnable()) {
//            persistedConfig = fetchService.fetch(persistedConfig, MfsGenericLimitConfig.Relationships.MFS_TRANSACTION_LIMIT_CONFIGS);
//            Collection<? extends MfsTxnLimitConfig> asosiatedLimits = persistedConfig.getMfsTransactionLimitConfigs();
//            if (!CollectionUtils.isEmpty(asosiatedLimits)) {
//              for (MfsTxnLimitConfig limitConfig: asosiatedLimits) {
//                limitConfig.setLastModifiedDate(Calendar.getInstance());
//                limitConfig.setGenericLimit(null);
//                mfsTxnLimitConfigDAO.update(limitConfig);
//              }
//            }
//        }
        return convertToAccountGenericLimitResponse(persistedConfig);
    }

    @Transactional
    public int deleteMfsGenericLimitConfig(Long genericLimitConfigId) {
        return mfsGenericLimitConfigDAO.delete(genericLimitConfigId);
    }

    // Account Limit Related Data;
    @Transactional
    public List<AccountLimitData> getAccountLimitData(Account account) {
        String accountType = account.getType().getName();
        List<AccountLimitData> accountLimits = new ArrayList<>();
        List<MfsTxnLimitConfig> limtConfigs = mfsTxnLimitConfigDAO.getMfsTxnLimitConfigsByStatusAndAccountType(true, accountType);
        if (!CollectionUtils.isEmpty(limtConfigs)) {
            Set<MfsTxnLimitConfig> coveredLimitConfigs = new HashSet<>();
            for (MfsTxnLimitConfig limitConfig: limtConfigs) {
                if (coveredLimitConfigs.contains(limitConfig) 
                    || (limitConfig.getApplyOn().equals(LimitSubject.FROM) && !accountType.equalsIgnoreCase(limitConfig.getFromAcType()))
                    || (limitConfig.getApplyOn().equals(LimitSubject.TO) && !accountType.equalsIgnoreCase(limitConfig.getToAcType()))) {
                    continue;
                }
                AccountLimitData currentLimitData = new AccountLimitData();
                coveredLimitConfigs.add(limitConfig);
                Long dailyMaxNumberOfTxn = limitConfig.getMaxNumberOfTxnPerDay();
                BigDecimal dailyMaxAmount= limitConfig.getMaxAmountPerDay();
                Long monthlyMaxNumberOfTxn = limitConfig.getMaxNumberOfTxnPerMonth();
                BigDecimal monthlyMaxAmount = limitConfig.getMaxAmountPerMonth();
                boolean isApplyOnDestination = limitConfig.getApplyOn() == MfsTxnLimitConfig.LimitSubject.TO;
                MfsGenericLimitConfig genericConfig = limitConfig.getGenericLimit();
                Set<TransferType> transferTypes = new HashSet<>();
                if (genericConfig != null && genericConfig.isEnable()) {
                    dailyMaxNumberOfTxn = genericConfig.getMaxNumberOfTxnPerDay();
                    dailyMaxAmount = genericConfig.getMaxAmountPerDay();
                    monthlyMaxNumberOfTxn = genericConfig.getMaxNumberOfTxnPerMonth();
                    monthlyMaxAmount = genericConfig.getMaxAmountPerMonth();
                    genericConfig = fetchService.fetch(genericConfig, MfsGenericLimitConfig.Relationships.MFS_TRANSACTION_LIMIT_CONFIGS);
                    Collection<? extends MfsTxnLimitConfig> asosiatedLimits = genericConfig.getMfsTransactionLimitConfigs();
                    if (!CollectionUtils.isEmpty(asosiatedLimits)) {
                      for (MfsTxnLimitConfig relatedLimitConfig: asosiatedLimits) {
                        coveredLimitConfigs.add(relatedLimitConfig);
                        if (relatedLimitConfig.isEnable()) {
                            transferTypes.add(relatedLimitConfig.getTransferType());
                        }
                      }
                    }
                    currentLimitData.setLimitTypeName(genericConfig.getDescription());
                } else {
                    transferTypes.add(limitConfig.getTransferType());
                    currentLimitData.setLimitTypeName(limitConfig.getMfsTypeDescription());
                }
                Calendar today = Calendar.getInstance();
                // Get the txn count and amount on today
                TransactionSummaryVO dailyTxnCountAndAmount = transferDao.getTransactionedCountAndAmountBetweenPeriod(Period.day(today), account, isApplyOnDestination, transferTypes);
                // Get txn count and amount for this month
                Period monthOfDate = Period.monthOfDate(today);
                TransactionSummaryVO monthlyTxnCountAndAmount = transferDao.getTransactionedCountAndAmountBetweenPeriod(monthOfDate, account, isApplyOnDestination, transferTypes);
                currentLimitData.setDailyUsedTxnAmount(dailyTxnCountAndAmount.getAmount());
                currentLimitData.setDailyTxnAmount(dailyMaxAmount);
                currentLimitData.setDailyUsedTxnNumber(dailyTxnCountAndAmount.getCount());
                currentLimitData.setDailyTxnNumber(dailyMaxNumberOfTxn);
                currentLimitData.setMonthlyUsedTxnAmount(monthlyTxnCountAndAmount.getAmount());
                currentLimitData.setMonthlyTxnAmount(monthlyMaxAmount);
                currentLimitData.setMonthlyUsedTxnNumber(monthlyTxnCountAndAmount.getCount());
                currentLimitData.setMonthlyTxnNumber(monthlyMaxNumberOfTxn);
                accountLimits.add(currentLimitData);
            }
        }
        return accountLimits;
    }

    public WalletLimitResponse loadAccountLimitsByAccount(MemberAccount account) {
        WalletLimitResponse response = new WalletLimitResponse();
        Set<WalletLimitData> walletLimits = new HashSet<>();
        account = fetchService.fetch(account, MemberAccount.Relationships.MEMBER, MemberAccount.Relationships.ACCOUNT_LIMIT_CONFIGS, RelationshipHelper.nested(Element.Relationships.GROUP));
        List<MfsTxnLimitConfig> groupLimitConfigs = mfsTxnLimitConfigDAO.loadMfsTxnLimitConfigByGroup(account.getMember().getGroup());
        if (!CollectionUtils.isEmpty(groupLimitConfigs)) {
          for (MfsTxnLimitConfig groupConfig: groupLimitConfigs) {
              WalletLimitData wl = new WalletLimitData();
              wl.setMinAmountPerTxn(groupConfig.getMinAmountPerTxn());
              wl.setMaxAmountPerTxn(groupConfig.getMaxAmountPerTxn());
              wl.setMaxAmountPerDay(groupConfig.getMaxAmountPerDay());
              wl.setMaxNumberOfTxnPerDay(groupConfig.getMaxNumberOfTxnPerDay());
              wl.setMaxAmountPerMonth(groupConfig.getMaxAmountPerMonth());
              wl.setMaxNumberOfTxnPerMonth(groupConfig.getMaxNumberOfTxnPerMonth());
              wl.setTxnTypeId(groupConfig.getTransferType().getId());
              wl.setTxnTypeName(groupConfig.getTransferType().getName());
              wl.setEnable(groupConfig.isEnable());
              wl.setApplyOn(groupConfig.getApplyOn().name());
              wl.setGroupName(groupConfig.getGroup().getName());
              wl.setAccountId(account.getId());
              walletLimits.add(wl);
          }
        }
        List<MfsAccountTxnLimitConfig> mfsAccountTxnLimitConfigs = mfsAccountTxnLimitConfigDAO.getAllMfsAccountTxnLimitConfigsByAccount(account);
        if (!CollectionUtils.isEmpty(mfsAccountTxnLimitConfigs)) {
            for (MfsAccountTxnLimitConfig accountConfig: mfsAccountTxnLimitConfigs) {
                WalletLimitData wl = new WalletLimitData();
                wl.setMinAmountPerTxn(accountConfig.getMinAmountPerTxn());
                wl.setMaxAmountPerTxn(accountConfig.getMaxAmountPerTxn());
                wl.setMaxAmountPerDay(accountConfig.getMaxAmountPerDay());
                wl.setMaxNumberOfTxnPerDay(accountConfig.getMaxNumberOfTxnPerDay());
                wl.setMaxAmountPerMonth(accountConfig.getMaxAmountPerMonth());
                wl.setMaxNumberOfTxnPerMonth(accountConfig.getMaxNumberOfTxnPerMonth());
                wl.setTxnTypeId(accountConfig.getTransferType().getId());
                wl.setTxnTypeName(accountConfig.getTransferType().getName());
                wl.setEnable(accountConfig.isEnable());
                wl.setApplyOn(accountConfig.getApplyOn().name());
                walletLimits.add(wl);
                wl.setAccountId(account.getId());
            }
        }
        response.setLimits(walletLimits);
        return response;
    }

    public WalletLimitResponse loadLimitsByGroup(MemberGroup group, MemberAccountType type) {
        Set<TransferType> alreadyConfiguredTransferTypesOfGroup = new HashSet<>();
        fetchService.fetch(group, MemberGroup.Relationships.MFS_ACCOUNT_TYPE_SETTINGS, MemberGroup.Relationships.GROUP_TXN_LIMIT_CONFIGS, RelationshipHelper.nested(AccountType.Relationships.FROM_TRANSFER_TYPES, AccountType.Relationships.TO_TRANSFER_TYPES));
        WalletLimitResponse response = new WalletLimitResponse();
        Set<WalletLimitData> walletLimits = new HashSet<>();
        List<MfsTxnLimitConfig> mfsGroupTxnLimitConfigs = mfsTxnLimitConfigDAO.loadMfsTxnLimitConfigByGroup(group);
        if (!CollectionUtils.isEmpty(mfsGroupTxnLimitConfigs)) {
            for (MfsTxnLimitConfig config: mfsGroupTxnLimitConfigs) {
                WalletLimitData wl = new WalletLimitData();
                wl.setLimitId(config.getId());
                wl.setMinAmountPerTxn(config.getMinAmountPerTxn());
                wl.setMaxAmountPerTxn(config.getMaxAmountPerTxn());
                wl.setMaxAmountPerDay(config.getMaxAmountPerDay());
                wl.setMaxNumberOfTxnPerDay(config.getMaxNumberOfTxnPerDay());
                wl.setMaxAmountPerMonth(config.getMaxAmountPerMonth());
                wl.setMaxNumberOfTxnPerMonth(config.getMaxNumberOfTxnPerMonth());
                wl.setTxnTypeId(config.getTransferType().getId());
                wl.setTxnTypeName(config.getTransferType().getName());
                wl.setEnable(config.isEnable());
                wl.setApplyOn(config.getApplyOn().name());
                walletLimits.add(wl);
                alreadyConfiguredTransferTypesOfGroup.add(config.getTransferType());
            }
        }
        for (MfsAccountTypeGroup matg: group.getMfsAccountTypeSettings()) {
          if (type.getId()!= null && (type.getId().longValue() == matg.getAccountType().getId().longValue())) {
            for(TransferType fromType : matg.getAccountType().getFromTransferTypes()) {
                if (!alreadyConfiguredTransferTypesOfGroup.contains(fromType)) {
                    WalletLimitData wl = new WalletLimitData();
                    wl.setTxnTypeId(fromType.getId());
                    wl.setTxnTypeName(fromType.getName());
                    wl.setEnable(false);
                    wl.setApplyOn(MfsTxnLimitConfig.LimitSubject.FROM.name());
                    walletLimits.add(wl);
                }
            }
            for(TransferType toType : matg.getAccountType().getToTransferTypes()) {
                if (!alreadyConfiguredTransferTypesOfGroup.contains(toType)) {
                    WalletLimitData wl = new WalletLimitData();
                    wl.setTxnTypeId(toType.getId());
                    wl.setTxnTypeName(toType.getName());
                    wl.setEnable(false);
                    wl.setApplyOn(MfsTxnLimitConfig.LimitSubject.TO.name());
                    walletLimits.add(wl);
                }
            }
          }
        }
        response.setLimits(walletLimits);
        return response;
    }

  public WalletLimitResponse saveAccountLimitsByAccount(final MemberAccount account, final Set<WalletLimitData> requestedLimits, final Map<Long, TransferType> transferTypes) {
    WalletLimitResponse response = new WalletLimitResponse();
    fetchService.fetch(account, MemberAccount.Relationships.MEMBER, MemberAccount.Relationships.ACCOUNT_LIMIT_CONFIGS);
    final Map<WalletLimitData, MfsAccountTxnLimitConfig> existingLimitConfigs = new HashMap<>();
    account.getAccountTxnLimitConfigs().stream()
            .forEach( config -> existingLimitConfigs.put(new WalletLimitData(config.getTransferType().getId(), null, config.getApplyOn().name(), account.getId()), config));
    Set<MfsAccountTxnLimitConfig> requestedLimitConfigs = requestedLimits.stream()
            .map(limit -> buildMfsAccountTxnLimitConfig(account, limit, transferTypes, existingLimitConfigs))
            .collect(Collectors.toSet());
    account.updateAccountTxnLimitConfigs(requestedLimitConfigs);
    fetchService.fetch(account, MemberAccount.Relationships.ACCOUNT_LIMIT_CONFIGS);
    Collection<MfsAccountTxnLimitConfig> savedAccountLimitConfigs = account.getAccountTxnLimitConfigs();
    Set<WalletLimitData> savedWalletLimits = savedAccountLimitConfigs.stream()
            .map(config -> buildWalletLimitFromMfsAccountTxnLimitConfig(config, account))
            .collect(Collectors.toSet());
    response.setLimits(savedWalletLimits);
    response.setWalletNo(account.getOwnerName());
    return response;
  }

  @Transactional
  public Set<WalletLimitData> saveGroupLimits(final MemberGroup group, MemberAccountType type, Set<WalletLimitData> requestedLimits, final Map<Long, TransferType> transferTypes) {
      fetchService.fetch(group, MemberGroup.Relationships.MFS_ACCOUNT_TYPE_SETTINGS, MemberGroup.Relationships.GROUP_TXN_LIMIT_CONFIGS, RelationshipHelper.nested(AccountType.Relationships.FROM_TRANSFER_TYPES, AccountType.Relationships.TO_TRANSFER_TYPES));
      final Map<WalletLimitData, MfsTxnLimitConfig> existingLimitConfigs = new HashMap<>();
              group.getGroupTxnLimitConfigs().stream()
              .forEach( config -> existingLimitConfigs.put(new WalletLimitData(config.getTransferType().getId(), group.getId(), config.getApplyOn().name()), config));
      Set<MfsTxnLimitConfig> requestedLimitConfigs = requestedLimits.stream()
              .map(limit -> buildMfsTxnLimitConfig(group, limit, transferTypes, existingLimitConfigs))
              .collect(Collectors.toSet());
      group.updateGroupTxnLimitConfigs(requestedLimitConfigs);
      fetchService.fetch(group, MemberGroup.Relationships.GROUP_TXN_LIMIT_CONFIGS);
      Collection<MfsTxnLimitConfig> updatedConfigs = group.getGroupTxnLimitConfigs();
      return updatedConfigs.stream()
              .map(config -> buildWalletLimitFromMfsTxnLimitConfig(config, group))
              .collect(Collectors.toSet());
  }

    private MfsTxnLimitConfig convertToMfsTxnLimitConfig(UpdateTxnLimitRequest txnLimitUpdateReq, MfsTxnLimitConfig txnLimitConfig) {
        txnLimitConfig.setMfsTypeDescription(txnLimitUpdateReq.getMfsTypeDescription());
        txnLimitConfig.setMinAmountPerTxn(txnLimitUpdateReq.getMinAmountPerTxn());
        txnLimitConfig.setMaxAmountPerTxn(txnLimitUpdateReq.getMaxAmountPerTxn());
        txnLimitConfig.setMaxNumberOfTxnPerDay(txnLimitUpdateReq.getMaxNumberOfTxnPerDay());
        txnLimitConfig.setMaxAmountPerDay(txnLimitUpdateReq.getMaxAmountPerDay());
        txnLimitConfig.setMaxNumberOfTxnPerMonth(txnLimitUpdateReq.getMaxNumberOfTxnPerMonth());
        txnLimitConfig.setMaxAmountPerMonth(txnLimitUpdateReq.getMaxAmountPerMonth());
        txnLimitConfig.setApplyOn(txnLimitUpdateReq.getApplyOn());
        txnLimitConfig.setEnable(txnLimitUpdateReq.isEnable());
        if (StringUtils.isNotBlank(txnLimitUpdateReq.getGenericLimit())) {
            txnLimitConfig.setGenericLimit(loadGenericLimitConfigByNameAndEnabled(txnLimitUpdateReq.getGenericLimit(), true));
        } else {
            txnLimitConfig.setGenericLimit(null);
        }
        return txnLimitConfig;
      }

    private TxnLimitResponse convertToTxnLimitResponse(MfsTxnLimitConfig txnLimitConfig) {
      TxnLimitResponse response = new TxnLimitResponse();
      response.setId(txnLimitConfig.getId());
      response.setMfsTypeName(txnLimitConfig.getMfsTypeName());
      response.setMfsTypeDescription(txnLimitConfig.getMfsTypeDescription());
      response.setFromAcType(txnLimitConfig.getFromAcType());
      response.setToAcType(txnLimitConfig.getToAcType());
      response.setMinAmountPerTxn(txnLimitConfig.getMinAmountPerTxn());
      response.setMaxAmountPerTxn(txnLimitConfig.getMaxAmountPerTxn());
      response.setMaxNumberOfTxnPerDay(txnLimitConfig.getMaxNumberOfTxnPerDay());
      response.setMaxAmountPerDay(txnLimitConfig.getMaxAmountPerDay());
      response.setMaxNumberOfTxnPerMonth(txnLimitConfig.getMaxNumberOfTxnPerMonth());
      response.setMaxAmountPerMonth(txnLimitConfig.getMaxAmountPerMonth());
      response.setApplyOn(txnLimitConfig.getApplyOn());
      response.setCoreTxnType(txnLimitConfig.getTransferType().getName());
      response.setEnable(txnLimitConfig.isEnable());
      return response;
    }

    private GenericLimitResponse convertToGenericLimitResponse(MfsGenericLimitConfig genericLimitConfig) {
    	GenericLimitResponse response = new GenericLimitResponse();
        response.setId(genericLimitConfig.getId());
        response.setName(genericLimitConfig.getName());
        response.setDescription(genericLimitConfig.getDescription());
        response.setMaxNumberOfTxnPerDay(genericLimitConfig.getMaxNumberOfTxnPerDay());
        response.setMaxAmountPerDay(genericLimitConfig.getMaxAmountPerDay());
        response.setMaxNumberOfTxnPerMonth(genericLimitConfig.getMaxNumberOfTxnPerMonth());
        response.setMaxAmountPerMonth(genericLimitConfig.getMaxAmountPerMonth());
        response.setEnable(genericLimitConfig.isEnable());
        return response;
      }

    private AccountTxnLimitResponse convertToAccountTxnLimitResponse(MfsAccountTxnLimitConfig accountTxnLimitConfig) {
        AccountTxnLimitResponse response = new AccountTxnLimitResponse();
        response.setId(accountTxnLimitConfig.getId());
        response.setMfsTypeName(accountTxnLimitConfig.getMfsTypeName());
        response.setMfsTypeDescription(accountTxnLimitConfig.getMfsTypeDescription());
        response.setFromAcType(accountTxnLimitConfig.getFromAcType());
        response.setToAcType(accountTxnLimitConfig.getToAcType());
        response.setMinAmountPerTxn(accountTxnLimitConfig.getMinAmountPerTxn());
        response.setMaxAmountPerTxn(accountTxnLimitConfig.getMaxAmountPerTxn());
        response.setMaxNumberOfTxnPerDay(accountTxnLimitConfig.getMaxNumberOfTxnPerDay());
        response.setMaxAmountPerDay(accountTxnLimitConfig.getMaxAmountPerDay());
        response.setMaxNumberOfTxnPerMonth(accountTxnLimitConfig.getMaxNumberOfTxnPerMonth());
        response.setMaxAmountPerMonth(accountTxnLimitConfig.getMaxAmountPerMonth());
        response.setApplyOn(accountTxnLimitConfig.getApplyOn());
        response.setCoreTxnType(accountTxnLimitConfig.getTransferType().getName());
        response.setEnable(accountTxnLimitConfig.isEnable());
        response.setWalletNo(accountTxnLimitConfig.getAccount().getOwnerName());
        return response;
      }

    private AccountGenericLimitResponse convertToAccountGenericLimitResponse(MfsAccountGenericLimitConfig accountGenericLimitConfig) {
    	AccountGenericLimitResponse response = new AccountGenericLimitResponse();
        response.setId(accountGenericLimitConfig.getId());
        response.setName(accountGenericLimitConfig.getName());
        response.setDescription(accountGenericLimitConfig.getDescription());
        response.setMaxNumberOfTxnPerDay(accountGenericLimitConfig.getMaxNumberOfTxnPerDay());
        response.setMaxAmountPerDay(accountGenericLimitConfig.getMaxAmountPerDay());
        response.setMaxNumberOfTxnPerMonth(accountGenericLimitConfig.getMaxNumberOfTxnPerMonth());
        response.setMaxAmountPerMonth(accountGenericLimitConfig.getMaxAmountPerMonth());
        response.setEnable(accountGenericLimitConfig.isEnable());
        return response;
      }

    private MfsAccountTxnLimitConfig convertToMfsAccountTxnLimitConfig(UpdateAccountTxnLimitRequest accountTxnLimitUpdateReq, MfsAccountTxnLimitConfig accountTxnLimitConfig) {
        accountTxnLimitConfig.setMfsTypeDescription(accountTxnLimitUpdateReq.getMfsTypeDescription());
        accountTxnLimitConfig.setMinAmountPerTxn(accountTxnLimitUpdateReq.getMinAmountPerTxn());
        accountTxnLimitConfig.setMaxAmountPerTxn(accountTxnLimitUpdateReq.getMaxAmountPerTxn());
        accountTxnLimitConfig.setMaxNumberOfTxnPerDay(accountTxnLimitUpdateReq.getMaxNumberOfTxnPerDay());
        accountTxnLimitConfig.setMaxAmountPerDay(accountTxnLimitUpdateReq.getMaxAmountPerDay());
        accountTxnLimitConfig.setMaxNumberOfTxnPerMonth(accountTxnLimitUpdateReq.getMaxNumberOfTxnPerMonth());
        accountTxnLimitConfig.setMaxAmountPerMonth(accountTxnLimitUpdateReq.getMaxAmountPerMonth());
        accountTxnLimitConfig.setApplyOn(accountTxnLimitUpdateReq.getApplyOn());
        accountTxnLimitConfig.setEnable(accountTxnLimitUpdateReq.isEnable());
//        if (StringUtils.isNotBlank(accountTxnLimitUpdateReq.getGenericAccountLimit())) {
//            accountTxnLimitConfig.setAccountGenericLimit(loadAccountGenericLimitConfigByNameAndEnabled(accountTxnLimitUpdateReq.getGenericAccountLimit(), true));
//        } else {
//            txnLimitConfig.setGenericLimit(null);
//        }
        return accountTxnLimitConfig;
      }

    private MfsTxnLimitConfig buildMfsTxnLimitConfig(MemberGroup group, WalletLimitData txnLimit, Map<Long,TransferType> transferTypes, final Map<WalletLimitData, MfsTxnLimitConfig> existingConfigs) {
    	txnLimit.setGroupId(group.getId());
    	MfsTxnLimitConfig txnLimitConfig;
        if (existingConfigs.containsKey(txnLimit)){
            txnLimitConfig = existingConfigs.get(txnLimit);
        } else {
            txnLimitConfig = new MfsTxnLimitConfig();
        }
        txnLimitConfig.setMinAmountPerTxn(txnLimit.getMinAmountPerTxn());
        txnLimitConfig.setMaxAmountPerTxn(txnLimit.getMaxAmountPerTxn());
        txnLimitConfig.setMaxNumberOfTxnPerDay(txnLimit.getMaxNumberOfTxnPerDay());
        txnLimitConfig.setMaxNumberOfTxnPerMonth(txnLimit.getMaxNumberOfTxnPerMonth());
        txnLimitConfig.setMaxAmountPerDay(txnLimit.getMaxAmountPerDay());
        txnLimitConfig.setMaxAmountPerMonth(txnLimit.getMaxAmountPerMonth());
        txnLimitConfig.setApplyOn(MfsTxnLimitConfig.LimitSubject.valueOf(txnLimit.getApplyOn()));
        TransferType transferType = transferTypes.get(txnLimit.getTxnTypeId());
        txnLimitConfig.setMfsTypeName(transferType.getName());
        txnLimitConfig.setMfsTypeDescription(transferType.getDescription());
        txnLimitConfig.setTransferType(transferType);
        txnLimitConfig.setFromAcType(transferType.getFrom().getName());
        txnLimitConfig.setToAcType(transferType.getTo().getName());
        txnLimitConfig.setEnable(txnLimit.isEnable());
        txnLimitConfig.setGroup(group);
//        if (StringUtils.isNotBlank(txnLimit.getGenericLimit())) {
//            MfsGenericLimitConfig gnericLimitConfig = loadGenericLimitConfigByNameAndEnabled(txnLimit.getGenericLimit(), true);
//            txnLimitConfig.setGenericLimit(gnericLimitConfig);
//        }
        return txnLimitConfig;
    }

    private MfsAccountTxnLimitConfig buildMfsAccountTxnLimitConfig(MemberAccount account, WalletLimitData txnLimit, Map<Long,TransferType> transferTypes, final Map<WalletLimitData, MfsAccountTxnLimitConfig> existingConfigs) {
        txnLimit.setAccountId(account.getId());
        MfsAccountTxnLimitConfig txnLimitConfig;
        if (existingConfigs.containsKey(txnLimit)){
            txnLimitConfig = existingConfigs.get(txnLimit);
        } else {
            txnLimitConfig = new MfsAccountTxnLimitConfig();
        }
        txnLimitConfig.setMinAmountPerTxn(txnLimit.getMinAmountPerTxn());
        txnLimitConfig.setMaxAmountPerTxn(txnLimit.getMaxAmountPerTxn());
        txnLimitConfig.setMaxNumberOfTxnPerDay(txnLimit.getMaxNumberOfTxnPerDay());
        txnLimitConfig.setMaxNumberOfTxnPerMonth(txnLimit.getMaxNumberOfTxnPerMonth());
        txnLimitConfig.setMaxAmountPerDay(txnLimit.getMaxAmountPerDay());
        txnLimitConfig.setMaxAmountPerMonth(txnLimit.getMaxAmountPerMonth());
        txnLimitConfig.setApplyOn(MfsAccountTxnLimitConfig.LimitSubject.valueOf(txnLimit.getApplyOn()));
        TransferType transferType = transferTypes.get(txnLimit.getTxnTypeId());
        txnLimitConfig.setMfsTypeName(transferType.getName());
        txnLimitConfig.setMfsTypeDescription(transferType.getDescription());
        txnLimitConfig.setTransferType(transferType);
        txnLimitConfig.setFromAcType(transferType.getFrom().getName());
        txnLimitConfig.setToAcType(transferType.getTo().getName());
        txnLimitConfig.setEnable(txnLimit.isEnable());
        txnLimitConfig.setAccount(account);
//        if (StringUtils.isNotBlank(txnLimit.getGenericLimit())) {
//            MfsGenericLimitConfig gnericLimitConfig = loadGenericLimitConfigByNameAndEnabled(txnLimit.getGenericLimit(), true);
//            txnLimitConfig.setGenericLimit(gnericLimitConfig);
//        }
        return txnLimitConfig;
    }

    private WalletLimitData buildWalletLimitFromMfsTxnLimitConfig(MfsTxnLimitConfig txnLimitConfig, MemberGroup group) {
        WalletLimitData txnLimit = new WalletLimitData(txnLimitConfig.getTransferType().getId(), group.getId(), txnLimitConfig.getApplyOn().name());
        txnLimit.setLimitId(txnLimitConfig.getId());
        txnLimit.setMinAmountPerTxn(txnLimitConfig.getMinAmountPerTxn());
        txnLimit.setMaxAmountPerTxn(txnLimitConfig.getMaxAmountPerTxn());
        txnLimit.setMaxNumberOfTxnPerDay(txnLimitConfig.getMaxNumberOfTxnPerDay());
        txnLimit.setMaxNumberOfTxnPerMonth(txnLimitConfig.getMaxNumberOfTxnPerMonth());
        txnLimit.setMaxAmountPerDay(txnLimitConfig.getMaxAmountPerDay());
        txnLimit.setMaxAmountPerMonth(txnLimitConfig.getMaxAmountPerMonth());
        txnLimit.setApplyOn(txnLimitConfig.getApplyOn().name());
        txnLimit.setEnable(txnLimitConfig.isEnable());
        txnLimit.setGroupName(group.getName());
        txnLimit.setTxnTypeName(txnLimitConfig.getTransferType().getName());
        txnLimit.setTxnTypeId(txnLimitConfig.getTransferType().getId());
//        if (StringUtils.isNotBlank(txnLimit.getGenericLimit())) {
//            MfsGenericLimitConfig gnericLimitConfig = loadGenericLimitConfigByNameAndEnabled(txnLimit.getGenericLimit(), true);
//            txnLimitConfig.setGenericLimit(gnericLimitConfig);
//        }
        return txnLimit;
    }

    private WalletLimitData buildWalletLimitFromMfsAccountTxnLimitConfig(MfsAccountTxnLimitConfig txnLimitConfig, MemberAccount account) {
        WalletLimitData txnLimit = new WalletLimitData(txnLimitConfig.getTransferType().getId(), null, txnLimitConfig.getApplyOn().name(), account.getId());
        txnLimit.setLimitId(txnLimitConfig.getId());
        txnLimit.setMinAmountPerTxn(txnLimitConfig.getMinAmountPerTxn());
        txnLimit.setMaxAmountPerTxn(txnLimitConfig.getMaxAmountPerTxn());
        txnLimit.setMaxNumberOfTxnPerDay(txnLimitConfig.getMaxNumberOfTxnPerDay());
        txnLimit.setMaxNumberOfTxnPerMonth(txnLimitConfig.getMaxNumberOfTxnPerMonth());
        txnLimit.setMaxAmountPerDay(txnLimitConfig.getMaxAmountPerDay());
        txnLimit.setMaxAmountPerMonth(txnLimitConfig.getMaxAmountPerMonth());
        txnLimit.setApplyOn(txnLimitConfig.getApplyOn().name());
        txnLimit.setEnable(txnLimitConfig.isEnable());
        txnLimit.setTxnTypeName(txnLimitConfig.getTransferType().getName());
        txnLimit.setTxnTypeId(txnLimitConfig.getTransferType().getId());
//        if (StringUtils.isNotBlank(txnLimit.getGenericLimit())) {
//            MfsGenericLimitConfig gnericLimitConfig = loadGenericLimitConfigByNameAndEnabled(txnLimit.getGenericLimit(), true);
//            txnLimitConfig.setGenericLimit(gnericLimitConfig);
//        }
        return txnLimit;
    }
}