package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class UpdateAccountRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String walletNo;

  @NotNull
  private String name;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("walletNo", walletNo)
        .add("name", name)
        .toString();
  }
}