package nl.strohalm.cyclos.mfs.models.transactions;

import java.util.HashSet;
import java.util.Set;

public class WalletLimitResponse {

	String walletNo;
	Set<WalletLimitData> limits = new HashSet<>();

	public String getWalletNo() {
		return walletNo;
	}

	public void setWalletNo(String walletNo) {
		this.walletNo = walletNo;
	}

	public Set<WalletLimitData> getLimits() {
		return limits;
	}

	public void setLimits(Set<WalletLimitData> limits) {
		this.limits = limits;
	}

}
