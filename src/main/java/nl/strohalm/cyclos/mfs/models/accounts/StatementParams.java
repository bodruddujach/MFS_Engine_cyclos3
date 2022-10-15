package nl.strohalm.cyclos.mfs.models.accounts;

import java.util.Calendar;
import java.util.List;

public class StatementParams {
  private Long accountNo;
  private Calendar beginDate;
  private Calendar endDate;
  private String  txnId;
  private List<String> txnTypes;
  private Boolean reverseOrder = false;
  private Integer currentPage = 0;
  private Integer pageSize = 200;

  public Long getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(Long accountNo) {
    this.accountNo = accountNo;
  }

  public Calendar getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(Calendar beginDate) {
    this.beginDate = beginDate;
  }

  public Calendar getEndDate() {
    return endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }

  public List<String> getTxnTypes() {
    return txnTypes;
  }

  public void setTxnTypes(List<String> txnTypes) {
    this.txnTypes = txnTypes;
  }

  public Boolean getReverseOrder() {
    return reverseOrder;
  }

  public void setReverseOrder(Boolean reverseOrder) {
    this.reverseOrder = reverseOrder;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
