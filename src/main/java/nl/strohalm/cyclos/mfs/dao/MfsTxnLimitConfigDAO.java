package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig;

public interface MfsTxnLimitConfigDAO  extends BaseDAO<MfsTxnLimitConfig>, InsertableDAO<MfsTxnLimitConfig>, UpdatableDAO<MfsTxnLimitConfig>, DeletableDAO<MfsTxnLimitConfig> {
}
