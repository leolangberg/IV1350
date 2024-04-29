package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;

public class getItemTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Display display;
    private static cashRegister cashRegister;
    
   

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
        externalInventorySys.database.addItem(new ItemDTO(10, "pear", "green", 5.00, 0.12), 1);

        
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
    public void getDiscountValidTest() {
        

        ItemDTO result = externalInventorySys.database.getItem(10, 1);

        ItemDTO expResult = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        
        assertEquals(expResult.getItemID(), result.getItemID());
        assertEquals(expResult.getItemName(), result.getItemName());
        assertEquals(expResult.getItemDescription(), result.getItemDescription());
        assertEquals(expResult.getItemPrice(), result.getItemPrice(), 0.01);
        assertEquals(expResult.getItemVAT(), result.getItemVAT(), 0.01);
        
        
    }

}