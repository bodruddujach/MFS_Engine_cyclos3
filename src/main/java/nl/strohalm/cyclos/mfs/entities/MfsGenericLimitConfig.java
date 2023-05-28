package nl.strohalm.cyclos.mfs.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.access.User;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MfsGenericLimitConfig extends Entity {

	private static final long serialVersionUID = 1L;

	public static enum Relationships implements Relationship {
		MFS_TRANSACTION_LIMIT_CONFIGS("mfsTransactionLimitConfigs");

		private final String name;

		private Relationships(final String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}

	private String name;

	private String description;

	private Integer maxNumberOfTxnPerDay;

	private Integer maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private Collection<? extends MfsTxnLimitConfig> mfsTransactionLimitConfigs;

	private boolean enable;

	private User createdBy;

	private Calendar createdAt;

	private User lastModifiedBy;

	private Calendar lastModifiedDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@JsonIgnore
	public Collection<? extends MfsTxnLimitConfig> getMfsTransactionLimitConfigs() {
		return mfsTransactionLimitConfigs;
	}

	public void setMfsTransactionLimitConfigs(
			final Collection<? extends MfsTxnLimitConfig> mfsTransactionLimitConfigs) {
		this.mfsTransactionLimitConfigs = mfsTransactionLimitConfigs;
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
		return "MfsGenericLimitConfig [id= " + getId() + ",name=" + name + ", maxNumberOfTxnPerDay=" + maxNumberOfTxnPerDay
				+ ", maxNumberOfTxnPerMonth=" + maxNumberOfTxnPerMonth + ", maxAmountPerDay=" + maxAmountPerDay
				+ ", maxAmountPerMonth=" + maxAmountPerMonth + ", enable=" + enable + ", createdBy=" + createdBy
				+ ", createdAt=" + createdAt + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}

}
