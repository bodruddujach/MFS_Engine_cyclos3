package nl.strohalm.cyclos.mfs.models.accounts;

import java.io.Serializable;

public class AccountCategoryDTO implements Serializable{

	private static final long serialVersionUID = 4496612744847715528L;

	private long categoryId;
	private String categoryName;

	public AccountCategoryDTO(long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
