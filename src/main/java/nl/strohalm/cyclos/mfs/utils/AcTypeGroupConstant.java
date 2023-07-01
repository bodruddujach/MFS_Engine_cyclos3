package nl.strohalm.cyclos.mfs.utils;

import nl.strohalm.cyclos.mfs.models.enums.AccountType;

import java.util.HashMap;

public class AcTypeGroupConstant {
  public static  HashMap<AccountType,Long> acTypeGroups = new HashMap();

  static {
    acTypeGroups.put(AccountType.CUSTOMER,45L);
    acTypeGroups.put(AccountType.MERCHANT,61L);
    acTypeGroups.put(AccountType.AGENT,44L);
    acTypeGroups.put(AccountType.DISTRIBUTOR,42L);
    acTypeGroups.put(AccountType.DO,43L);
  }
}
