package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;

import java.util.Calendar;


public class CustomerTxnLimit extends Entity {
  public enum LimitType {DAILY, MONTHLY}

  private String wallet;
  private String txnType;
  private String limitType; // DAILY,MONTHLY
  private Boolean active;
  private Calendar createDate;

  public String getWallet() {
    return wallet;
  }

  public void setWallet(String wallet) {
    this.wallet = wallet;
  }

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }

  public String getLimitType() {
    return limitType;
  }

  public void setLimitType(String limitType) {
    this.limitType = limitType;
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
    return wallet + "-" + "txnType" + "-" + limitType;
  }
}
