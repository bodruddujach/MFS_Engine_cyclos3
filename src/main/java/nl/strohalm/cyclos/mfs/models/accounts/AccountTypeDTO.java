package nl.strohalm.cyclos.mfs.models.accounts;

import java.io.Serializable;
import java.util.List;

public class AccountTypeDTO implements Serializable{
	private static final long serialVersionUID = -679692678102729610L;

	private long typeId;
	private String typeName;
	private List<AccountCategoryDTO> accountCategories;

	public AccountTypeDTO(long typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<AccountCategoryDTO> getAccountCategories() {
		return accountCategories;
	}

	public void setAccountCategories(List<AccountCategoryDTO> accountCategories) {
		this.accountCategories = accountCategories;
	}
}
