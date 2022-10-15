package nl.strohalm.cyclos.mfs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MfsUtil {
  private MfsUtil() {
  }

  private static boolean isWallet(String walletNo) {
    boolean isWallet = true;
    Pattern p = Pattern.compile("^01\\d{9}");
    Matcher m = p.matcher(walletNo);
    if (!m.matches()) {
      isWallet = false;
    }
    return isWallet;
  }
}
