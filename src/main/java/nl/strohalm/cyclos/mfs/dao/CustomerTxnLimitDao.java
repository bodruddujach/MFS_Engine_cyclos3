package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.CustomerTxnLimit;

public interface CustomerTxnLimitDao extends BaseDAO<CustomerTxnLimit>, InsertableDAO<CustomerTxnLimit>, UpdatableDAO<CustomerTxnLimit>, DeletableDAO<CustomerTxnLimit> {
}
