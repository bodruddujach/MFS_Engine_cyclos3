package nl.strohalm.cyclos.mfs.entities;

import nl.strohalm.cyclos.entities.Entity;

import java.math.BigDecimal;

public class TxnLimitConfig extends Entity {

    private BigDecimal dailyLimit;

    private BigDecimal monthlyLimit;

    private BigDecimal min;

    private BigDecimal max;

    private int monthlyLimitCount;

    private int dailyLimitCount;

    private String walletHolder;

    private String txnType;

    private String enable;

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public int getMonthlyLimitCount() {
        return monthlyLimitCount;
    }

    public void setMonthlyLimitCount(int monthlyLimitCount) {
        this.monthlyLimitCount = monthlyLimitCount;
    }

    public int getDailyLimitCount() {
        return dailyLimitCount;
    }

    public void setDailyLimitCount(int dailyLimitCount) {
        this.dailyLimitCount = dailyLimitCount;
    }

    public String getWalletHolder() {
        return walletHolder;
    }

    public void setWalletHolder(String walletHolder) {
        this.walletHolder = walletHolder;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "TxnLimitConfig{" +
                "dailyLimit=" + dailyLimit +
                ", monthlyLimit=" + monthlyLimit +
                ", min=" + min +
                ", max=" + max +
                ", monthlyLimitCount=" + monthlyLimitCount +
                ", dailyLimitCount=" + dailyLimitCount +
                ", walletHolder='" + walletHolder + '\'' +
                ", txnType='" + txnType + '\'' +
                ", enable='" + enable + '\'' +
                '}';
    }
}
