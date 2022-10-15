package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;

import java.util.Calendar;
import java.util.Date;

public class MfsTxnType extends Entity {
  private Long coreTxnTypeId;
  private Integer txnCode;
  private String name; // BANK_CASH_IN, CASH_OUT_TO_AGENT
  private String description;
  private Boolean active;
  private Calendar createDate;

  public Long getCoreTxnTypeId() {
    return coreTxnTypeId;
  }

  public void setCoreTxnTypeId(Long coreTxnTypeId) {
    this.coreTxnTypeId = coreTxnTypeId;
  }

  public Integer getTxnCode() {
    return txnCode;
  }

  public void setTxnCode(Integer txnCode) {
    this.txnCode = txnCode;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Calendar getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Calendar createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return name;
  }
}
