package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.AccountStatus;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UpdateAcStatus implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String walletNo;
  @NotNull
  private AccountStatus walletStatus;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public AccountStatus getWalletStatus() {
    return walletStatus;
  }

  public void setWalletStatus(AccountStatus walletStatus) {
    this.walletStatus = walletStatus;
  }
}
