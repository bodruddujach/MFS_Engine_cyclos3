package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig.LimitSubject;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

public class MfsTxnLimitConfigDAOImpl extends BaseDAOImpl<MfsTxnLimitConfig> implements MfsTxnLimitConfigDAO {
    public MfsTxnLimitConfigDAOImpl() {
        super(MfsTxnLimitConfig.class);
    }

    @Override
    public List<MfsTxnLimitConfig> getAllMfsTxnLimitConfigs() {
        final StringBuilder hql = new StringBuilder();
        hql.append(" select mtlc");
        hql.append(" from MfsTxnLimitConfig mtlc");
        return list(hql.toString(), null);
    }

    @Override
    public List<MfsTxnLimitConfig> getMfsTxnLimitConfigsByStatus(boolean enabled) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mtlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
        return list(hql.toString(), namedParameters);
	}

    @Override
    public List<MfsTxnLimitConfig> loadMfsTxnLimitConfigByTransferTypeAndApplyOn(TransferType transferType, LimitSubject applyOn) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mtlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "transferType", transferType);
        HibernateHelper.addParameterToQuery(hql, namedParameters, "applyOn", applyOn);
        return list(hql.toString(), namedParameters);
    }


}
