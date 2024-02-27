package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;
import nl.strohalm.cyclos.entities.Relationship;
import nl.strohalm.cyclos.entities.accounts.AccountType;
import nl.strohalm.cyclos.utils.StringValuedEnum;

import java.util.Calendar;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class MfsTxnType extends Entity {

  public static enum Relationships implements Relationship {
        PARENT_TYPE("parentType"), CHILD_TYPES("childTypes"), FROM("from"), TO("to");
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

    public static enum AccountTypeNature implements StringValuedEnum {
        MEMBER("M"), SYSTEM("S");

        private final String value;

        private AccountTypeNature(final String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

  @NotNull(message="core Ttxn type Id should not be empty")
  private Long coreTxnTypeId;
  private Long fromTypeId;
  private String fromTypeName;
  private AccountTypeNature fromTypeNature;
  private Long toTypeId;
  private String toTypeName;
  private AccountTypeNature toTypeNature;
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
  private boolean scheduledPayment;
  private TypeCategory typeCategory;
  private MfsTxnType parentType;
  private Collection<MfsTxnType> childTypes;

  public Long getCoreTxnTypeId() {
    return coreTxnTypeId;
  }

  public void setCoreTxnTypeId(Long coreTxnTypeId) {
    this.coreTxnTypeId = coreTxnTypeId;
  }

  public Long getFromTypeId() {
    return fromTypeId;
  }

  public void setFromTypeId(Long fromTypeId) {
    this.fromTypeId = fromTypeId;
  }

  public Long getToTypeId() {
    return toTypeId;
  }

  public void setToTypeId(Long toTypeId) {
    this.toTypeId = toTypeId;
  }

  public String getFromTypeName() {
    return fromTypeName;
  }

  public void setFromTypeName(String fromTypeName) {
    this.fromTypeName = fromTypeName;
  }

  public AccountTypeNature getFromTypeNature() {
    return fromTypeNature;
  }

  public void setFromTypeNature(AccountTypeNature fromTypeNature) {
    this.fromTypeNature = fromTypeNature;
  }

  public String getToTypeName() {
    return toTypeName;
  }

  public void setToTypeName(String toTypeName) {
    this.toTypeName = toTypeName;
  }

  public AccountTypeNature getToTypeNature() {
    return toTypeNature;
  }

  public void setToTypeNature(AccountTypeNature toTypeNature) {
    this.toTypeNature = toTypeNature;
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
  
  public boolean isScheduledPayment() {
    return scheduledPayment;
  }

  public void setScheduledPayment(boolean scheduledPayment) {
    this.scheduledPayment = scheduledPayment;
  }

  public TypeCategory getTypeCategory() {
    return typeCategory;
  }

  public void setTypeCategory(TypeCategory typeCategory) {
    this.typeCategory = typeCategory;
  }

  @JsonBackReference
  public MfsTxnType getParentType() {
    return parentType;
  }

  public void setParentType(MfsTxnType parentType) {
    this.parentType = parentType;
  }

  @JsonManagedReference
  public Collection<MfsTxnType> getChildTypes() {
    return childTypes;
  }

  public void setChildTypes(Collection<MfsTxnType> childTypes) {
    this.childTypes = childTypes;
  }

@Override
  public String toString() {
    return name;
  }

}
