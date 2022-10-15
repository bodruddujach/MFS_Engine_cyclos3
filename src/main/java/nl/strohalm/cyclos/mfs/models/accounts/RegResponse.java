package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import nl.strohalm.cyclos.mfs.models.transactions.Response;

import java.io.Serializable;

public class RegResponse extends Response implements Serializable {
  private String walletNo;
  private String walletStatus;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getWalletStatus() {
    return walletStatus;
  }

  public void setWalletStatus(String walletStatus) {
    this.walletStatus = walletStatus;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("walletNo", walletNo)
        .add("walletStatus", walletStatus)
        .add("status", status)
        .toString();
  }
}
