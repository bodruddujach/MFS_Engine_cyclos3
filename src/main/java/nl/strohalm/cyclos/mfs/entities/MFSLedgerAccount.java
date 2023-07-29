package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;

import java.util.Calendar;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MFSLedgerAccount extends Entity {

  public enum Relationships implements Relationship {
      PARENT_LEDGER("parentLedger");
      private final String name;

      private Relationships(final String name) {
        this.name = name;
      }

      @Override
      public String getName() {
          return name;
      }
  }
  public enum LedgerType {MEMBER, SYSTEM}

  Long accountId;

  MFSLedgerAccount parentLedger;

  String code;

  String name;

  String description;

  String level;

  String category;

  String nature;

  boolean onlyParent;

  boolean lastLevel;

  boolean active;

  String type; // MEMBER, SYSTEM

  boolean showInReports;

  Calendar createDate;

  @JsonIgnore
  private Collection<MFSLedgerAccount> childLedgers;

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

  public String getNature() {
    return nature;
  }

  public void setNature(String nature) {
    this.nature = nature;
  }

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public MFSLedgerAccount getParentLedger() {
    return parentLedger;
  }

  public void setParentLedger(MFSLedgerAccount parentLedger) {
    this.parentLedger = parentLedger;
  }

  public void changeParentLedger(MFSLedgerAccount newParentLedger) {
    this.getParentLedger().getChildLedgers().remove(this);
    this.setParentLedger(newParentLedger);
    newParentLedger.getChildLedgers().add(this);
  }

  public boolean isOnlyParent() {
    return onlyParent;
  }

  public void setOnlyParent(boolean onlyParent) {
    this.onlyParent = onlyParent;
  }

  public boolean isLastLevel() {
    return lastLevel;
  }

  public void setLastLevel(boolean lastlevel) {
    this.lastLevel = lastlevel;
  }

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

  public boolean isShowInReports() {
    return showInReports;
  }

  public void setShowInReports(boolean showInReports) {
    this.showInReports = showInReports;
  }
  
  public Collection<MFSLedgerAccount> getChildLedgers() {
    return childLedgers;
  }

  public void setChildLedgers(Collection<MFSLedgerAccount> childLedgers) {
    this.childLedgers = childLedgers;
  }

@Override
  public String toString() {
    return "MFSLedgerAccount{" +
      "accountId=" + accountId +
      ", code='" + code + '\'' +
      ", level='" + level + '\'' +
      ", category='" + category + '\'' +
      '}';
  }
}
