package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.AccountStatus;
import nl.strohalm.cyclos.mfs.models.enums.AccountType;
import nl.strohalm.cyclos.mfs.models.transactions.Response;

public class WalletInfoResponse extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1865460926496253449L;
	private String walletNo;
	private String name;
	private String userType;
	private AccountTypeDTO accountType;
	private AccountCategoryDTO accountCategory;
	private AccountStatus accountStatus;

	public String getWalletNo() {
		return walletNo;
	}

	public void setWalletNo(String walletNo) {
		this.walletNo = walletNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public AccountTypeDTO getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountTypeDTO accountType) {
		this.accountType = accountType;
	}

	public AccountCategoryDTO getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(AccountCategoryDTO accountCategory) {
		this.accountCategory = accountCategory;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
}
