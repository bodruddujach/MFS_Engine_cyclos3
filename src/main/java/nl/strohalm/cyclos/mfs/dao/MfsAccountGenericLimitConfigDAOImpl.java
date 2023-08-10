package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.mfs.entities.MfsAccountGenericLimitConfig;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

public class MfsAccountGenericLimitConfigDAOImpl extends BaseDAOImpl<MfsAccountGenericLimitConfig>
		implements MfsAccountGenericLimitConfigDAO {

	public MfsAccountGenericLimitConfigDAOImpl() {
		super(MfsAccountGenericLimitConfig.class);
	}

	@Override
	public List<MfsAccountGenericLimitConfig> getAllMfsAccountGenericLimitConfigsByAccount(Account account) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "maglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
		return list(hql.toString(), namedParameters);
	}

	@Override
	public List<MfsAccountGenericLimitConfig> getMfsAccountGenericLimitConfigsByAccountAndStatus(Account account, boolean enabled) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "maglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
		HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
		return list(hql.toString(), namedParameters);
	}

	@Override
	public MfsAccountGenericLimitConfig loadByAccountAndName(Account account, String name) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "maglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
		HibernateHelper.addParameterToQuery(hql, namedParameters, "name", name);
		return uniqueResult(hql.toString(), namedParameters);
	}

	@Override
	public MfsAccountGenericLimitConfig loadByAccountAndNameAndEnabled(Account account, String name, boolean enabled) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "maglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "account", account);
		HibernateHelper.addParameterToQuery(hql, namedParameters, "name", name);
		HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
		return uniqueResult(hql.toString(), namedParameters);
	}

}
