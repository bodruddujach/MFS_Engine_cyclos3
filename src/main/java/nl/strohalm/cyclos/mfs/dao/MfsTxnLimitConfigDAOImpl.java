package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;

public class MfsTxnLimitConfigDAOImpl extends BaseDAOImpl<MfsTxnLimitConfig> implements MfsTxnLimitConfigDAO {
    public MfsTxnLimitConfigDAOImpl() {
        super(MfsTxnLimitConfig.class);
    }
}
