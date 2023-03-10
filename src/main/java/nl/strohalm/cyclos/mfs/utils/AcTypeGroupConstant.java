package nl.strohalm.cyclos.mfs.utils;

import nl.strohalm.cyclos.mfs.models.enums.AccountType;

import java.util.HashMap;

public class AcTypeGroupConstant {
  public static  HashMap<AccountType,Long> acTypeGroups = new HashMap();

  static {
    acTypeGroups.put(AccountType.CUSTOMER,5L);
    acTypeGroups.put(AccountType.MERCHANT,12L);
    acTypeGroups.put(AccountType.AGENT,13L);
    acTypeGroups.put(AccountType.DISTRIBUTOR,14L);
    acTypeGroups.put(AccountType.DO,15L);
  }
}
