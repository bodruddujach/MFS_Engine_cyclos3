package nl.strohalm.cyclos.mfs.models.transactions;

import java.util.Set;

public class TxnLimitExchangeDTO {

	Set<WalletLimitData> limits;

	public Set<WalletLimitData> getLimits() {
		return limits;
	}

	public void setLimits(Set<WalletLimitData> limits) {
		this.limits = limits;
	}

}
