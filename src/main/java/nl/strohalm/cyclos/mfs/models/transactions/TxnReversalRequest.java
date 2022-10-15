package nl.strohalm.cyclos.mfs.models.transactions;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class TxnReversalRequest implements Serializable {
  private static final long serialVersionUID = 101505023451L;

  @NotNull
  @Size(min = 1, max = 100)
  private String txnId;

  @NotNull
  @Size(min = 6, max = 11)
  private String fromAc;

  @NotNull
  @Size(min = 6, max = 11)
  private String toAc;

  @NotNull
  @DecimalMin(value = "0", message = "Amount should be positive")
  private BigDecimal amount;

  @Size(min = 4, max = 4)
  @Digits(integer = 10, fraction = 0, message = "PIN should be numeric only")
  private String pin;

  private boolean fingerPrintTxn = false;
  private byte[] fpSignature;

  @Size(max = 40)
  private String requestId;

  @Size(max = 250)
  private String note;

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }

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

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public boolean isFingerPrintTxn() {
    return fingerPrintTxn;
  }

  public void setFingerPrintTxn(boolean fingerPrintTxn) {
    this.fingerPrintTxn = fingerPrintTxn;
  }

  public byte[] getFpSignature() {
    return fpSignature;
  }

  public void setFpSignature(byte[] fpSignature) {
    this.fpSignature = fpSignature;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
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
      .add("txnId", txnId)
      .add("fromAc", fromAc)
      .add("toAc", toAc)
      .add("amount", amount)
      .add("requestId", requestId)
      .toString();
  }
}
