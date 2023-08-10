package nl.strohalm.cyclos.mfs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.exceptions.EntityNotFoundException;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTypeGroup;

public class MfsAccountTypeGroupDaoImpl extends BaseDAOImpl<MfsAccountTypeGroup> implements MfsAccountTypeGroupDao {

	public MfsAccountTypeGroupDaoImpl() {
		super(MfsAccountTypeGroup.class);
	}

	public void delete(final long accountTypeId, final long groupId) {
		getHibernateTemplate().delete(load(accountTypeId, groupId));
	}

	public MfsAccountTypeGroup load(final long accountTypeId, final long groupId, final Relationship... fetch) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("accountTypeId", accountTypeId);
		final MfsAccountTypeGroup matg = uniqueResult(
				"from " + getEntityType().getName() + " where group.id = :groupId and accountType.id = :accountTypeId",
				params);
		if (matg == null) {
			throw new EntityNotFoundException(getEntityType());
		}
		if (fetch != null && fetch.length > 0) {
			return load(matg.getId(), fetch);
		} else {
			return matg;
		}
	}

	@Override
	public List<MfsAccountTypeGroup> loadAll() {
		final StringBuilder hql = new StringBuilder();
		hql.append(" select matg");
		hql.append(" from MfsAccountTypeGroup matg");
		return list(hql.toString(), null);
	}
}
