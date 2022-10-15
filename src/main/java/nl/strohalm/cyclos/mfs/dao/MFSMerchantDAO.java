package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MFSMerchant;

public interface MFSMerchantDAO extends BaseDAO<MFSMerchant>, InsertableDAO<MFSMerchant>, UpdatableDAO<MFSMerchant>, DeletableDAO<MFSMerchant> {
}
