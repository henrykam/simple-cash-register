package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;

/**
 * Created by Henry on 1/17/2015.
 */

/**
 * Interface of the cash register
 */
public interface CashRegister {

    // Transaction methods

    /**
     * Begins a new transaction
     */
    public void transactionClose() throws Exception;

    /**
     * Ends an existing transaction
     */
    public void transactionOpen() throws Exception;

    /**
     * Checks whether an existing transaction is open.
     * @return true when a transaction is currently open, else false
     */
    public boolean isTransactionOpen();

    /**
     * Checks whether unit item exists
     * @param itemNumber item number to check
     * @return true when unit item exists, else false
     */
    public boolean unitItemExists(int itemNumber);

    /**
     * Check whether weighted item exists
     * @param itemNumber item number to check
     * @return true when weighted item exists, else false
     */
    public boolean weightedItemExists(int itemNumber);

    /**
     * Purchase a unit item by item number and quantity.
     * @param itemNumber item number to purchase
     * @param quantity unit quantity of purchase
     */
    public void transactionPurchaseUnitItem(int itemNumber, int quantity) throws Exception;

    /**
     * Purchase a weighted item by item number and weight/
     * @param itemNumber item number to purchase
     * @param weightInLbs weight of purchase
     */
    public void transactionPurchaseWeightedItem(int itemNumber, double weightInLbs) throws Exception;

    /**
     * Apply a coupon to sale
     * @param savings amount of savings
     * @param threshold total purchase threshold to qualify
     * @throws Exception
     */
    public void transactionApplyCoupon(BigDecimal savings, BigDecimal threshold) throws Exception;

    /**
     * Prints the transaction receipt
     */
    public void transactionPrintReceipt() throws Exception;


    // Admin methods

    /**
     * Adds a new unit item to the cash register.
     * Returns the item number assigned to the new item.
     * @param itemName the name of the unit item
     * @param price the price of the unit item per quanta
     * @return the item number assigned
     */
    public int adminAddUnitItem(String itemName, BigDecimal price) throws Exception;

    /**
     * Adds a new weighted item to the cash register.
     * Returns the item number assigned to the new item.
     * @param itemName the name of the weighted item
     * @param pricePerLb the price of the weighted item per kg
     * @return the item number assigned
     */
    public int adminAddWeightedItem(String itemName, BigDecimal pricePerLb) throws Exception;

    /**
     * Sets the discount for a unit item
     * @param itemNumber item number of the unit item
     * @param bulkQuantity purchased quantity to receive discount
     * @param discount amount discounted
     * @return
     */
    public void adminSetUnitItemBulkDiscount(int itemNumber, int bulkQuantity, BigDecimal discount) throws Exception;

    /**
     * Prints the available items
     */
    public void adminPrintItems();

}
