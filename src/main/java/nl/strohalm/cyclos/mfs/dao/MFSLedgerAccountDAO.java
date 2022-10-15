package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAO;
import nl.strohalm.cyclos.dao.DeletableDAO;
import nl.strohalm.cyclos.dao.InsertableDAO;
import nl.strohalm.cyclos.dao.UpdatableDAO;
import nl.strohalm.cyclos.mfs.entities.MFSLedgerAccount;

import java.util.List;

public interface MFSLedgerAccountDAO extends BaseDAO<MFSLedgerAccount>, InsertableDAO<MFSLedgerAccount>, UpdatableDAO<MFSLedgerAccount>, DeletableDAO<MFSLedgerAccount> {
  MFSLedgerAccount findByCode(String ledgerCode);

  List<MFSLedgerAccount> getAllLedgerAc();

  List<MFSLedgerAccount> searchAccount(String ledgerCode);
}
