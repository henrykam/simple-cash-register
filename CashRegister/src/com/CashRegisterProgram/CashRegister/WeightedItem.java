package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;

/**
 * Created by Henry on 1/17/2015.
 */

/**
 * Class of a weighted item
 */
public class WeightedItem implements Item {

    private int number;
    private String name;
    private BigDecimal pricePerLb;
    private static String quantifier = "WeightInLbs";

    public WeightedItem(String name, BigDecimal pricePerLb) {
        this.name = name;
        this.pricePerLb = pricePerLb;
    }

    public BigDecimal getPricePerQuantifier() {
        return pricePerLb;
    }

    public void setPricePerQuantifier(BigDecimal pricePerLb) {
        this.pricePerLb = pricePerLb;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the quantifier of the item (eg. Unit, Weight)
     *
     * @return item quantifier
     */
    @Override
    public String getQuantifier() {
        return quantifier;
    }

    /**
     * Gets the item number
     * @return item number
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * Sets the item number
     * @param number item number
     */
    @Override
    public void setNumber(int number) {
        this.number = number;
    }
}

