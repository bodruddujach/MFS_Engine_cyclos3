package nl.strohalm.cyclos.mfs.models.accounts;

public class ChangePinRequest {
  String walletNo;
  String oldPin;
  String newPin;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getOldPin() {
    return oldPin;
  }

  public void setOldPin(String oldPin) {
    this.oldPin = oldPin;
  }

  public String getNewPin() {
    return newPin;
  }

  public void setNewPin(String newPin) {
    this.newPin = newPin;
  }
}
