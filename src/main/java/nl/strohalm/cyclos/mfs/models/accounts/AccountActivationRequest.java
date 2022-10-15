package nl.strohalm.cyclos.mfs.models.accounts;

import java.io.Serializable;

public class AccountActivationRequest implements Serializable {
  private String maker;
  private String checker;
  private String walletNo;


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

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }


}