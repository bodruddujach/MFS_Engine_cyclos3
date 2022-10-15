package nl.strohalm.cyclos.mfs.utils;

import nl.strohalm.cyclos.mfs.models.enums.TransactionType;

import java.util.HashMap;

public class TxnTypeConstant {
  public static HashMap<TransactionType,Long> txnTypeMap = new HashMap();
  public static HashMap<Long,TransactionType> txnTypeMapKey = new HashMap();

  static {
    txnTypeMap.put(TransactionType.BANK_CASHIN,31L);
    txnTypeMap.put(TransactionType.BANK_CASHOUT,46L);
    txnTypeMap.put(TransactionType.SEND_MONEY,36L);
    txnTypeMap.put(TransactionType.TOPUP_AGENT,32L);
    txnTypeMap.put(TransactionType.CASHOUT_AGENT_DISTRIBUTOR,33L);
    txnTypeMap.put(TransactionType.CASH_IN_FROM_MFS_AGENT,34L);
    txnTypeMap.put(TransactionType.PAYMENT,40L);
    txnTypeMap.put(TransactionType.BILL_PAYMENT,41L);
    txnTypeMap.put(TransactionType.CASH_OUT_TO_MFS_AGENT,45L);
    txnTypeMap.put(TransactionType.MOBILE_RECHARGE,47L);
  }

  static {
    txnTypeMapKey.put(31L,TransactionType.BANK_CASHIN);
    txnTypeMapKey.put(46L,TransactionType.BANK_CASHOUT);
    txnTypeMapKey.put(35L,TransactionType.SEND_MONEY);
    txnTypeMapKey.put(32L,TransactionType.TOPUP_AGENT);
    txnTypeMapKey.put(33L,TransactionType.CASHOUT_AGENT_DISTRIBUTOR);
    txnTypeMapKey.put(34L,TransactionType.CASH_IN_FROM_MFS_AGENT);
    txnTypeMapKey.put(40L,TransactionType.PAYMENT);
    txnTypeMapKey.put(41L,TransactionType.BILL_PAYMENT);
    txnTypeMapKey.put(45L,TransactionType.CASH_OUT_TO_MFS_AGENT);
    txnTypeMapKey.put(47L,TransactionType.MOBILE_RECHARGE);
  }
}
