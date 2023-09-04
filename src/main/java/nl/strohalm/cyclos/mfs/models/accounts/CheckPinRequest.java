package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CheckPinRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  public CheckPinRequest() {
  }

  public CheckPinRequest(String walletNo, String pin) {
    this.walletNo = walletNo;
    this.pin = pin;
  }

  @NotNull
  @Size(min = 11, max = 11, message = "Invalid wallet number")
  private String walletNo;

  @NotNull
  @Size(min = 6, max = 6, message = "PIN length should be minimum 6 digits")
  @Digits(integer = 10, fraction = 0, message = "PIN should be numeric only")
  private String pin;

  private String remoteAddress;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getRemoteAddress() {
    return remoteAddress;
  }

  public void setRemoteAddress(String remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("walletNo", walletNo)
        .add("remoteAddress", remoteAddress)
        .toString();
  }
}