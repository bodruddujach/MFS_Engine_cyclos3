package nl.strohalm.cyclos.mfs.models.transactions;

import java.io.Serializable;
import java.util.List;

public class BulkTxnRequest implements Serializable {
  private List<TxnRequest> txnRequestList;

  public List<TxnRequest> getTxnRequestList() {
    return txnRequestList;
  }

  public void setTxnRequestList(List<TxnRequest> txnRequestList) {
    this.txnRequestList = txnRequestList;
  }
}
