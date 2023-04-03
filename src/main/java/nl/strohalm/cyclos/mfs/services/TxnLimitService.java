package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.dao.accounts.transactions.TransferDAO;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.dao.MfsGenericLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.MfsTxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.TxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.exceptions.ErrorConstants;
import nl.strohalm.cyclos.mfs.exceptions.MFSCommonException;
import nl.strohalm.cyclos.mfs.models.transactions.AccountLimitData;
import nl.strohalm.cyclos.mfs.models.transactions.GenericLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateGenericLimitRequest;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;
import nl.strohalm.cyclos.services.fetch.FetchServiceLocal;
import nl.strohalm.cyclos.services.transactions.TransactionSummaryVO;
import nl.strohalm.cyclos.utils.Period;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public TxnLimitConfig loadTxnLimitConfig(Long txnLimitConfigId) {
        return txnLimitConfigDAO.load(txnLimitConfigId);
    }

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

    @Transactional
    public TxnLimitConfig saveTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
        return txnLimitConfigDAO.insert(txnLimitConfig);
    }

    @Transactional
    public TxnLimitResponse saveMfsTxnLimitConfig(MfsTxnLimitConfig txnLimitConfig) {
        txnLimitConfig.setCreatedAt(Calendar.getInstance());
        List<MfsTxnLimitConfig> existingConfigs = mfsTxnLimitConfigDAO.loadMfsTxnLimitConfigByTransferTypeAndApplyOn(txnLimitConfig.getTransferType(), txnLimitConfig.getApplyOn());
        if (!CollectionUtils.isEmpty(existingConfigs)) {
          throw new MFSCommonException(ErrorConstants.TXN_LIMIT_CONFIGURATION_EXISTS, String.format("Transaction Limit Configuration Already Exists For TransferType: %s, ApplyOn: %s", txnLimitConfig.getTransferType().getName(), txnLimitConfig.getApplyOn()), HttpStatus.BAD_REQUEST);
        }
        MfsTxnLimitConfig persisitedConfig = mfsTxnLimitConfigDAO.insert(txnLimitConfig);
        return convertToTxnLimitResponse(persisitedConfig);
    }

    @Transactional
    public TxnLimitConfig updateTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
        System.out.println(txnLimitConfig.toString());
        return txnLimitConfigDAO.update(txnLimitConfig);
    }

    @Transactional
    public TxnLimitResponse updateMfsTxnLimitConfig(UpdateTxnLimitRequest txnLimitConfigReq) {
        MfsTxnLimitConfig dbTxnLimitConfig = mfsTxnLimitConfigDAO.load(txnLimitConfigReq.getId(), MfsTxnLimitConfig.Relationships.TransferType);
        dbTxnLimitConfig.setLastModifiedDate(Calendar.getInstance());
        convertToMfsTxnLimitConfig(txnLimitConfigReq, dbTxnLimitConfig);
        MfsTxnLimitConfig persistedConfig = mfsTxnLimitConfigDAO.update(dbTxnLimitConfig);
        return convertToTxnLimitResponse(persistedConfig);
    }

    @Transactional
    public int deleteTxnLimitConfig(Long txnLimitConfigId) {
        return mfsTxnLimitConfigDAO.delete(txnLimitConfigId);
    }

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
    public int deleteMfsGenericLimitConfig(Long genericLimitConfigId) {
        return mfsGenericLimitConfigDAO.delete(genericLimitConfigId);
    }

    @Transactional
    public List<AccountLimitData> getAccountLimitData(Account account) {
        String accountType = account.getType().getName();
        List<AccountLimitData> accountLimits = new ArrayList<>();
        List<MfsTxnLimitConfig> limtConfigs = mfsTxnLimitConfigDAO.getMfsTxnLimitConfigsByStatusAndAccountType(true, accountType);
        if (!CollectionUtils.isEmpty(limtConfigs)) {
            Set<MfsTxnLimitConfig> coveredLimitConfigs = new HashSet<>();
            for (MfsTxnLimitConfig limtConfig: limtConfigs) {
                if (coveredLimitConfigs.contains(limtConfig)) {
                    continue;
                }
                AccountLimitData currentLimitData = new AccountLimitData();
                coveredLimitConfigs.add(limtConfig);
                Integer dailyMaxNumberOfTxn = limtConfig.getMaxNumberOfTxnPerDay();
                BigDecimal dailyMaxAmount= limtConfig.getMaxAmountPerDay();
                Integer monthlyMaxNumberOfTxn = limtConfig.getMaxNumberOfTxnPerMonth();
                BigDecimal monthlyMaxAmount = limtConfig.getMaxAmountPerMonth();
                boolean isApplyOnDestination = limtConfig.getApplyOn() == MfsTxnLimitConfig.LimitSubject.TO;
                MfsGenericLimitConfig genericConfig = limtConfig.getGenericLimit();
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
                    transferTypes.add(limtConfig.getTransferType());
                    currentLimitData.setLimitTypeName(limtConfig.getMfsTypeDescription());
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

    private MfsTxnLimitConfig convertToMfsTxnLimitConfig(UpdateTxnLimitRequest txnLimitUpdateReq, MfsTxnLimitConfig txnLimitConfig) {
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
}
