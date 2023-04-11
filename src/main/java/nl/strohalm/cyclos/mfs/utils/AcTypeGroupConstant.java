package nl.strohalm.cyclos.mfs.utils;

import nl.strohalm.cyclos.mfs.models.enums.AccountType;

import java.util.HashMap;

public class AcTypeGroupConstant {
  public static  HashMap<AccountType,Long> acTypeGroups = new HashMap();

  static {
    acTypeGroups.put(AccountType.CUSTOMER,22L);
    acTypeGroups.put(AccountType.MERCHANT,61L);
    acTypeGroups.put(AccountType.AGENT,25L);
    acTypeGroups.put(AccountType.DISTRIBUTOR,43L);
    acTypeGroups.put(AccountType.DO,63L);
  }
}
