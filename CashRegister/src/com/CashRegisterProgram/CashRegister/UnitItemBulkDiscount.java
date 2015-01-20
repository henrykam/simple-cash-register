package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;

/**
 * Created by Henry on 1/18/2015.
 */
public class UnitItemBulkDiscount {

    private BigDecimal discount;
    private int bulkQuantity;

    public UnitItemBulkDiscount(int bulkQuantity, BigDecimal discount) {
        this.bulkQuantity = bulkQuantity;
        this.discount = discount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public int getBulkQuantity() {
        return bulkQuantity;
    }

    public void setBulkQuantity(int bulkQuantity) {
        this.bulkQuantity = bulkQuantity;
    }
}
