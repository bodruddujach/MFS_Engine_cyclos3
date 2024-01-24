package nl.strohalm.cyclos.mfs.models.transactions;

import com.google.common.base.MoreObjects;

import nl.strohalm.cyclos.mfs.entities.MfsTxnType.TxnTypeTag;
import nl.strohalm.cyclos.webservices.model.FieldValueVO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnRequest implements Serializable {
  private static final long serialVersionUID = 1213217428686438208L;

  @NotNull
  private String fromAc;

  @NotNull
  private String toAc;
  private String byAc;
  private String fromType;
  private String toType;
  private String fromAcAlias;
  private String toAcAlias;
  private String pin;
  private String password;

  @NotNull
  private BigDecimal amount;

  @NotNull
  private String txnType;

  private String requestId; // from client side
  private String userType;
  private String invoiceNo; // txn specific
  private String tokenNo;
  private String channel;
  private String maker;
  private String checker;
  private String customerRefId; //customer specific
  private BigDecimal rate;
  private BigDecimal minRate;
  private BigDecimal maxRate;
  private Boolean feeFromCustomer;
  private String externalCustomer;
  private String note;
  private String txnTag;
  private String parentRequestId;
  private TxnTypeTag txnTypeTag;
  private String traceData;
  private boolean scheduledPayment;
  private List<FieldValueVO> fields;

  public String getFromAc() {
    return fromAc;
  }

  public void setFromAc(String fromAc) {
    this.fromAc = fromAc;
  }

  public String getToAc() {
    return toAc;
  }

  public void setToAc(String toAc) {
    this.toAc = toAc;
  }

  public String getByAc() {
    return byAc;
  }

  public String getFromType() {
    return fromType;
  }

  public void setFromType(String fromType) {
    this.fromType = fromType;
  }

  public String getToType() {
    return toType;
  }

  public void setToType(String toType) {
    this.toType = toType;
  }

  public void setByAc(String byAc) {
    this.byAc = byAc;
  }

  public String getFromAcAlias() {
    return fromAcAlias;
  }

  public void setFromAcAlias(String fromAcAlias) {
    this.fromAcAlias = fromAcAlias;
  }

  public String getToAcAlias() {
    return toAcAlias;
  }

  public void setToAcAlias(String toAcAlias) {
    this.toAcAlias = toAcAlias;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public String getTokenNo() {
    return tokenNo;
  }

  public void setTokenNo(String tokenNo) {
    this.tokenNo = tokenNo;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getMaker() {
    return maker;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public String getChecker() {
    return checker;
  }

  public void setChecker(String checker) {
    this.checker = checker;
  }

  public String getCustomerRefId() {
    return customerRefId;
  }

  public void setCustomerRefId(String customerRefId) {
    this.customerRefId = customerRefId;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public BigDecimal getMinRate() {
    return minRate;
  }

  public void setMinRate(BigDecimal minRate) {
    this.minRate = minRate;
  }

  public BigDecimal getMaxRate() {
    return maxRate;
  }

  public void setMaxRate(BigDecimal maxRate) {
    this.maxRate = maxRate;
  }

  public Boolean getFeeFromCustomer() {
    return feeFromCustomer;
  }

  public void setFeeFromCustomer(Boolean feeFromCustomer) {
    this.feeFromCustomer = feeFromCustomer;
  }

  public String getExternalCustomer() {
    return externalCustomer;
  }

  public void setExternalCustomer(String externalCustomer) {
    this.externalCustomer = externalCustomer;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getTxnTag() {
    return txnTag;
  }

  public void setTxnTag(String txnTag) {
    this.txnTag = txnTag;
  }

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }



  public String getParentRequestId() {
    return parentRequestId;
  }

  public void setParentRequestId(String parentRequestId) {
    this.parentRequestId = parentRequestId;
  }

  

  public TxnTypeTag getTxnTypeTag() {
    return txnTypeTag;
  }

  public void setTxnTypeTag(TxnTypeTag txnTypeTag) {
    this.txnTypeTag = txnTypeTag;
  }
  
  public String getTraceData() {
    return traceData;
  }

  public void setTraceData(String traceData) {
    this.traceData = traceData;
  }

  public boolean isScheduledPayment() {
    return scheduledPayment;
  }

  public void setScheduledPayment(boolean scheduledPayment) {
    this.scheduledPayment = scheduledPayment;
  }

  public List<FieldValueVO> getFields() {
    return fields;
  }

  public void setFields(List<FieldValueVO> fields) {
    this.fields = fields;
  }

@Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("fromAc", fromAc)
        .add("toAc", toAc)
        .add("amount", amount)
        .add("txnType", txnType)
        .add("parentRequestId", parentRequestId)
        .toString();
  }
}
