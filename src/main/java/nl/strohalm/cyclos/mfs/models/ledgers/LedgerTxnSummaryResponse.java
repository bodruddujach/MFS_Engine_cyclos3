package nl.strohalm.cyclos.mfs.models.ledgers;

import java.math.BigDecimal;

public class LedgerTxnSummaryResponse {
	private int txnCount;
	private BigDecimal amount;

	public int getTxnCount() {
		return txnCount;
	}

	public void setTxnCount(int txnCount) {
		this.txnCount = txnCount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
