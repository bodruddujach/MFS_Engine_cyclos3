package nl.strohalm.cyclos.mfs.dao;


import java.util.List;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;

public interface MfsGenericLimitConfigDAO extends BaseDAO<MfsGenericLimitConfig>, InsertableDAO<MfsGenericLimitConfig>, UpdatableDAO<MfsGenericLimitConfig>, DeletableDAO<MfsGenericLimitConfig> {

	public List<MfsGenericLimitConfig> getAllMfsGenericLimitConfigs();

	public List<MfsGenericLimitConfig> getMfsGLienericmitConfigsByStatus(boolean enabled);

	public MfsGenericLimitConfig loadByName(String name);

	public MfsGenericLimitConfig loadByNameAndEnabled(String name, boolean enabled);
}
