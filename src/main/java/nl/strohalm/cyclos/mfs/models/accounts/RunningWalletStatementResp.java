package nl.strohalm.cyclos.mfs.models.accounts;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class RunningWalletStatementResp extends WalletStatementResp {

	private String accountName;
	private String AccountStatus;
	private BigDecimal openigBalance;
	private BigDecimal closingBalance;
	private BigDecimal currentBalance;
	private Calendar fromDate;
	private Calendar toDate;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountStatus() {
		return AccountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		AccountStatus = accountStatus;
	}

	public BigDecimal getOpenigBalance() {
		return openigBalance;
	}

	public void setOpenigBalance(BigDecimal openigBalance) {
		this.openigBalance = openigBalance;
	}

	public BigDecimal getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Calendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}

	public Calendar getToDate() {
		return toDate;
	}

	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}

}
