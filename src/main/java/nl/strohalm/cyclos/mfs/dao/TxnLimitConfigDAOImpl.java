package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.TxnLimitConfig;

public class TxnLimitConfigDAOImpl extends BaseDAOImpl<TxnLimitConfig> implements TxnLimitConfigDAO {
    public TxnLimitConfigDAOImpl() {
        super(TxnLimitConfig.class);
    }
}
