package nl.strohalm.cyclos.mfs.dao;


import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.mfs.entities.MfsAccountTypeGroup;

public interface MfsAccountTypeGroupDao extends BaseDAO<MfsAccountTypeGroup>, InsertableDAO<MfsAccountTypeGroup>, UpdatableDAO<MfsAccountTypeGroup>, DeletableDAO<MfsAccountTypeGroup> {

	public void delete(final long accountTypeId, final long groupId);

	public MfsAccountTypeGroup load(final long accountTypeId, final long groupId, final Relationship... fetch);
}
