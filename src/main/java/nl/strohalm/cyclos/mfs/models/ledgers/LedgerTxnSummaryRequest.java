package nl.strohalm.cyclos.mfs.models.ledgers;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

public class LedgerTxnSummaryRequest {

	@NotNull
	private String ledgerCode;
	@NotNull
	private Calendar date;

	private boolean credits = false;

	private String paymentFilter;

	private boolean includeChargebacks = false;

	private boolean rootOnlyTransactions = true;

	public String getLedgerCode() {
		return ledgerCode;
	}

	public void setLedgerCode(String ledgerCode) {
		this.ledgerCode = ledgerCode;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public boolean isCredits() {
		return credits;
	}

	public void setCredits(boolean credits) {
		this.credits = credits;
	}

	public String getPaymentFilter() {
		return paymentFilter;
	}

	public void setPaymentFilter(String paymentFilter) {
		this.paymentFilter = paymentFilter;
	}

	public boolean isIncludeChargebacks() {
		return includeChargebacks;
	}

	public void setIncludeChargebacks(boolean includeChargebacks) {
		this.includeChargebacks = includeChargebacks;
	}

	public boolean isRootOnlyTransactions() {
		return rootOnlyTransactions;
	}

	public void setRootOnlyTransactions(boolean rootOnlyTransactions) {
		this.rootOnlyTransactions = rootOnlyTransactions;
	}

}
