package nl.strohalm.cyclos.mfs.models.accounts;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePinRequest {

  @NotNull
  @Size(min = 11, max = 11, message = "Invalid wallet number")
  String walletNo;
 
  @Size(min = 6, max = 6, message = "PIN length should be minimum 6 digits")
  @Digits(integer = 10, fraction = 0, message = "PIN should be numeric only")
  String oldPin;
  
  @Size(min = 6, max = 6, message = "PIN length should be minimum 6 digits")
  @Digits(integer = 10, fraction = 0, message = "PIN should be numeric only")
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
