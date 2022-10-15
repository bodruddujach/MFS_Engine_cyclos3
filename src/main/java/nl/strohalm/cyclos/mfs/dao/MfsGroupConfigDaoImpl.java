package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.MfsGroupConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MfsGroupConfigDaoImpl extends BaseDAOImpl<MfsGroupConfig> implements MfsGroupConfigDao {

  public MfsGroupConfigDaoImpl() {
    super(MfsGroupConfig.class);
  }

  public List<MfsGroupConfig> get() {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MfsGroupConfig ");
    hql.append(" where 1=1");
    return list(hql.toString(), namedParameters);
  }

  public MfsGroupConfig findByName(String name){
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    namedParameters.put("name",name);
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from MfsGroupConfig t");
    hql.append(" where t.name = :name");
    return uniqueResult(hql.toString(), namedParameters);
  }
}
