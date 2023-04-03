package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.AccountStatus;
import nl.strohalm.cyclos.mfs.models.enums.AccountType;
import nl.strohalm.cyclos.webservices.model.RegistrationFieldValueVO;

import java.io.Serializable;
import java.util.List;

public class AcRegRequest implements Serializable {
  /**
   * 
  */
  private static final long serialVersionUID = 1L;
  private String walletNo;
  private String fullName;
  private String mobile;
  private String email;
  private String password;
  private String pin;
  private String gender;
  private AccountType accountType;
  private AccountStatus accountStatus;
  private String remoteAddress;
  private List<RegistrationFieldValueVO> fields;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public String getRemoteAddress() {
    return remoteAddress;
  }

  public void setRemoteAddress(String remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }

  public List<RegistrationFieldValueVO> getFields() {
    return fields;
  }

  public void setFields(List<RegistrationFieldValueVO> fields) {
    this.fields = fields;
  }

}