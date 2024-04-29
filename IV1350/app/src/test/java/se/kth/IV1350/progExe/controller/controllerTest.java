package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.DTO.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class controllerTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Display display;
    private static cashRegister cashRegister;

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

        externalInventorySys.database.clear();
        ctrl.newSale();
        externalInventorySys.database.addItem(new ItemDTO(10, "pear", "green", 5.00, 0.12), 1);
        
    }

    @After
    public void tearDown() {
        externalInventorySys.database.clear();
        ctrl.endSale();
    }

    @AfterClass
    public static void termProgExe() {
        //delete all
    }


    @Test
    public void newSaleIdTest() {

        int expResult = 2; // Sale ID starts at 1, so the second sale should have ID 2.
        int result = externalAccountingSys.newID();
        assertEquals(expResult, result);

    }


    @Test
    /**
     * Tries to fetch Item with valid id.
    */
    public void scanItemValidIdTest() {

        int itemID = 10;
        String expResult = "Add 1 item with item id: 10\n" + "Item ID: 10\n" + "Item name: pear\n" + "Item cost: 5.0 SEK\n" + "VAT: 12%\n" + "Item description: green\n" + "\n" + "Total cost (incl VAT): 5.0 SEK\n" + "Total VAT: 0.6 SEK\n";
        String result = ctrl.getItem(itemID);
        String testMsg = "Scanned Item with valid id: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }

    @Test
    /**
     * Tries to fetch Item with invalid id.
     */
    public void scanItemInvalidIdTest() {

        int itemID = -1;

        String expResult = "ItemID: -1 is Invalid.";
        String result = ctrl.getItem(itemID);
        String testMsg = "Scanned Item with Invalid id: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }

    @Test
    /**
     * Uses invalid Quantity ( quantity <= 0) for getItem().
     */
    public void scanItemInvalidQuantity() {

        int itemID = 10;
        int quantity = 0;

        String expResult = "ItemID: 10 is Invalid.";
        String result = ctrl.getItem(itemID, quantity);
        String testMsg = "Scanned Item with Invalid Quantity: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
        
    }


    @Test
    /**
     * Tries to fetch more instances of same Item than there exists in database.
     */
    public void scanItemNoQuantityInDatabase() {

        int itemID = 10;
        int quantity = 2;

        String expResult = "ItemID: 10 is Invalid.";
        String result = ctrl.getItem(itemID, quantity);
        String testMsg = "Scanned Item with Higher Quantity than Inventory holds: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }


    @Test
    public void endSaleTest() {

        
        String expResult = "End Sale:\n" + //
                        "Total cost (incl VAT): 0.0 SEK\n" + //
                        "Total VAT: 0.0 SEK"
                        + "\n" + "";
        String result = ctrl.endSale();
        assertEquals(expResult, result);

    }


    @Test
    public void ValidPaymentTest() {

        double amount = 100;
    }




    @Test
    public void getDiscountFromIDTest() {

        // getDiscountFromID method returns true if the discount exists and false otherwise,
        // we can test it by calling it with a discount ID that we know exists and one that we know doesn't exist.

        // Test with a discount ID that exists
        int existingDiscountID = 1; // discount ID that exists
        boolean result = ctrl.getDiscountFromID(existingDiscountID);
        assertTrue(result);

        // Test with a discount ID that doesn't exist
        int nonExistingDiscountID = 15; // discount ID that doesn't exist
        result = ctrl.getDiscountFromID(nonExistingDiscountID);
        assertFalse(result);
    }

}

