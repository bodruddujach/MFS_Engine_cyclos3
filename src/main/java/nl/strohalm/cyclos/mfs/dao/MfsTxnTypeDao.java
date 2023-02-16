package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;

import java.util.List;


public interface MfsTxnTypeDao extends BaseDAO<MfsTxnType>, InsertableDAO<MfsTxnType>, UpdatableDAO<MfsTxnType>, DeletableDAO<MfsTxnType> {

  List<MfsTxnType> get();
  MfsTxnType findByName(String name);
  MfsTxnType findByTxnId(Long txnId);
  List<MfsTxnType> findByTypeTag(TxnTypeTag tag);
  MfsTxnType findByNameAndTypeTag(String name, TxnTypeTag tag);
}
