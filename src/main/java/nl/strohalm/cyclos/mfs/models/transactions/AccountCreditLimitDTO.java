package nl.strohalm.cyclos.mfs.models.transactions;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountCreditLimitDTO {

	private String walletNo;

	@NotNull(message="lower credit limit is required")
	@Min(message="lower credit limit should be equal or greater than zero", value=0)
	private BigDecimal lowerCreditLimit;

	@NotNull(message="lower credit liimt is required")
	@Min(message="upper credit limit should be equal or greater than zero", value=0)
	private BigDecimal upperCreditLimit;

	public String getWalletNo() {
		return walletNo;
	}

	public void setWalletNo(String walletNo) {
		this.walletNo = walletNo;
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

}
