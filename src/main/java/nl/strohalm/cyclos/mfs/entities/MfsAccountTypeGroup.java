package nl.strohalm.cyclos.mfs.entities;

import java.util.Calendar;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.MemberAccountType;
import nl.strohalm.cyclos.entities.groups.MemberGroup;

public class MfsAccountTypeGroup extends Entity {

	private static final long serialVersionUID = -8963799109950855014L;

	public static enum Relationships implements Relationship {
		ACCOUNT_TYPE("accountType"), GROUP("group");

		private final String name;

		private Relationships(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private MemberAccountType accountType;

	private MemberGroup group;

	private String groupName;

	private String accountTypeName;

	private boolean defaultGroup;

	private Calendar createdAt;

	private Calendar modifiedAt;

	public MemberAccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(MemberAccountType accountType) {
		this.accountType = accountType;
	}

	public MemberGroup getGroup() {
		return group;
	}

	public void setGroup(MemberGroup group) {
		this.group = group;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	public boolean isDefaultGroup() {
		return defaultGroup;
	}

	public void setDefaultGroup(boolean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "MfsAccountTypeGroup [getId()=" + getId() + ", getName()=" + getName() + "]";
	}

}
