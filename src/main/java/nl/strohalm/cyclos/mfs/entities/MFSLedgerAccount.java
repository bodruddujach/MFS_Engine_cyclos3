package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;

import java.util.Calendar;

public class MFSLedgerAccount extends Entity {

  public enum LedgerType {MEMBER, SYSTEM}

  Long accountId;

  Long parentId;

  String code;

  String description;

  String level;

  String category;

  boolean onlyParent;

  boolean active;

  String type; // MEMBER, SYSTEM

  Calendar createDate;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public boolean isOnlyParent() {
    return onlyParent;
  }

  public void setOnlyParent(boolean onlyParent) {
    this.onlyParent = onlyParent;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Calendar getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Calendar createDate) {
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return "MFSLedgerAccount{" +
      "accountId=" + accountId +
      ", parentId=" + parentId +
      ", code='" + code + '\'' +
      ", level='" + level + '\'' +
      ", category='" + category + '\'' +
      '}';
  }
}
