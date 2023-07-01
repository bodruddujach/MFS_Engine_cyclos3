package nl.strohalm.cyclos.mfs.utils;

import nl.strohalm.cyclos.entities.accounts.MemberAccount;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

public class MfsConstant {
  private MfsConstant(){};
  public static final String CUSTOMER = "CUSTOMER";
  public static final String AGENT = "AGENT";
  public static final String SYSTEM_ACCOUNT = "SYSTEM";
  public static final String CHANNEL = "rest";
  public static final String PRINCIPAL_TYPE = "user";
  public static final String STATUS_SUCCESS = "SUCCESS";
  public static final String STATUS_PROCESSED = "PROCESSED";
  public static final String STATUS_FAILED = "FAILED";
  public static final BigDecimal MFS_MAX_TXN_AMOUNT= BigDecimal.valueOf(1000000);
  public static final BigDecimal MFS_MIN_TXN_AMOUNT = BigDecimal.valueOf(0.0001);
  public static final List<MemberAccount.Status> DEBIT_ALLOWED_STATUS = asList(MemberAccount.Status.ACTIVE);
  public static final List<MemberAccount.Status> TXN_BLOCK_STATUS = asList(MemberAccount.Status.SUSPENDED, MemberAccount.Status.CLOSED, MemberAccount.Status.INACTIVE, MemberAccount.Status.PENDING, MemberAccount.Status.REMOVED);
}
