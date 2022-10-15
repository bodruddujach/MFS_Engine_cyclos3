package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WalletStatementDetail implements Serializable {

  public enum TxnActionType {
    DEBIT,CREDIT
  }
  private static final long serialVersionUID = 1L;
  private Long id;
  private Integer parentId;
  private BigDecimal amount;
  private String fromWallet;
  private String fromName;
  private String toWallet;
  private String toName;
  private Date txnTime;
  private Integer typeId;
  private Date stmtDate;
  private String transactionNumber;
  private String parentTransactionNumber;
  private String typeName;
  private Integer chargeBankOfId ;
  private Integer chargeBackById ;
  private String description;
  private String txnType;
  private String invoiceNo;
  private String customerRefId;
  private String note;
  private BigDecimal runningBalance;
  private TxnActionType actionType;
  private Integer fromAcId;
  private Integer toAcId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getFromWallet() {
    return fromWallet;
  }

  public void setFromWallet(String fromWallet) {
    this.fromWallet = fromWallet;
  }

  public String getToWallet() {
    return toWallet;
  }

  public void setToWallet(String toWallet) {
    this.toWallet = toWallet;
  }

  public Date getTxnTime() {
    return txnTime;
  }

  public void setTxnTime(Date txnTime) {
    this.txnTime = txnTime;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Date getStmtDate() {
    return stmtDate;
  }

  public void setStmtDate(Date stmtDate) {
    this.stmtDate = stmtDate;
  }

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public String getParentTransactionNumber() {
    return parentTransactionNumber;
  }

  public void setParentTransactionNumber(String parentTransactionNumber) {
    this.parentTransactionNumber = parentTransactionNumber;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Integer getChargeBankOfId() {
    return chargeBankOfId;
  }

  public void setChargeBankOfId(Integer chargeBankOfId) {
    this.chargeBankOfId = chargeBankOfId;
  }

  public Integer getChargeBackById() {
    return chargeBackById;
  }

  public void setChargeBackById(Integer chargeBackById) {
    this.chargeBackById = chargeBackById;
  }

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }

  public String getToName() {
    return toName;
  }

  public void setToName(String toName) {
    this.toName = toName;
  }

  public String getFromName() {
    return fromName;
  }

  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public TxnActionType getActionType() {
    return actionType;
  }

  public void setActionType(TxnActionType actionType) {
    this.actionType = actionType;
  }

  public BigDecimal getRunningBalance() {
    return runningBalance;
  }

  public void setRunningBalance(BigDecimal runningBalance) {
    this.runningBalance = runningBalance;
  }

  public Integer getFromAcId() {
    return fromAcId;
  }

  public void setFromAcId(Integer fromAcId) {
    this.fromAcId = fromAcId;
  }

  public Integer getToAcId() {
    return toAcId;
  }

  public void setToAcId(Integer toAcId) {
    this.toAcId = toAcId;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("amount", amount)
        .add("fromWallet", fromWallet)
        .add("fromName", fromName)
        .add("toWallet", toWallet)
        .add("toName", toName)
        .add("txnTime", txnTime)
        .add("typeId", typeId)
        .add("stmtDate", stmtDate)
        .add("transactionNumber", transactionNumber)
        .add("parentTransactionNumber", parentTransactionNumber)
        .add("typeName", typeName)
        .add("description", description)
        .add("txnType", txnType)
        .add("invoiceNo", invoiceNo)
        .add("customerRefId", customerRefId)
        .add("note", note)
        .toString();
  }
}