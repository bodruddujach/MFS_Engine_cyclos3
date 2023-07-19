package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.utils.StringValuedEnum;

import java.util.Calendar;
import java.util.Date;

public class MfsTxnType extends Entity {

  public static enum Relationships implements Relationship {
        PARENT_TYPE("parentType");
        private final String name;

        private Relationships(final String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
  }
	
  public static enum TxnTypeTag implements StringValuedEnum {
    MFS("mfs"), MFS_DYNAMIC("mfs-dynamic"), I_BANKING("i-banking"), INTERNAL("internal");
    private final String value;

    private TxnTypeTag(final String value) {
      this.value = value;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  public enum TypeCategory implements StringValuedEnum {
    MAIN("main"), FEE("fee"), COMMISSION("commission");
    private final String value;

    private TypeCategory(final String value) {
      this.value = value;
    }

    @Override
    public String getValue() {
      return value;
    }
  }

  private Long coreTxnTypeId;
  private Integer txnCode;
  private String name; // BANK_CASH_IN, CASH_OUT_TO_AGENT
  private String description;
  private Boolean active;
  private Calendar createDate;
  private TxnTypeTag typeTag;
  private boolean includeFeeInResponse;
  private boolean includeFromAcBalanceInResponse;
  private boolean includeToAcBalanceInResponse;
  private boolean fromAcPinEnabled;
  private boolean byAcPinEnabled;
  private TypeCategory typeCategory;
  private MfsTxnType parentType;

  public Long getCoreTxnTypeId() {
    return coreTxnTypeId;
  }

  public void setCoreTxnTypeId(Long coreTxnTypeId) {
    this.coreTxnTypeId = coreTxnTypeId;
  }

  public Integer getTxnCode() {
    return txnCode;
  }

  public void setTxnCode(Integer txnCode) {
    this.txnCode = txnCode;
  }

  @Override
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Calendar getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Calendar createDate) {
    this.createDate = createDate;
  }

  
  public TxnTypeTag getTypeTag() {
    return typeTag;
  }

  public void setTypeTag(TxnTypeTag typeTag) {
    this.typeTag = typeTag;
  }
  
  public boolean isIncludeFeeInResponse() {
    return includeFeeInResponse;
  }

  public void setIncludeFeeInResponse(boolean includeFeeInResponse) {
    this.includeFeeInResponse = includeFeeInResponse;
  }

  public boolean isIncludeFromAcBalanceInResponse() {
    return includeFromAcBalanceInResponse;
  }

  public void setIncludeFromAcBalanceInResponse(boolean includeFromAcBalanceInResponse) {
    this.includeFromAcBalanceInResponse = includeFromAcBalanceInResponse;
  }

  public boolean isIncludeToAcBalanceInResponse() {
    return includeToAcBalanceInResponse;
  }

  public void setIncludeToAcBalanceInResponse(boolean includeToAcBalanceInResponse) {
    this.includeToAcBalanceInResponse = includeToAcBalanceInResponse;
  }

  public boolean isFromAcPinEnabled() {
    return fromAcPinEnabled;
  }

  public void setFromAcPinEnabled(boolean fromAcPinEnabled) {
    this.fromAcPinEnabled = fromAcPinEnabled;
  }

  public boolean isByAcPinEnabled() {
    return byAcPinEnabled;
  }

  public void setByAcPinEnabled(boolean byAcPinEnabled) {
    this.byAcPinEnabled = byAcPinEnabled;
  }

  
  public TypeCategory getTypeCategory() {
    return typeCategory;
  }

  public void setTypeCategory(TypeCategory typeCategory) {
    this.typeCategory = typeCategory;
  }

  public MfsTxnType getParentType() {
    return parentType;
  }

  public void setParentType(MfsTxnType parentType) {
    this.parentType = parentType;
  }

  @Override
  public String toString() {
    return name;
  }
}
