package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.AccountStatus;
import nl.strohalm.cyclos.mfs.models.enums.AccountType;
import nl.strohalm.cyclos.mfs.models.transactions.Response;

public class WalletInfoResponse extends Response {
  private String walletNo;
  private String name;
  private AccountType userType;
  private AccountStatus accountStatus;

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

  public AccountType getUserType() {
    return userType;
  }

  public void setUserType(AccountType userType) {
    this.userType = userType;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }
}
