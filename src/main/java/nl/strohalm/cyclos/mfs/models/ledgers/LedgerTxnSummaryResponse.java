package nl.strohalm.cyclos.mfs.models.ledgers;

import java.math.BigDecimal;

public class LedgerTxnSummaryResponse {
	private long txnCount;
	private BigDecimal amount;

	public long getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(long txnCount) {
		this.txnCount = txnCount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
