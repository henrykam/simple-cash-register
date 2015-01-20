package com.CashRegisterProgram.CashRegister.test;

import com.CashRegisterProgram.CashRegister.ItemNotFoundException;
import com.CashRegisterProgram.CashRegister.MyCashRegister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class MyCashRegisterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testTransactionOpen() throws Exception {
        MyCashRegister myCashRegister = new MyCashRegister();
        assertFalse(myCashRegister.isTransactionOpen());

        myCashRegister.transactionOpen();
        assertTrue(myCashRegister.isTransactionOpen());

        myCashRegister.transactionClose();
        assertFalse(myCashRegister.isTransactionOpen());
    }

    @Test
    public void testTransactionClose() throws Exception {
        MyCashRegister myCashRegister = new MyCashRegister();
        assertFalse(myCashRegister.isTransactionOpen());

        myCashRegister.transactionOpen();
        assertTrue(myCashRegister.isTransactionOpen());

        myCashRegister.transactionClose();
        assertFalse(myCashRegister.isTransactionOpen());
    }

    @Test
    public void testUnitItemExists() throws Exception {
        MyCashRegister myCashRegister = new MyCashRegister();
        assertFalse(myCashRegister.unitItemExists(12345));

        int itemNum = myCashRegister.adminAddUnitItem("NewItem", new BigDecimal(12.00));
    assertTrue(myCashRegister.unitItemExists(itemNum));
}

    @Test
    public void testWeightedItemExists() throws Exception {
        MyCashRegister myCashRegister = new MyCashRegister();
        assertFalse(myCashRegister.weightedItemExists(12345));

        int itemNum = myCashRegister.adminAddWeightedItem("NewItem", new BigDecimal(12.00));
        assertTrue(myCashRegister.weightedItemExists(itemNum));
    }

    @Test
    public void testTransactionPurchaseUnitItem() throws Exception {
        MyCashRegister myCashRegister = new MyCashRegister();
        myCashRegister.transactionOpen();
        boolean notFound = false;
        try {
            myCashRegister.transactionPurchaseUnitItem(12345, 1);
        }
        catch (ItemNotFoundException e) {
            notFound = true;
        }

        assertTrue(notFound);
        int itemNum = myCashRegister.adminAddUnitItem("NewItem", new BigDecimal(12.00));
        myCashRegister.transactionPurchaseUnitItem(itemNum, 1);
        myCashRegister.transactionPrintReceipt();
        
        assertTrue(outContent.toString().contains("NewItem"));
        assertTrue(outContent.toString().contains("12.00"));
    }

}