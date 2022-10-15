package nl.strohalm.cyclos.mfs.models.transactions;

public class TxnReversalResponse extends Response {
  private String txnId;

  public String getTxnId() {
    return txnId;
  }

  public void setTxnId(String txnId) {
    this.txnId = txnId;
  }
}
