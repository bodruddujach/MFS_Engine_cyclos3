package nl.strohalm.cyclos.mfs.models.transactions;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TxnResponse extends Response implements Serializable {

  private static final long serialVersionUID = 5522207428686438288L;

  private String txnId;
  private BigDecimal amount = BigDecimal.valueOf(-1);
  private BigDecimal fee = BigDecimal.valueOf(-1);
  private BigDecimal commission = BigDecimal.valueOf(-1);
  private BigDecimal balanceFrom = BigDecimal.valueOf(-1);
  private BigDecimal balanceTo = BigDecimal.valueOf(-1);
  private String ticket = "N/A";
  private String traceNo = "N/A";

  private String fromAccount;
  private String toAccount;
  private String fromAccountAlias;
  private String toAccountAlias;
  private String externalCustomer;
  private Date txnTime;

  private String fromAccountName;
  private String toAccountName;
  private String txnType;
  private String txnTypeName;
  private String invoiceNo;
  private String customerRefId;
  private String note;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public BigDecimal getCommission() {
    return commission;
  }

  public void setCommission(BigDecimal commission) {
    this.commission = commission;
  }

  public BigDecimal getBalanceFrom() {
    return balanceFrom;
  }

  public void setBalanceFrom(BigDecimal balanceFrom) {
    this.balanceFrom = balanceFrom;
  }

  public BigDecimal getBalanceTo() {
    return balanceTo;
  }

  public void setBalanceTo(BigDecimal balanceTo) {
    this.balanceTo = balanceTo;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getTraceNo() {
    return traceNo;
  }

  public void setTraceNo(String traceNo) {
    this.traceNo = traceNo;
  }

  public String getFromAccount() {
    return fromAccount;
  }

  public void setFromAccount(String fromAccount) {
    this.fromAccount = fromAccount;
  }

  public String getToAccount() {
    return toAccount;
  }

  public void setToAccount(String toAccount) {
    this.toAccount = toAccount;
  }

  public String getFromAccountAlias() {
    return fromAccountAlias;
  }

  public void setFromAccountAlias(String fromAccountAlias) {
    this.fromAccountAlias = fromAccountAlias;
  }

  public String getToAccountAlias() {
    return toAccountAlias;
  }

  public void setToAccountAlias(String toAccountAlias) {
    this.toAccountAlias = toAccountAlias;
  }

  public String getExternalCustomer() {
    return externalCustomer;
  }

  public void setExternalCustomer(String externalCustomer) {
    this.externalCustomer = externalCustomer;
  }

  public Date getTxnTime() {
    return txnTime;
  }

  public void setTxnTime(Date txnTime) {
    this.txnTime = txnTime;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getFromAccountName() {
    return fromAccountName;
  }

  public void setFromAccountName(String fromAccountName) {
    this.fromAccountName = fromAccountName;
  }

  public String getToAccountName() {
    return toAccountName;
  }

  public void setToAccountName(String toAccountName) {
    this.toAccountName = toAccountName;
  }

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getCustomerRefId() {
    return customerRefId;
  }

  public void setCustomerRefId(String customerRefId) {
    this.customerRefId = customerRefId;
  }

  public String getTxnTypeName() {
    return txnTypeName;
  }

  public void setTxnTypeName(String txnTypeName) {
    this.txnTypeName = txnTypeName;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }


  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("amount", amount)
        .add("fromAccount", fromAccount)
        .add("toAccount", toAccount)
        .add("txnTime", txnTime)
        .add("fromAccountName", fromAccountName)
        .add("toAccountName", toAccountName)
        .add("txnType", txnType)
        .add("txnTypeName", txnTypeName)
        .add("invoiceNo", invoiceNo)
        .add("note", note)
        .add("status", status)
        .toString();
  }
}