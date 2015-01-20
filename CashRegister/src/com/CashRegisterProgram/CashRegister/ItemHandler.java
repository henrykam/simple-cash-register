package com.CashRegisterProgram.CashRegister;

/**
 * Created by Henry on 1/18/2015.
 */
public class ItemHandler {

    ItemHandlerDao itemHandlerDao;

    public ItemHandler() {
        itemHandlerDao = new ItemHandlerDao();
    }

    public int addItem(Item item) throws Exception {
        int itemNumber = -1;
        try {
            itemNumber = itemHandlerDao.addItem(item);
        }
        catch (ItemAlreadyExistsException e) {
            System.out.println("Item already exists");
        }
        return itemNumber;
    }

    public Item getItem(int itemNumber) {
        Item item = itemHandlerDao.getItem(itemNumber);
        return item;
    }

    public boolean itemExists(int itemNumber) {
        return itemHandlerDao.itemExists(itemNumber);
    }

    public Integer[] getAllItemNumbers() {
        return itemHandlerDao.getAllItemNumbers();
    }

}
