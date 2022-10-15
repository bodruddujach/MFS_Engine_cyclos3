package nl.strohalm.cyclos.mfs.models.ledgers;

import java.util.Calendar;

public class LedgerStatementRequest {
  private String ledgerCode;
  private Calendar beginDate;
  private Calendar endDate;
  private String txnId;

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

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }
}
