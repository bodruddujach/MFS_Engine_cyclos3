package nl.strohalm.cyclos.mfs.dao;


import java.util.List;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.entities.accounts.Account;
import nl.strohalm.cyclos.mfs.entities.MfsAccountGenericLimitConfig;
import nl.strohalm.cyclos.mfs.entities.MfsGenericLimitConfig;

public interface MfsAccountGenericLimitConfigDAO extends BaseDAO<MfsAccountGenericLimitConfig>, InsertableDAO<MfsAccountGenericLimitConfig>, UpdatableDAO<MfsAccountGenericLimitConfig>, DeletableDAO<MfsAccountGenericLimitConfig> {

	public List<MfsAccountGenericLimitConfig> getAllMfsAccountGenericLimitConfigsByAccount(Account account);

	public List<MfsAccountGenericLimitConfig> getMfsAccountGenericLimitConfigsByAccountAndStatus(Account account, boolean enabled);

	public MfsAccountGenericLimitConfig loadByAccountAndName(Account account, String name);

	public MfsAccountGenericLimitConfig loadByAccountAndNameAndEnabled(Account account, String name, boolean enabled);

}
