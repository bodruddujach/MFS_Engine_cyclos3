package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.TransactionType;

import java.util.Calendar;
import java.util.List;

public class WalletStatementRequest {
  private String walletNo;
  private String ledgerCode;
  private Calendar beginDate;
  private Calendar endDate;
  private String txnId;
  private Boolean reverseOrder = false;
  private Integer currentPage = 0;
  private Integer pageSize;
  private List<TransactionType> txnTypes;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getLedgerCode() {
    return ledgerCode;
  }

  public void setLedgerCode(String ledgerCode) {
    this.ledgerCode = ledgerCode;
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

  public List<TransactionType> getTxnTypes() {
    return txnTypes;
  }

  public void setTxnTypes(List<TransactionType> txnTypes) {
    this.txnTypes = txnTypes;
  }

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }

  @Override
  public String toString() {
    return "WalletStatementRequest{" +
        "walletNo='" + walletNo + '\'' +
        ", beginDate=" + beginDate +
        ", endDate=" + endDate +
        ", reverseOrder=" + reverseOrder +
        ", currentPage=" + currentPage +
        ", pageSize=" + pageSize +
        ", txnTypes=" + txnTypes +
        '}';
  }
}
