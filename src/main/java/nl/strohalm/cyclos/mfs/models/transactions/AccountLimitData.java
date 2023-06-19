package nl.strohalm.cyclos.mfs.models.transactions;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountLimitData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String limitTypeName;

	private Long dailyUsedTxnNumber;

	private Long dailyTxnNumber;

	private BigDecimal dailyUsedTxnAmount;

	private BigDecimal dailyTxnAmount;

	private Long monthlyUsedTxnNumber;

	private Long monthlyTxnNumber;

	private BigDecimal monthlyUsedTxnAmount;

	private BigDecimal monthlyTxnAmount;

	public String getLimitTypeName() {
		return limitTypeName;
	}

	public void setLimitTypeName(String limitTypeName) {
		this.limitTypeName = limitTypeName;
	}

	public Long getDailyUsedTxnNumber() {
		return dailyUsedTxnNumber;
	}

	public void setDailyUsedTxnNumber(Long dailyUsedTxnNumber) {
		this.dailyUsedTxnNumber = dailyUsedTxnNumber;
	}

	public Long getDailyTxnNumber() {
		return dailyTxnNumber;
	}

	public void setDailyTxnNumber(Long dailyTxnNumber) {
		this.dailyTxnNumber = dailyTxnNumber;
	}

	public BigDecimal getDailyUsedTxnAmount() {
		return dailyUsedTxnAmount;
	}

	public void setDailyUsedTxnAmount(BigDecimal dailyUsedTxnAmount) {
		this.dailyUsedTxnAmount = dailyUsedTxnAmount;
	}

	public BigDecimal getDailyTxnAmount() {
		return dailyTxnAmount;
	}

	public void setDailyTxnAmount(BigDecimal dailyTxnAmount) {
		this.dailyTxnAmount = dailyTxnAmount;
	}

	public Long getMonthlyUsedTxnNumber() {
		return monthlyUsedTxnNumber;
	}

	public void setMonthlyUsedTxnNumber(Long monthlyUsedTxnNumber) {
		this.monthlyUsedTxnNumber = monthlyUsedTxnNumber;
	}

	public Long getMonthlyTxnNumber() {
		return monthlyTxnNumber;
	}

	public void setMonthlyTxnNumber(Long monthlyTxnNumber) {
		this.monthlyTxnNumber = monthlyTxnNumber;
	}

	public BigDecimal getMonthlyUsedTxnAmount() {
		return monthlyUsedTxnAmount;
	}

	public void setMonthlyUsedTxnAmount(BigDecimal monthlyUsedTxnAmount) {
		this.monthlyUsedTxnAmount = monthlyUsedTxnAmount;
	}

	public BigDecimal getMonthlyTxnAmount() {
		return monthlyTxnAmount;
	}

	public void setMonthlyTxnAmount(BigDecimal monthlyTxnAmount) {
		this.monthlyTxnAmount = monthlyTxnAmount;
	}

}
