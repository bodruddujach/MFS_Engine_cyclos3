package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

public class MfsGenericLimitConfigDAOImpl extends BaseDAOImpl<MfsGenericLimitConfig>
		implements MfsGenericLimitConfigDAO {

	public MfsGenericLimitConfigDAOImpl() {
		super(MfsGenericLimitConfig.class);
	}

	@Override
	public List<MfsGenericLimitConfig> getAllMfsGenericLimitConfigs() {
		final StringBuilder hql = new StringBuilder();
		hql.append(" select mglc");
		hql.append(" from MfsGenericLimitConfig mglc");
		return list(hql.toString(), null);
	}

	@Override
	public List<MfsGenericLimitConfig> getMfsGLienericmitConfigsByStatus(boolean enabled) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
		return list(hql.toString(), namedParameters);
	}

	@Override
	public MfsGenericLimitConfig loadByName(String name) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "name", name);
		return uniqueResult(hql.toString(), namedParameters);
	}

	@Override
	public MfsGenericLimitConfig loadByNameAndEnabled(String name, boolean enabled) {
		final Map<String, Object> namedParameters = new HashMap<String, Object>();
		final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mglc");
		HibernateHelper.addParameterToQuery(hql, namedParameters, "name", name);
		HibernateHelper.addParameterToQuery(hql, namedParameters, "enabled", enabled);
		return uniqueResult(hql.toString(), namedParameters);
	}

}
