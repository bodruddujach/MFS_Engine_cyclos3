package nl.strohalm.cyclos.mfs.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.access.User;
import nl.strohalm.cyclos.entities.accounts.MemberAccount;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MfsAccountGenericLimitConfig extends Entity {

	private static final long serialVersionUID = 1L;

	public static enum Relationships implements Relationship {
		MFS_ACCOUNT_TRANSACTION_LIMIT_CONFIGS("mfsAccountTransactionLimitConfigs"), ACCOUNT("account");

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

	private Long maxNumberOfTxnPerDay;

	private Long maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private Collection<? extends MfsAccountTxnLimitConfig> mfsAccountTransactionLimitConfigs;

	private boolean enable;

	private User createdBy;

	private Calendar createdAt;

	private User lastModifiedBy;

	private Calendar lastModifiedDate;

	private MemberAccount account;

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

	public Long getMaxNumberOfTxnPerDay() {
		return maxNumberOfTxnPerDay;
	}

	public void setMaxNumberOfTxnPerDay(final Long maxNumberOfTxnPerDay) {
		this.maxNumberOfTxnPerDay = maxNumberOfTxnPerDay;
	}

	public Long getMaxNumberOfTxnPerMonth() {
		return maxNumberOfTxnPerMonth;
	}

	public void setMaxNumberOfTxnPerMonth(final Long maxNumberOfTxnPerMonth) {
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
	public Collection<? extends MfsAccountTxnLimitConfig> getMfsAccountTransactionLimitConfigs() {
		return mfsAccountTransactionLimitConfigs;
	}

	public void setMfsAccountTransactionLimitConfigs(
			Collection<? extends MfsAccountTxnLimitConfig> mfsAccountTransactionLimitConfigs) {
		this.mfsAccountTransactionLimitConfigs = mfsAccountTransactionLimitConfigs;
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

	public MemberAccount getAccount() {
		return account;
	}

	public void setAccount(MemberAccount account) {
		this.account = account;
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
