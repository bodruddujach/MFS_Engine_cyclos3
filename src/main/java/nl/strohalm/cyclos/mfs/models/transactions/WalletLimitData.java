package nl.strohalm.cyclos.mfs.models.transactions;

import java.math.BigDecimal;
import java.util.Objects;

public class WalletLimitData {

	private Long limitId;

	private Long accountId;

	private long txnTypeId;

	private String txnTypeName;

	private BigDecimal minAmountPerTxn;

	private BigDecimal maxAmountPerTxn;

	private Long maxNumberOfTxnPerDay;

	private Long maxNumberOfTxnPerMonth;

	private BigDecimal maxAmountPerDay;

	private BigDecimal maxAmountPerMonth;

	private Long groupId;

	private String groupName;

	private boolean enable;

	private String applyOn;
	
	public WalletLimitData() {

	}

	public WalletLimitData(long txnTypeId, Long groupId, String applyOn) {
		this.txnTypeId = txnTypeId;
		this.groupId = groupId;
		this.applyOn = applyOn;
	}

	public WalletLimitData(long txnTypeId, Long groupId, String applyOn, Long accountId) {
		this.txnTypeId = txnTypeId;
		this.groupId = groupId;
		this.applyOn = applyOn;
		this.accountId = accountId;
	}

	public Long getLimitId() {
		return limitId;
	}

	public void setLimitId(Long limitId) {
		this.limitId = limitId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public long getTxnTypeId() {
		return txnTypeId;
	}

	public void setTxnTypeId(long txnTypeId) {
		this.txnTypeId = txnTypeId;
	}

	public String getTxnTypeName() {
		return txnTypeName;
	}

	public void setTxnTypeName(String txnTypeName) {
		this.txnTypeName = txnTypeName;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getApplyOn() {
		return applyOn;
	}

	public void setApplyOn(String applyOn) {
		this.applyOn = applyOn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, applyOn, groupId, txnTypeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WalletLimitData other = (WalletLimitData) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(applyOn, other.applyOn)
				&& Objects.equals(groupId, other.groupId) && txnTypeId == other.txnTypeId;
	}

	
}
