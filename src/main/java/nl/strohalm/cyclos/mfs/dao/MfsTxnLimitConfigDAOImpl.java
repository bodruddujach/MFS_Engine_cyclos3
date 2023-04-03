package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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

	@Override
	public List<MfsTxnLimitConfig> getMfsTxnLimitConfigsByStatusAndAccountType(boolean enabled, String accountType) {
        if (StringUtils.isBlank(accountType)) {
            throw new IllegalArgumentException("accountType must be provided");
        }
        final Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("accountType", accountType);
        namedParameters.put("enabled", enabled);
        
        final StringBuilder hql = new StringBuilder();
        hql.append(" from MfsTxnLimitConfig mtlc ");
        hql.append(" where 1=1 ");
        hql.append(" and mtlc.enable = :enabled");
        hql.append(" and (mtlc.fromAcType = :accountType or mtlc.toAcType = :accountType");
        return list(hql.toString(), namedParameters);
	}


}
