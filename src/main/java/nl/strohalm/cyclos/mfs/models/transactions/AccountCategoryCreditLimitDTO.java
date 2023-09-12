package nl.strohalm.cyclos.mfs.models.transactions;

import java.math.BigDecimal;

public class AccountCategoryCreditLimitDTO {

	private Long accountTypeId;

	private String accountTypeName;

	private Long accountCategoryId;

	private String accountCategoryName;

	private BigDecimal lowerCreditLimt;

	private BigDecimal upperCreditLimit;

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

	public BigDecimal getLowerCreditLimt() {
		return lowerCreditLimt;
	}

	public void setLowerCreditLimt(BigDecimal lowerCreditLimt) {
		this.lowerCreditLimt = lowerCreditLimt;
	}

	public BigDecimal getUpperCreditLimit() {
		return upperCreditLimit;
	}

	public void setUpperCreditLimit(BigDecimal upperCreditLimit) {
		this.upperCreditLimit = upperCreditLimit;
	}

}
