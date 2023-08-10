package nl.strohalm.cyclos.mfs.models.transactions;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountCategoryCreditLimitRequestDTO {

	private Long accountTypeId;

	private String accountTypeName;

	private Long accountCategoryId;

	private String accountCategoryName;

	@NotNull(message="lower credit liimt is required")
	@Min(message="lower credit liimit should be equal or greater than zero", value=0)
	private BigDecimal lowerCreditLimit;

	@NotNull(message="lower credit liimt is required")
	@Min(message="upper credit liimt should be equal or greater than zero", value=0)
	private BigDecimal upperCreditLimit;

	private boolean updateAccountLimits = false;

	public Long getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(Long accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public Long getAccountCategoryId() {
		return accountCategoryId;
	}

	public void setAccountCategoryId(Long accountCategoryId) {
		this.accountCategoryId = accountCategoryId;
	}

	public String getAccountCategoryName() {
		return accountCategoryName;
	}

	public void setAccountCategoryName(String accountCategoryName) {
		this.accountCategoryName = accountCategoryName;
	}

	public BigDecimal getLowerCreditLimit() {
		return lowerCreditLimit;
	}

	public void setLowerCreditLimit(BigDecimal lowerCreditLimt) {
		this.lowerCreditLimit = lowerCreditLimt;
	}

	public BigDecimal getUpperCreditLimit() {
		return upperCreditLimit;
	}

	public void setUpperCreditLimit(BigDecimal upperCreditLimit) {
		this.upperCreditLimit = upperCreditLimit;
	}

	public boolean isUpdateAccountLimits() {
		return updateAccountLimits;
	}

	public void setUpdateAccountLimits(boolean updateAccountLimits) {
		this.updateAccountLimits = updateAccountLimits;
	}

}
