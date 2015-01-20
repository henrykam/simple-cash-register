package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;

/**
 * Created by Henry on 1/18/2015.
 */
public class Coupon {
    private BigDecimal threshold;
    private BigDecimal savings;

    public Coupon(BigDecimal threshold, BigDecimal savings) {
        this.threshold = threshold;
        this.savings = savings;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getSavings() {
        return savings;
    }

    public void setSavings(BigDecimal savings) {
        this.savings = savings;
    }
}
