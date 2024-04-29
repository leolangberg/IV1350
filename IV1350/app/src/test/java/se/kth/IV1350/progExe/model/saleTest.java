package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.integration.external.*;


import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;

public class saleTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Display display;
    private static cashRegister cashRegister;
    private static Sale sale;

    
    
   

    ExternalAccountingSys.AccountingSysDatabase database = externalAccountingSys.database;
    ExternalAccountingSys.AccountingSysDatabase.linkedListStruct linkedList = database.receiptlog;



    @BeforeClass
    public static void initProgExe() {

        externalAccountingSys = new ExternalAccountingSys();
        externalDiscountSys = new ExternalDiscountSys();
        externalInventorySys = new ExternalInventorySys();
        display = new Display();
        cashRegister = new cashRegister();

        ctrl = new Controller(externalAccountingSys, externalInventorySys, externalDiscountSys, display, cashRegister);
    
    }

    @Before
    public void setUp() {
        
        ctrl.newSale();
        externalInventorySys.database.addItem(new ItemDTO(15, "pear", "green", 5.00, 0.12), 5);
        sale = new Sale(1);
        
    }

    @After
    public void tearDown() {
        externalInventorySys.database.clear();
    }

    @AfterClass
    public static void termProgExe() {
        //delete all
    }


    @Test
    public void testAddItem() {

        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 5;

        sale.addItem(itemDTO, quantity);

        assertEquals(quantity, sale.getSaleItemList().get(itemDTO).intValue()); // Convert Integer to int
        assertEquals(itemDTO.getItemPrice() * quantity, sale.getSalePrice(), 0.001); // Add delta value for comparing floating-point numbers
        assertEquals((itemDTO.getItemVAT() * itemDTO.getItemPrice()) * quantity, sale.getSaleVAT(), 0.001); // Add delta value for comparing floating-point numbers
    }

 
    @Test
    public void testApplyNumeralDiscount() {
        
        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 10;

        sale.addItem(itemDTO, quantity);
        

        double initialTotalPrice = 100.0;
        double numeralDiscount = 10.0;

        sale.applyNumeralDiscount(numeralDiscount);

        assertEquals(initialTotalPrice - numeralDiscount, sale.getSalePrice(), 0.001); // Add delta value for comparing floating-point numbers
        assertEquals(numeralDiscount, sale.getSaleDiscount(), 0.001); // Add delta value for comparing floating-point numbers
    }

    @Test
    public void testApplyPercentageDiscount() {

        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 10;

        sale.addItem(itemDTO, quantity);
        
        double initialTotalPrice = 100.0;
        double percentageDiscount = 0.1; // 10%

        sale.applyPercentageDiscount(percentageDiscount);

        assertEquals(initialTotalPrice * (1 - percentageDiscount), sale.getSalePrice(), 0.001); // Add delta value for comparing floating-point numbers
        assertEquals(initialTotalPrice * percentageDiscount, sale.getSaleDiscount(), 0.001); // Add delta value for comparing floating-point numbers
    }

}