package nl.strohalm.cyclos.mfs.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ErrorConstants {

  private ErrorConstants(){}

  // error code
  public static final String INVALID_PIN = "4001";
  public static final String INVALID_ACCOUNT = "4002";

  public static final String ACCOUNT_NOT_ACTIVE = "4003";
  public static final String ACCOUNT_NOT_FOUND = "4004";
  public static final String TXN_TYPE_NOT_FOUND = "4005";
  public static final String NOT_ENOUGH_BALANCE = "4006";
  public static final String INVALID_AC_TYPE = "4007";
  public static final String INVALID_INPUT="4008";

  public static final String INVALID_AMOUNT = "4009";
  public static final String INVALID_TXN_TYPE = "4010";
  public static final String FROM_AC_NOT_FOUND = "4011";
  public static final String TO_AC_NOT_FOUND = "4012";

  public static final String FROM_AC_IS_NOT_ACTIVE = "4013";
  public static final String TO_AC_IS_SUSPENDED_OR_CLOSE = "4014";
  public static final String TXN_TYPE_IS_NOT_ENABLE = "4015";
  public static final String MINIMUM_AMOUNT_EXCEEDED = "4016";
  public static final String TRANSACTION_NOT_FOUND = "4017";
  public static final String FROM_AC_STATUS_NOT_VALID = "4018";
  public static final String AC_STATUS_NOT_VALID = "4019";
  public static final String BLOCKED_PIN = "4020";
  public static final String INVALID_AC_STATUS = "4021";
  public static final String PIN_ALREADY_USED = "4022";
  public static final String AMOUNT_NOT_MATCHED = "4023";
  public static final String STATEMENT_NOT_SUPPORTED = "4024";
  public static final String TXN_ALREADY_REVERSE = "4025";
  public static final String MIN_AMOUNT_PER_TXN_NOT_MET = "4026";
  public static final String MAX_AMOUNT_PER_TXN_EXCEEDED = "4027";
  public static final String MAX_NUMBER_OF_TXN_PER_DAY_EXCEEDED = "4028";
  public static final String MAX_AMOUNT_PER_DAY_EXCEEDED = "4020";
  public static final String MAX_NUMBER_OF_TXN_PER_MONTH_EXCEEDED = "4030";
  public static final String MAX_AMOUNT_PER_MONTH_EXCEEDED = "4031";
  public static final String TXN_LIMIT_CONFIGURATION_EXISTS = "4032";
  public static final String BY_AC_NOT_FOUND = "4033";



  public static final String INTERNAL_ERROR= "5001";

  // Error mapping
  public static final Map<String, String> ERROR_MAP = new HashMap<String, String>();

  static {
    ERROR_MAP.put(INVALID_ACCOUNT, "Invalid Account");
    ERROR_MAP.put(ACCOUNT_NOT_ACTIVE, "Account is not Active");
    ERROR_MAP.put(ACCOUNT_NOT_FOUND, "Account Not Found");
    ERROR_MAP.put(INVALID_AC_TYPE, "Invalid AC Type");
    ERROR_MAP.put(INVALID_INPUT, "Invitation Input Data");
    ERROR_MAP.put(INVALID_AMOUNT, "Debit And Credit Amount is not equal");
    ERROR_MAP.put(NOT_ENOUGH_BALANCE, "NOT_ENOUGH_BALANCE");
    ERROR_MAP.put(INVALID_TXN_TYPE, "INVALID_TXN_TYPE");
    ERROR_MAP.put(FROM_AC_NOT_FOUND, "FROM_AC_NOT_FOUND");
    ERROR_MAP.put(TO_AC_NOT_FOUND, "TO_AC_NOT_FOUND");
    ERROR_MAP.put(BY_AC_NOT_FOUND, "BY_AC_NOT_FOUND");
    ERROR_MAP.put(TXN_TYPE_NOT_FOUND, "TXN_TYPE_NOT_FOUND");
    ERROR_MAP.put(FROM_AC_IS_NOT_ACTIVE, "FROM_AC_IS_NOT_ACTIVE");
    ERROR_MAP.put(TO_AC_IS_SUSPENDED_OR_CLOSE, "TO_AC_IS_SUSPENDED_OR_CLOSE");
    ERROR_MAP.put(TXN_TYPE_IS_NOT_ENABLE, "TXN_TYPE_IS_NOT_ENABLE");
    ERROR_MAP.put(MINIMUM_AMOUNT_EXCEEDED, "MINIMUM_AMOUNT_EXCEEDED");
    ERROR_MAP.put(TRANSACTION_NOT_FOUND, "TRANSACTION_DETAIL_NOT_FOUND: ID %s");
    ERROR_MAP.put(INVALID_PIN, "INVALID_PIN");
    ERROR_MAP.put(FROM_AC_STATUS_NOT_VALID, "FROM_AC_STATUS_NOT_VALID");
    ERROR_MAP.put(AC_STATUS_NOT_VALID, "AC_STATUS_NOT_VALID");
    ERROR_MAP.put(BLOCKED_PIN, "PIN is blocked");
    ERROR_MAP.put(INTERNAL_ERROR, "Internal server error");
    ERROR_MAP.put(INVALID_AC_STATUS, "Invalid Ac Status");
    ERROR_MAP.put(PIN_ALREADY_USED, "Pin already used");
    ERROR_MAP.put(AMOUNT_NOT_MATCHED, "Amount is not matched with original transaction");
    ERROR_MAP.put(STATEMENT_NOT_SUPPORTED, "Statement not supported");
    ERROR_MAP.put(TXN_ALREADY_REVERSE, "Txn Already Reversed");
    ERROR_MAP.put(MIN_AMOUNT_PER_TXN_NOT_MET, "TRANSACTION_MINIMUM_AMOUNT_IS_NOT_MET");
    ERROR_MAP.put(MAX_AMOUNT_PER_TXN_EXCEEDED, "TANSACTION_MAX_AMOUNT_LIMIT_EXCEEDED");
    ERROR_MAP.put(MAX_NUMBER_OF_TXN_PER_DAY_EXCEEDED, "MAX_NUMBER_OF_TRANSACTION_PER_DAY_EXCEEDED");
    ERROR_MAP.put(MAX_AMOUNT_PER_DAY_EXCEEDED, "MAX_AMOUNT_PER_DAY_LIMIT_EXCEEDED");
    ERROR_MAP.put(MAX_NUMBER_OF_TXN_PER_MONTH_EXCEEDED, "MAX_NUMBER_OF_TRANSACTION_PER_MONTH_EXCEEDED");
    ERROR_MAP.put(MAX_AMOUNT_PER_MONTH_EXCEEDED, "MAX_AMOUNT_PER_MONTH_LIMIT_EXCEEDED");
    ERROR_MAP.put(TXN_LIMIT_CONFIGURATION_EXISTS, "Transaction Limit Configuration Already Exists ");
  }

}
