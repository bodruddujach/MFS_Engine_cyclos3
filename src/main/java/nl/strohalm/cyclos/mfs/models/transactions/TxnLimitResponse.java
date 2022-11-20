package nl.strohalm.cyclos.mfs.models.transactions;

import java.io.Serializable;
import java.math.BigDecimal;

import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig.LimitSubject;

public class TxnLimitResponse implements Serializable {

	private static final long serialVersionUID = -8060456222893541999L;

	private long id;

	private BigDecimal minAmountPerTxn;

	private BigDecimal maxAmountPerTxn;

	private Long maxNumberOfTxnPerDay;

	private Long maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private LimitSubject applyOn;

	private String txnType;

	private boolean enable;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getMinAmountPerTxn() {
		return minAmountPerTxn;
	}

	public void setMinAmountPerTxn(BigDecimal minAmountPerTxn) {
		this.minAmountPerTxn = minAmountPerTxn;
	}

	public BigDecimal getMaxAmountPerTxn() {
		return maxAmountPerTxn;
	}

	public void setMaxAmountPerTxn(BigDecimal maxAmountPerTxn) {
		this.maxAmountPerTxn = maxAmountPerTxn;
	}

	public Long getMaxNumberOfTxnPerDay() {
		return maxNumberOfTxnPerDay;
	}

	public void setMaxNumberOfTxnPerDay(Long maxNumberOfTxnPerDay) {
		this.maxNumberOfTxnPerDay = maxNumberOfTxnPerDay;
	}

	public Long getMaxNumberOfTxnPerMonth() {
		return maxNumberOfTxnPerMonth;
	}

	public void setMaxNumberOfTxnPerMonth(Long maxNumberOfTxnPerMonth) {
		this.maxNumberOfTxnPerMonth = maxNumberOfTxnPerMonth;
	}

	public BigDecimal getMaxAmountPerDay() {
		return maxAmountPerDay;
	}

	public void setMaxAmountPerDay(BigDecimal maxAmountPerDay) {
		this.maxAmountPerDay = maxAmountPerDay;
	}

	public BigDecimal getMaxAmountPerMonth() {
		return maxAmountPerMonth;
	}

	public void setMaxAmountPerMonth(BigDecimal maxAmountPerMonth) {
		this.maxAmountPerMonth = maxAmountPerMonth;
	}

	public LimitSubject getApplyOn() {
		return applyOn;
	}

	public void setApplyOn(LimitSubject applyOn) {
		this.applyOn = applyOn;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
