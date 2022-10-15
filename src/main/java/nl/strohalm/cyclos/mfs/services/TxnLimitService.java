package nl.strohalm.cyclos.mfs.services;

import nl.strohalm.cyclos.mfs.dao.TxnLimitConfigDAO;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxnLimitService {

    @Autowired
    private TxnLimitConfigDAO txnLimitConfigDAO;

    @Transactional
    public TxnLimitConfig loadTxnLimitConfig(Long txnLimitConfigId) {
        return txnLimitConfigDAO.load(txnLimitConfigId);
    }

    @Transactional
    public TxnLimitConfig saveTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
        return txnLimitConfigDAO.insert(txnLimitConfig);
    }
    @Transactional
    public TxnLimitConfig updateTxnLimitConfig(TxnLimitConfig txnLimitConfig) {
        System.out.println(txnLimitConfig.toString());
        return txnLimitConfigDAO.update(txnLimitConfig);
    }

    @Transactional
    public int deleteTxnLimitConfig(Long txnLimitConfigId) {
        return txnLimitConfigDAO.delete(txnLimitConfigId);
    }

}
