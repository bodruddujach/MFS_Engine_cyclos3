package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import nl.strohalm.cyclos.mfs.models.transactions.Response;

import java.math.BigDecimal;

public class BalanceResponse extends Response {
  private BigDecimal balance;
  private BigDecimal availableBalance;

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public BigDecimal getAvailableBalance() {
    return availableBalance;
  }

  public void setAvailableBalance(BigDecimal availableBalance) {
    this.availableBalance = availableBalance;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("status", status)
        .add("description", message)
        .add("balance", balance)
        .add("availableBalance", availableBalance)
        .toString();
  }
}
