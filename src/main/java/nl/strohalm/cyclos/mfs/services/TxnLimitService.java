package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.mfs.dao.MfsTxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.dao.TxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import nl.strohalm.cyclos.mfs.models.transactions.TxnLimitResponse;

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
    public MfsTxnLimitConfig loadMfsTxnLimitConfig(Long txnLimitConfigId) {
        return mfsTxnLimitConfigDAO.load(txnLimitConfigId);
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
    public TxnLimitResponse updateMfsTxnLimitConfig(MfsTxnLimitConfig txnLimitConfig) {
        txnLimitConfig.setLastModifiedDate(Calendar.getInstance());
        MfsTxnLimitConfig persistedConfig = mfsTxnLimitConfigDAO.update(txnLimitConfig);
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
