package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType;
import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;
import nl.strohalm.cyclos.utils.hibernate.HibernateHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MfsTxnTypeDaoImpl extends BaseDAOImpl<MfsTxnType> implements MfsTxnTypeDao {

  public MfsTxnTypeDaoImpl() {
    super(MfsTxnType.class);
  }

  public List<MfsTxnType> get() {
    final StringBuilder hql = new StringBuilder();
    hql.append(" select mtt");
    hql.append(" from MfsTxnType mtt");
    return list(hql.toString(), null);
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

  @Override
  public List<MfsTxnType> findByTypeTag(TxnTypeTag typeTag) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mtt");
    HibernateHelper.addParameterToQuery(hql, namedParameters, "typeTag", typeTag);
    return list(hql.toString(), namedParameters);
  }

  @Override
  public MfsTxnType findByNameAndTypeTag(String name, TxnTypeTag typeTag) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    final StringBuilder hql = HibernateHelper.getInitialQuery(getEntityType(), "mtt");
    HibernateHelper.addParameterToQuery(hql, namedParameters, "name", name);
    HibernateHelper.addParameterToQuery(hql, namedParameters, "typeTag", typeTag);
    return uniqueResult(hql.toString(), namedParameters);
  }

}
