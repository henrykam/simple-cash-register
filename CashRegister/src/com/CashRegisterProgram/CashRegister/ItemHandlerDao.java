package com.CashRegisterProgram.CashRegister;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Henry on 1/18/2015.
 */
public class ItemHandlerDao {

    HashMap<Integer, Item> itemHashMap;

    public ItemHandlerDao() {
        itemHashMap = new HashMap<Integer, Item>();
    }

    public int addItem(Item item) throws Exception {
        int itemNumber = item.getName().hashCode();
        item.setNumber(itemNumber);
        if(!itemHashMap.containsKey(itemNumber))
            itemHashMap.put(itemNumber,item);
        else
            throw new Exception("Item already exists");

        return itemNumber;
    }

    public Item getItem(int itemNumber) {
        return itemHashMap.get(itemNumber);
    }

    public boolean itemExists(int itemNumber) {
        return itemHashMap.containsKey(itemNumber);
    }

    public Integer[] getAllItemNumbers() {
        Set<Integer> itemNumberSet = itemHashMap.keySet();
        return itemNumberSet.toArray(new Integer[itemNumberSet.size()]);
    }

}
