package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.enums.MerchantCategory;

import java.math.BigDecimal;

public class MerchantRegRequest extends AcRegRequest {
  private String                      merchantCode;

  private BigDecimal feeRate;
  private BigDecimal                  maxFee;
  private BigDecimal                  minFee;
  private BigDecimal                  fixedFee;

  private Boolean                     feeFromMerchant = false;
  private Boolean                     feeFromCustomer = false;
  private Boolean                     enableFeeTxn    = true;


  private MerchantCategory category;


  public String getMerchantCode() {
    return merchantCode;
  }

  public void setMerchantCode(String merchantCode) {
    this.merchantCode = merchantCode;
  }

  public BigDecimal getFeeRate() {
    return feeRate;
  }

  public void setFeeRate(BigDecimal feeRate) {
    this.feeRate = feeRate;
  }

  public BigDecimal getMaxFee() {
    return maxFee;
  }

  public void setMaxFee(BigDecimal maxFee) {
    this.maxFee = maxFee;
  }

  public BigDecimal getMinFee() {
    return minFee;
  }

  public void setMinFee(BigDecimal minFee) {
    this.minFee = minFee;
  }

  public BigDecimal getFixedFee() {
    return fixedFee;
  }

  public void setFixedFee(BigDecimal fixedFee) {
    this.fixedFee = fixedFee;
  }

  public Boolean getFeeFromMerchant() {
    return feeFromMerchant;
  }

  public void setFeeFromMerchant(Boolean feeFromMerchant) {
    this.feeFromMerchant = feeFromMerchant;
  }

  public Boolean getFeeFromCustomer() {
    return feeFromCustomer;
  }

  public void setFeeFromCustomer(Boolean feeFromCustomer) {
    this.feeFromCustomer = feeFromCustomer;
  }

  public Boolean getEnableFeeTxn() {
    return enableFeeTxn;
  }

  public void setEnableFeeTxn(Boolean enableFeeTxn) {
    this.enableFeeTxn = enableFeeTxn;
  }

  public MerchantCategory getCategory() {
    return category;
  }

  public void setCategory(MerchantCategory category) {
    this.category = category;
  }

}
