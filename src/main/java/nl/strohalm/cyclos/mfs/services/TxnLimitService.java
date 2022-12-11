package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.mfs.dao.MfsTxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.TxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;
import nl.strohalm.cyclos.mfs.models.transactions.UpdateTxnLimitRequest;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxnLimitService {

    @Autowired
    private TxnLimitConfigDAO txnLimitConfigDAO;

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
    public TxnLimitConfig saveTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
        return txnLimitConfigDAO.insert(txnLimitConfig);
    }

    @Transactional
    public TxnLimitResponse saveMfsTxnLimitConfig(MfsTxnLimitConfig txnLimitConfig) {
        txnLimitConfig.setCreatedAt(Calendar.getInstance());
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

    private MfsTxnLimitConfig convertToMfsTxnLimitConfig(UpdateTxnLimitRequest txnLimitUpdateReq, MfsTxnLimitConfig txnLimitConfig) {
    	txnLimitConfig.setMinAmountPerTxn(txnLimitUpdateReq.getMinAmountPerTxn());
    	txnLimitConfig.setMaxAmountPerTxn(txnLimitUpdateReq.getMaxAmountPerTxn());
    	txnLimitConfig.setMaxNumberOfTxnPerDay(txnLimitUpdateReq.getMaxNumberOfTxnPerDay());
    	txnLimitConfig.setMaxAmountPerDay(txnLimitUpdateReq.getMaxAmountPerDay());
    	txnLimitConfig.setMaxNumberOfTxnPerMonth(txnLimitUpdateReq.getMaxNumberOfTxnPerMonth());
    	txnLimitConfig.setMaxAmountPerMonth(txnLimitUpdateReq.getMaxAmountPerMonth());
    	txnLimitConfig.setApplyOn(txnLimitUpdateReq.getApplyOn());
    	txnLimitConfig.setEnable(txnLimitUpdateReq.isEnable());
        return txnLimitConfig;
      }

    private TxnLimitResponse convertToTxnLimitResponse(MfsTxnLimitConfig txnLimitConfig) {
      TxnLimitResponse response = new TxnLimitResponse();
      response.setId(txnLimitConfig.getId());
      response.setMinAmountPerTxn(txnLimitConfig.getMinAmountPerTxn());
      response.setMaxAmountPerTxn(txnLimitConfig.getMaxAmountPerTxn());
      response.setMaxNumberOfTxnPerDay(txnLimitConfig.getMaxNumberOfTxnPerDay());
      response.setMaxAmountPerDay(txnLimitConfig.getMaxAmountPerDay());
      response.setMaxNumberOfTxnPerMonth(txnLimitConfig.getMaxNumberOfTxnPerMonth());
      response.setMaxAmountPerMonth(txnLimitConfig.getMaxAmountPerMonth());
      response.setApplyOn(txnLimitConfig.getApplyOn());
      response.setTxnType(txnLimitConfig.getTransferType().getName());
      response.setEnable(txnLimitConfig.isEnable());
      return response;
    }
}
