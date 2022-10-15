package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;

import java.util.List;


public interface MfsTxnTypeDao extends BaseDAO<MfsTxnType>, InsertableDAO<MfsTxnType>, UpdatableDAO<MfsTxnType>, DeletableDAO<MfsTxnType> {

  List<MfsTxnType> get();
  MfsTxnType findByName(String name);
  MfsTxnType findByTxnId(Long txnId);
}
