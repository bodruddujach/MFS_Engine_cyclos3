package nl.strohalm.cyclos.mfs.dao;

import nl.strohalm.cyclos.dao.BaseDAOImpl;
import nl.strohalm.cyclos.mfs.entities.CustomerTxnLimit;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CustomerTxnLimitDaoImpl extends BaseDAOImpl<CustomerTxnLimit> implements CustomerTxnLimitDao {
  public CustomerTxnLimitDaoImpl() {
    super(CustomerTxnLimit.class);
  }

  public List<CustomerTxnLimit> findWalletLimitObject(Calendar createDate, String txnType, String limitType, String wallet) {
    final Map<String, Object> namedParameters = new HashMap<String, Object>();
    final StringBuilder hql = new StringBuilder();
    hql.append(" select t");
    hql.append(" from CustomerTxnLimit ");
    hql.append("where 1=1");
    if (createDate != null) {
      hql.append(" and t.createDate >= :createDate");
      namedParameters.put("createDate", createDate);
    }
    if (StringUtils.isNotEmpty(wallet)) {
      hql.append(" and t.wallet >= :wallet");
      namedParameters.put("wallet", wallet);
    }
    if (StringUtils.isNotEmpty(txnType)) {
      hql.append(" and t.txnType = :txnType");
      namedParameters.put("wallet", wallet);
    }
    if (StringUtils.isNotEmpty(limitType)) {
      hql.append(" and t.limitType = :limitType");
      namedParameters.put("limitType", limitType);
    }
    return list(hql.toString(), namedParameters);
  }
}
