package nl.strohalm.cyclos.mfs.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.access.User;
import nl.strohalm.cyclos.entities.accounts.transactions.TransferType;
import nl.strohalm.cyclos.utils.StringValuedEnum;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MfsTxnLimitConfig extends Entity {

	public static enum LimitSubject implements StringValuedEnum {
		FROM("src"), TO("dst");

		private final String value;

		private LimitSubject(final String value) {
			this.value = value;
		}

		@Override
		public String getValue() {
			return value;
		}
	}

	public static enum Relationships implements Relationship {
		TransferType("transferType"), MFS_GENERIC_LIMIT("genericLimit");

		private final String name;

		private Relationships(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}

	private String mfsTypeName;

	private String mfsTypeDescription;

	private String fromAcType;

	private String toAcType;

	private BigDecimal minAmountPerTxn;

	private BigDecimal maxAmountPerTxn;

	private Integer maxNumberOfTxnPerDay;

	private Integer maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private LimitSubject applyOn;

	private TransferType transferType;

	private MfsGenericLimitConfig genericLimit;

	private boolean enable;

	private User createdBy;

	private Calendar createdAt;

	private User lastModifiedBy;

	private Calendar lastModifiedDate;

	public String getMfsTypeName() {
		return mfsTypeName;
	}

	public void setMfsTypeName(String mfsTypeName) {
		this.mfsTypeName = mfsTypeName;
	}

	public String getMfsTypeDescription() {
		return mfsTypeDescription;
	}

	public void setMfsTypeDescription(String mfsTypeDescription) {
		this.mfsTypeDescription = mfsTypeDescription;
	}

	public String getFromAcType() {
		return fromAcType;
	}

	public void setFromAcType(String fromAcType) {
		this.fromAcType = fromAcType;
	}

	public String getToAcType() {
		return toAcType;
	}

	public void setToAcType(String toAcType) {
		this.toAcType = toAcType;
	}

	public BigDecimal getMinAmountPerTxn() {
		return minAmountPerTxn;
	}

	public void setMinAmountPerTxn(final BigDecimal minAmountPerTxn) {
		this.minAmountPerTxn = minAmountPerTxn;
	}

	public BigDecimal getMaxAmountPerTxn() {
		return maxAmountPerTxn;
	}

	public void setMaxAmountPerTxn(final BigDecimal maxAmountPerTxn) {
		this.maxAmountPerTxn = maxAmountPerTxn;
	}

	public Integer getMaxNumberOfTxnPerDay() {
		return maxNumberOfTxnPerDay;
	}

	public void setMaxNumberOfTxnPerDay(final Integer maxNumberOfTxnPerDay) {
		this.maxNumberOfTxnPerDay = maxNumberOfTxnPerDay;
	}

	public Integer getMaxNumberOfTxnPerMonth() {
		return maxNumberOfTxnPerMonth;
	}

	public void setMaxNumberOfTxnPerMonth(final Integer maxNumberOfTxnPerMonth) {
		this.maxNumberOfTxnPerMonth = maxNumberOfTxnPerMonth;
	}

	public BigDecimal getMaxAmountPerDay() {
		return maxAmountPerDay;
	}

	public void setMaxAmountPerDay(final BigDecimal maxAmountPerDay) {
		this.maxAmountPerDay = maxAmountPerDay;
	}

	public BigDecimal getMaxAmountPerMonth() {
		return maxAmountPerMonth;
	}

	public void setMaxAmountPerMonth(final BigDecimal maxAmountPerMonth) {
		this.maxAmountPerMonth = maxAmountPerMonth;
	}

	public LimitSubject getApplyOn() {
		return applyOn;
	}

	public void setApplyOn(final LimitSubject applyOn) {
		this.applyOn = applyOn;
	}

	@JsonIgnore
	public TransferType getTransferType() {
		return transferType;
	}

	public void setTransferType(final TransferType transferType) {
		this.transferType = transferType;
	}

//	@JsonIgnore
	public MfsGenericLimitConfig getGenericLimit() {
		return genericLimit;
	}

	public void setGenericLimit(MfsGenericLimitConfig genericLimit) {
		this.genericLimit = genericLimit;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(final boolean enable) {
		this.enable = enable;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(final User createdBy) {
		this.createdBy = createdBy;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(final User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Calendar getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(final Calendar lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "TxnLimitConfig{" + "minAmountPerTxn=" + minAmountPerTxn + ", maxAmountPerTxn=" + maxAmountPerTxn
				+ ", maxNumberOfTxnPerDay=" + maxNumberOfTxnPerDay + ", maxNumberOfTxnPerMonth="
				+ maxNumberOfTxnPerMonth + ", maxAmountPerDay=" + maxAmountPerDay + ", maxAmountPerMonth="
				+ maxAmountPerMonth + ", applyOn=" + applyOn + ", enable='" + enable + '\'' + '}';
	}

}
