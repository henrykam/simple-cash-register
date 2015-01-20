package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Henry on 1/18/2015.
 */
public class MyCashRegister implements CashRegister {

    List<TransactionEntry> transactionEntryList;
    List<Coupon> couponList;
    ItemHandler unitItemHandler;
    ItemHandler weightedItemHandler;
    boolean isTransactionOpen;

    public MyCashRegister() {
        couponList = new ArrayList<Coupon>();
        isTransactionOpen = false;
        unitItemHandler = new ItemHandler();
        weightedItemHandler = new ItemHandler();
        transactionEntryList = new LinkedList<TransactionEntry>();
    }

    /**
     * Begins a new transaction
     */
    @Override
    public void transactionOpen() {
        isTransactionOpen = true;
        transactionEntryList = new ArrayList<TransactionEntry>();
    }

    /**
     * Ends an existing transaction
     */
    @Override
    public void transactionClose() {
        isTransactionOpen = false;
        transactionEntryList = null;
    }

    /**
     * Checks whether an existing transaction is open.
     *
     * @return true when a transaction is currently open, else false
     */
    @Override
    public boolean isTransactionOpen() {
        return isTransactionOpen;
    }

    /**
     * Checks whether unit item exists
     * @param itemNumber item number to check
     * @return true when unit item exists, else false
     */
    @Override
    public boolean unitItemExists(int itemNumber) {
        return unitItemHandler.itemExists(itemNumber);
    }

    /**
     * Check whether weighted item exists
     * @param itemNumber item number to check
     * @return true when weighted item exists, else false
     */
    @Override
    public boolean weightedItemExists(int itemNumber) {
        return weightedItemHandler.itemExists(itemNumber);
    }

    /**
     * Purchase a unit item by item number and quantity.
     *
     * @param itemNumber item number to purchase
     * @param quantity   unit quantity of purchase
     */
    @Override
    public void transactionPurchaseUnitItem(int itemNumber, int quantity) throws Exception {
        if(isTransactionOpen) {

            if(!unitItemExists(itemNumber))
                throw new ItemNotFoundException();

            Item item = unitItemHandler.getItem(itemNumber);
            String quantifier = item.getQuantifier();
            if(quantifier == "Unit") {
                String itemName = item.getName();
                BigDecimal price = item.getPricePerQuantifier();
                BigDecimal total = price.multiply(new BigDecimal(quantity));

                TransactionEntry transactionEntry = new TransactionEntry(item, new BigDecimal(quantity), total);
                if(transactionEntryList.contains(transactionEntry)) {
                    transactionEntry = transactionEntryList.get(transactionEntryList.indexOf(transactionEntry));
                    transactionEntry.setAmount(transactionEntry.getAmount().add(new BigDecimal(quantity)));
                    transactionEntry.setTotal(price.multiply(transactionEntry.getAmount()));
                }
                else
                    transactionEntryList.add(transactionEntry);
            }

        }
        else {
            throw new Exception("No open transactions!");
        }
    }

    /**
     * Purchase a weighted item by item number and weight/
     *
     * @param itemNumber item number to purchase
     * @param weightInLbs weight of purchase
     */
    @Override
    public void transactionPurchaseWeightedItem(int itemNumber, double weightInLbs) throws Exception {
        if(isTransactionOpen) {

            if(!weightedItemExists(itemNumber))
                throw new ItemNotFoundException();

            Item item = weightedItemHandler.getItem(itemNumber);
            String quantifier = item.getQuantifier();
            if(quantifier == "WeightInLbs") {

                String itemName = item.getName();
                BigDecimal price = item.getPricePerQuantifier();
                BigDecimal total = price.multiply(new BigDecimal(weightInLbs));

                TransactionEntry transactionEntry = new TransactionEntry(item, new BigDecimal(weightInLbs), total);
                if(transactionEntryList.contains(transactionEntry)) {
                    transactionEntry = transactionEntryList.get(transactionEntryList.indexOf(transactionEntry));
                    transactionEntry.setAmount(transactionEntry.getAmount().add(new BigDecimal(weightInLbs)));
                    transactionEntry.setTotal(price.multiply(transactionEntry.getAmount()));
                }
                else
                    transactionEntryList.add(transactionEntry);
            }
        }
        else {
            throw new Exception("No open transactions!");
        }
    }

    /**
     * Apply a coupon to sale
     * @param savings amount of savings
     * @param threshold total purchase threshold to qualify
     * @throws Exception
     */
    @Override
    public void transactionApplyCoupon(BigDecimal savings, BigDecimal threshold) throws Exception {
        if(isTransactionOpen) {
            Coupon coupon = new Coupon(threshold, savings);
            couponList.add(coupon);
        }
        else {
            throw new Exception("No open transactions!");
        }
    }

    /**
     * Prints the transaction receipt
     */
    @Override
    public void transactionPrintReceipt() throws Exception {
        if(isTransactionOpen) {

            BigDecimal subtotal = BigDecimal.ZERO;

            System.out.println("HENRY'S STORE");
            System.out.println("*** RECEIPT ***");
            System.out.println("");
            System.out.println("Purchases: ");
            System.out.println("----------------------------------------------------");

            BigDecimal bulkDiscount = BigDecimal.ZERO;
            for(TransactionEntry entry:transactionEntryList) {
                System.out.print("  Item: " + entry.getName());
                System.out.print("  Qty: " + entry.getAmount());
                System.out.print("  Price: " + entry.getPrice());
                System.out.print("  Total: " + entry.getTotal());
                System.out.println("");

                subtotal = subtotal.add(entry.getTotal());
                if(entry.getItem() instanceof UnitItem) {
                    UnitItem unitItem = (UnitItem) entry.getItem();
                    if(unitItem.hasBulkDiscount() && entry.getAmount().intValue() >= unitItem.getBulkDiscountQuantity())
                        bulkDiscount = bulkDiscount.add(unitItem.getBulkDiscountAmount());
                }
            }

            System.out.println("----------------------------------------------------");

            BigDecimal savings = BigDecimal.ZERO;
            for(Coupon coupon: couponList) {

                if(subtotal.compareTo(coupon.getThreshold()) >= 0 ) {
                    if(savings.compareTo(coupon.getSavings()) <= 0) {
                        savings = coupon.getSavings();
                    }
                }
            }

            BigDecimal total = subtotal.subtract(savings).subtract(bulkDiscount);

            System.out.println("Subtotal: " + subtotal.setScale(2, RoundingMode.HALF_EVEN));
            System.out.println("Coupon savings: " + savings.setScale(2, RoundingMode.HALF_EVEN));
            System.out.println("Bulk discounts: " + bulkDiscount.setScale(2,RoundingMode.HALF_EVEN));
            System.out.println("----------------------------------------------------");
            System.out.println("Total: " + total.setScale(2, RoundingMode.HALF_EVEN));
            System.out.println("----------------------------------------------------");
            System.out.println("");
            System.out.println("Thank you come again!");
            System.out.println("");
        }
        else {
            throw new Exception("No open transactions!");
        }
    }

    /**
     * Adds a new unit item to the cash register.
     * Returns the item number assigned to the new item.
     *
     * @param itemName the name of the unit item
     * @param price    the price of the unit item per quanta
     * @return the item number assigned
     */
    @Override
    public int adminAddUnitItem(String itemName, BigDecimal price) {
        UnitItem unitItem = new UnitItem(itemName, price);
        int itemNumber = -1;
        try {
            itemNumber = unitItemHandler.addItem(unitItem);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return itemNumber;
    }

    /**
     * Adds a new weighted item to the cash register.
     * Returns the item number assigned to the new item.
     *
     * @param itemName   the name of the weighted item
     * @param pricePerLb the price of the weighted item per Lb
     * @return the item number assigned
     */
    @Override
    public int adminAddWeightedItem(String itemName, BigDecimal pricePerLb) {
        WeightedItem weightedItem = new WeightedItem(itemName, pricePerLb);
        int itemNumber = -1;
        try {
            itemNumber = weightedItemHandler.addItem(weightedItem);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return itemNumber;
    }

    /**
     * Sets the discount for a unit item
     *
     * @param itemNumber   item number of the unit item
     * @param bulkQuantity purchased quantity to receive discount
     * @param discount     amount discounted
     * @return
     */
    @Override
    public void adminSetUnitItemBulkDiscount(int itemNumber, int bulkQuantity, BigDecimal discount) {
        Item item = unitItemHandler.getItem(itemNumber);
        if(item instanceof UnitItem)
        {
            UnitItem unitItem = (UnitItem)item;
            unitItem.enterBulkDiscount(bulkQuantity, discount);
        }

        return;
    }

    /**
     * Prints the available items
     */
    @Override
    public void adminPrintItems() {
        System.out.println("----------------------------------------------------");
        Integer[] unitItemNumbers = unitItemHandler.getAllItemNumbers();
        System.out.println("Unit items: ");
        for(int itemNumber:unitItemNumbers) {
            System.out.println("Item Number: " + itemNumber + " Name: " + unitItemHandler.getItem(itemNumber).getName());
        }
        System.out.println("");
        Integer[] weightedItemNumbers = weightedItemHandler.getAllItemNumbers();
        System.out.println("Weighted items: ");
        for(int itemNumber:weightedItemNumbers) {
            System.out.println("Item Number: " + itemNumber + " Name: " + weightedItemHandler.getItem(itemNumber).getName());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("");
    }


}
