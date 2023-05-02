package nl.strohalm.cyclos.mfs.models.transactions;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountLimitData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String limitTypeName;

	private Integer dailyUsedTxnNumber;

	private Integer dailyTxnNumber;

	private BigDecimal dailyUsedTxnAmount;

	private BigDecimal dailyTxnAmount;

	private Integer monthlyUsedTxnNumber;

	private Integer monthlyTxnNumber;

	private BigDecimal monthlyUsedTxnAmount;

	private BigDecimal monthlyTxnAmount;

	public String getLimitTypeName() {
		return limitTypeName;
	}

	public void setLimitTypeName(String limitTypeName) {
		this.limitTypeName = limitTypeName;
	}

	public Integer getDailyUsedTxnNumber() {
		return dailyUsedTxnNumber;
	}

	public void setDailyUsedTxnNumber(Integer dailyUsedTxnNumber) {
		this.dailyUsedTxnNumber = dailyUsedTxnNumber;
	}

	public Integer getDailyTxnNumber() {
		return dailyTxnNumber;
	}

	public void setDailyTxnNumber(Integer dailyTxnNumber) {
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

	public Integer getMonthlyUsedTxnNumber() {
		return monthlyUsedTxnNumber;
	}

	public void setMonthlyUsedTxnNumber(Integer monthlyUsedTxnNumber) {
		this.monthlyUsedTxnNumber = monthlyUsedTxnNumber;
	}

	public Integer getMonthlyTxnNumber() {
		return monthlyTxnNumber;
	}

	public void setMonthlyTxnNumber(Integer monthlyTxnNumber) {
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
