package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MfsTxnTypeDaoImpl extends BaseDAOImpl<MfsTxnType> implements MfsTxnTypeDao {

  public MfsTxnTypeDaoImpl() {
    super(MfsTxnType.class);
  }

  public List<MfsTxnType> get() {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MfsTxnType ");
    hql.append(" where 1=1");
    return list(hql.toString(), namedParameters);
  }

  public MfsTxnType findByName(String name) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    namedParameters.put("name", name);
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MfsTxnType t");
    hql.append(" where t.name = :name");
    return uniqueResult(hql.toString(), namedParameters);
  }

  public MfsTxnType findByTxnId(Long coreTxnTypeId){
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    namedParameters.put("coreTxnTypeId", coreTxnTypeId);
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MfsTxnType t");
    hql.append(" where t.coreTxnTypeId = :coreTxnTypeId");
    return uniqueResult(hql.toString(), namedParameters);
  }

}
