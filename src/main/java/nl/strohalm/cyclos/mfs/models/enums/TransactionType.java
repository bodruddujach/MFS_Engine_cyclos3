package nl.strohalm.cyclos.mfs.models.enums;

public enum TransactionType {
  BANK_CASHIN("Bank Cash-In for Distributor"),
  BANK_CASHOUT("Bank Cash-Out for Distributor"),
  BANK_CASHIN_MERCHANT("Bank Cash-In for Merchant"),
  BANK_CASHOUT_MERCHANT("Bank Cash-In for Merchant"),
  TOPUP_AGENT("TopUp Distributor to Agent"),
  CASHOUT_AGENT_DISTRIBUTOR("CashOut Agent to Distributor"),
  CASH_IN_FROM_MFS_AGENT("Cash-in From MFS Agent"),
  CASH_IN_FROM_BANK("Bank Cash-in"),
  CASH_OUT_TO_BANK("Bank Cash-out"),
  CASH_OUT_TO_MFS_AGENT("Cash-out To MFS Agent"),
  CASHBACK("Cashback"),
  PAYMENT("Payment"),
  BILL_PAYMENT("bill payment to utility merchant"),
  AGENT_ASSISTED_PAYMENT("Agent assisted Bill payment"),
  SEND_MONEY("Send Money"),
  MOBILE_RECHARGE("Mobile Recharge"),
  MERCHANT_TO_MERCHANT("Merchant To Merchant Payment"),
  REVERSE("Reverse Txn"),
  ADD_MONEY("Add Money From Bank"),
  ADD_MONEY_SSL("Add Money From SSL"),
  UTILITY_BILL_PAYMENT_WASA_SSL("Wasa Bill Payment Via SSL"),
  PAY_DISTRIBUTOR_COMMISSION("Pay Distributor Commission"),
  DISTRIBUTOR_COMMISSION_CASHOUT("Pay Distributor Commission for Cash Out"),
  DISTRIBUTOR_COMMISSION_CASHIN("Pay Distributor Commission for Cash In"),
  DISTRIBUTOR_COMMISSION_AAP("Pay Distributor Commission for Agent Assisted Payment"),
  DISTRIBUTOR_COMMISSION_AC_OPENING("Pay Distributor Commission for Account Opening"),
  SETTLEMENT_ADD_MONEY("Settlement Add Money From Bank"),
  SETTLEMENT_ADD_MONEY_SSL("Settlement Add Money From Bank Ac And Cards");


  private String description;

  TransactionType(String txnTypeName) {
    this.description = txnTypeName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
