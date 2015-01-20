package com.CashRegisterProgram.CashRegister;

/**
 * Created by Henry on 1/17/2015.
 */

import java.math.BigDecimal;

/**
 * Class of a unit item
 */
public class UnitItem implements Item {

    private int number;
    private String name;
    private BigDecimal pricePerUnit;
    private static String quantifier = "Unit";
    private UnitItemBulkDiscount unitItemBulkDiscount;
    private boolean hasBulkDiscount;

    public UnitItem(String name, BigDecimal pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public void enterBulkDiscount(int bulkQuantity, BigDecimal discount) {
        unitItemBulkDiscount = new UnitItemBulkDiscount(bulkQuantity, discount);
        hasBulkDiscount = true;
    }

    public boolean hasBulkDiscount() {
        return hasBulkDiscount;
    }

    public int getBulkDiscountQuantity() {
        if(unitItemBulkDiscount == null)
            return -1;
        else
            return unitItemBulkDiscount.getBulkQuantity();
    }

    public BigDecimal getBulkDiscountAmount() {
        if(unitItemBulkDiscount == null)
            return BigDecimal.ZERO;
        else
            return unitItemBulkDiscount.getDiscount();
    }

    public BigDecimal getPricePerQuantifier() {
        return pricePerUnit;
    }

    public void setPricePerQuantifier(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

}
