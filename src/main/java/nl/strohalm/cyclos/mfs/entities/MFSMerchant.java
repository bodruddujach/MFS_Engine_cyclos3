package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;

import java.math.BigDecimal;
import java.util.Calendar;

public class MFSMerchant extends Entity {

    private BigDecimal serviceFee;

    private BigDecimal minFee;

    private BigDecimal maxFee;

    private String merchantCode;

    private String name;

    private String wallet;

    private String description;

    private String feeDirection;

    private String category;

    private Calendar createdAt;

    private Calendar updatedAt;

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getMinFee() {
        return minFee;
    }

    public void setMinFee(BigDecimal minFee) {
        this.minFee = minFee;
    }

    public BigDecimal getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeeDirection() {
        return feeDirection;
    }

    public void setFeeDirection(String feeDirection) {
        this.feeDirection = feeDirection;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "MFSMerchant{" +
                ", serviceFee=" + serviceFee +
                ", minFee=" + minFee +
                ", maxFee=" + maxFee +
                ", merchantCode='" + merchantCode + '\'' +
                ", name='" + name + '\'' +
                ", wallet='" + wallet + '\'' +
                ", description='" + description + '\'' +
                ", feeDirection='" + feeDirection + '\'' +
                ", category='" + category + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
