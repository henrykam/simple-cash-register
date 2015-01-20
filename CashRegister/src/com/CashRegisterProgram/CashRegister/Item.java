package com.CashRegisterProgram.CashRegister;

/**
 * Created by Henry on 1/17/2015.
 */

import java.math.BigDecimal;

/**
 * Interface of an Item
 */
public interface Item {

    /**
     * Gets the name of the item
     * @return name of the item
     */
    public String getName();

    /**
     * Sets the name of the item
     * @param name name of the item
     */
    public void setName(String name);

    /**
     * Sets the price per quantifier
     * @param pricePerQuantifier price per quantifier
     */
    public void setPricePerQuantifier(BigDecimal pricePerQuantifier);

    /**
     * Gets the price per quantifier
     * @return price per quantifier
     */
    public BigDecimal getPricePerQuantifier();

    /**
     * Gets the quantifier of the item (eg. Unit, Weight)
     * @return item quantifier
     */
    public String getQuantifier();

    /**
     * Gets the item number
     * @return item number
     */
    public int getNumber();

    /**
     * Sets the item number
     * @param number item number
     */
    public void setNumber(int number);


}
