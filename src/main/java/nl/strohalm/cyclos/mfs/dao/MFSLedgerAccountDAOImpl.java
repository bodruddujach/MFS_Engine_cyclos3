package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MFSLedgerAccount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MFSLedgerAccountDAOImpl extends BaseDAOImpl<MFSLedgerAccount> implements MFSLedgerAccountDAO {
  public MFSLedgerAccountDAOImpl() {
    super(MFSLedgerAccount.class);
  }

  public MFSLedgerAccount findByCode(String ledgerCode) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    namedParameters.put("code", ledgerCode);
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MFSLedgerAccount t");
    hql.append(" where t.code = :code");
    return uniqueResult(hql.toString(), namedParameters);
  }

  public List<MFSLedgerAccount> getAllLedgerAc() {
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MFSLedgerAccount t");
    return list(hql.toString(), null);
  }

  public List<MFSLedgerAccount> searchAccount(String ledgerCode) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    namedParameters.put("code", ledgerCode);
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MFSLedgerAccount t");
    hql.append(" where t.code like '%" + ledgerCode + "%'");
    return list(hql.toString(), namedParameters);
  }
}
