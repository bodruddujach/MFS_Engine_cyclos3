package nl.strohalm.cyclos.mfs.models.transactions;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.google.common.base.MoreObjects;

import nl.strohalm.cyclos.mfs.entities.MfsTxnLimitConfig.LimitSubject;
import nl.strohalm.cyclos.mfs.models.enums.TransactionType;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnLimitRequest implements Serializable {

	private static final long serialVersionUID = 5484733901352497958L;

	private String mfsTypeDescription;

	private BigDecimal minAmountPerTxn;

	private BigDecimal maxAmountPerTxn;

	private Long maxNumberOfTxnPerDay;

	private Long maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private LimitSubject applyOn;

	private Long groupId;

	@NotNull
	private TransactionType txnType;

	private String genericLimit;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	private boolean enable;

	public String getMfsTypeDescription() {
		return mfsTypeDescription;
	}

	public void setMfsTypeDescription(String mfsTypeDescription) {
		this.mfsTypeDescription = mfsTypeDescription;
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public String getGenericLimit() {
		return genericLimit;
	}

	public void setGenericLimit(String genericLimit) {
		this.genericLimit = genericLimit;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("minAmountPerTxn", minAmountPerTxn)
				.add("maxAmountPerTxn", maxAmountPerTxn).add("maxNumberOfTxnPerDay", maxNumberOfTxnPerDay)
				.add("txnType", txnType).add("maxNumberOfTxnPerMonth", maxNumberOfTxnPerMonth).toString();
	}
}