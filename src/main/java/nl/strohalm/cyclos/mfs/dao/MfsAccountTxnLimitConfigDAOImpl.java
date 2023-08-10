package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTxnLimitConfig.LimitSubject;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

public class MfsAccountTxnLimitConfigDAOImpl extends BaseDAOImpl<MfsAccountTxnLimitConfig> implements MfsAccountTxnLimitConfigDAO {
    public MfsAccountTxnLimitConfigDAOImpl() {
        super(MfsAccountTxnLimitConfig.class);
    }

    @Override
    public List<MfsAccountTxnLimitConfig> getAllMfsAccountTxnLimitConfigs() {
        final StringBuilder hql = new StringBuilder();
        hql.append(" select matlc");
        hql.append(" from MfsAccountTxnLimitConfig matlc");
        return list(hql.toString(), null);
    }

    @Override
    public List<MfsAccountTxnLimitConfig> getAllMfsAccountTxnLimitConfigsByAccount(Account account) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "matlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
        return list(hql.toString(), namedParameters);
    }

    @Override
    public List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatus(boolean enabled) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "matlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
        return list(hql.toString(), namedParameters);
	}

	@Override
	public List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndAccount(boolean enabled,
			Account account) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "matlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
        HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
        return list(hql.toString(), namedParameters);
	}


    @Override
    public List<MfsAccountTxnLimitConfig> loadMfsAccountTxnLimitConfigByTransferTypeAndApplyOn(TransferType transferType, LimitSubject applyOn) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "matlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "transferType", transferType);
        HibernateHelper.addParameterToQuery(hql, namedParameters, "applyOn", applyOn);
        return list(hql.toString(), namedParameters);
    }

	@Override
	public List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndAccountType(boolean enabled, String accountType) {
        if (StringUtils.isBlank(accountType)) {
            throw new IllegalArgumentException("accountType must be provided");
        }
        final Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("accountType", accountType);
        namedParameters.put("enabled", enabled);
        
        final StringBuilder hql = new StringBuilder();
        hql.append(" from MfsAccountTxnLimitConfig matlc ");
        hql.append(" where 1=1 ");
        hql.append(" and matlc.enable = :enabled");
        hql.append(" and (matlc.fromAcType = :accountType or mtlc.toAcType = :accountType)");
        return list(hql.toString(), namedParameters);
    }

    @Override
    public List<MfsAccountTxnLimitConfig> loadMfsAccountTxnLimitConfigByTransferTypeAndAccountAndApplyOn(TransferType transferType, Account account, LimitSubject applyOn) {
        final Map<String, Object> namedParameters = new HashMap<String, Object>();
        final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "matlc");
        HibernateHelper.addParameterToQuery(hql, namedParameters, "transferType", transferType);
        HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
        HibernateHelper.addParameterToQuery(hql, namedParameters, "applyOn", applyOn);
        return list(hql.toString(), namedParameters);
	}
	@Override
    public List<MfsAccountTxnLimitConfig> getMfsAccountTxnLimitConfigsByStatusAndTransferTypeAndAccountsIn(boolean enabled, TransferType transferType, Set<Account> accounts) {
        if (CollectionUtils.isEmpty(accounts)) {
            throw new IllegalArgumentException("accounts must be provided");
        }
        final Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("enabled", enabled);
        namedParameters.put("transferType", transferType);
        namedParameters.put("accounts", accounts);

        final StringBuilder hql = new StringBuilder();
        hql.append(" from MfsAccountTxnLimitConfig matlc ");
        hql.append(" where 1=1 ");
        hql.append(" and matlc.enable = :enabled");
        hql.append(" and matlc.transferType = :transferType");
        hql.append(" and (matlc.account IN (:accounts))");
        return list(hql.toString(), namedParameters);
	}
}
