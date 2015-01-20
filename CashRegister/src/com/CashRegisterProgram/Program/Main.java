package com.CashRegisterProgram.Program;

import com.CashRegisterProgram.CashRegister.CashRegister;
import com.CashRegisterProgram.CashRegister.ItemNotFoundException;
import com.CashRegisterProgram.CashRegister.MyCashRegister;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by Henry on 1/17/2015.
 */
public class Main {

    public static void main(String[] args)   {
        CashRegister register = new MyCashRegister();
        System.out.println("*** Cash Register Started ***");

        try {
            int num = register.adminAddUnitItem("Cheerios 396g", new BigDecimal(6.99d));
            register.adminAddUnitItem("Raisin Bran 396g", new BigDecimal(4.99d));
            register.adminAddWeightedItem("Apples", new BigDecimal(2.49d));
            register.adminAddWeightedItem("Oranges", new BigDecimal(2.99d));
            register.adminSetUnitItemBulkDiscount(num, 5, new BigDecimal(0.5));

            Scanner sc = new Scanner (System.in);
            printCommands();
            do {

                if(!register.isTransactionOpen())
                    register.transactionOpen();

                if(sc.hasNextInt()) {
                    int command = sc.nextInt();
                    switch(command) {
                        case 1: System.out.println("Enter item number: ");
                                int unitItemNumber = -1;
                                if(sc.hasNextInt()) {
                                    unitItemNumber = sc.nextInt();
                                    if(!register.unitItemExists(unitItemNumber)) {
                                        System.out.println("Item does not exist!");
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Invalid item number!");
                                    sc.next();
                                    break;
                                }

                                System.out.println("Enter quantity: ");
                                int unitItemQuantity = 0;
                                if(sc.hasNextInt()) {
                                    unitItemQuantity = sc.nextInt();
                                    register.transactionPurchaseUnitItem(unitItemNumber, unitItemQuantity);
                                }
                                else {
                                    System.out.println("Invalid quantity!");
                                    sc.next();
                                }
                                break;
                        case 2: System.out.println("Enter item number: ");
                                int weightedItemNumber = -1;
                                if(sc.hasNextInt()) {
                                    weightedItemNumber = sc.nextInt();
                                    if (!register.weightedItemExists(weightedItemNumber)) {
                                        System.out.println("Item does not exist!");
                                        break;
                                    }
                                }
                                else {
                                    System.out.println("Invalid item number!");
                                    sc.next();
                                    break;
                                }
                                System.out.println("Enter weight (Lbs): ");
                                double weightedItemWeight = 0.0d;
                                if(sc.hasNextDouble()) {
                                    weightedItemWeight = sc.nextDouble();
                                    register.transactionPurchaseWeightedItem(weightedItemNumber, weightedItemWeight);
                                }
                                else {
                                    System.out.println("Invalid weight!");
                                    sc.next();
                                }
                                break;
                        case 3: System.out.println("Enter coupon savings: ");
                                BigDecimal savings = BigDecimal.ZERO;
                                BigDecimal threshold = BigDecimal.ZERO;
                                if(sc.hasNextBigDecimal()) {
                                    savings = sc.nextBigDecimal();
                                }
                                else {
                                    System.out.println("Invalid coupon savings!");
                                    sc.next();
                                    break;
                                }
                                System.out.println("Enter coupon threshold: ");
                                if(sc.hasNextBigDecimal()) {
                                    threshold = sc.nextBigDecimal();
                                    register.transactionApplyCoupon(savings, threshold);
                                }
                                else {
                                    System.out.println("Invalid coupon threshold!");
                                    sc.next();
                                }
                                break;
                        case 4: register.adminPrintItems();
                                break;
                        case 5: register.transactionPrintReceipt();
                                register.transactionClose();
                                break;
                        default:System.out.println("Invalid option!");
                                break;

                    }
                    printCommands();

                }
                else if(sc.hasNext("exit")) {
                    System.out.println("Shutting down cash register");
                    break;
                }
                else {
                    System.out.println("Invalid input!");
                    printCommands();
                    sc.next();
                }

            } while(sc.hasNext());


        }

        catch (Exception e) {
            System.out.println("Exception caught: " + e.getClass().toString());
        }




    }

    public static void printCommands() {
        System.out.println("Enter option: ");
        System.out.println("1 - Purchase unit item");
        System.out.println("2 - Purchase weighted item");
        System.out.println("3 - Use a coupon code");
        System.out.println("4 - Show all items");
        System.out.println("5 - Print receipt and start new transaction");
    }
}
