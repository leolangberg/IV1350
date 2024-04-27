package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.DTO.*;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class scanItemTest {

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
    /**
     * Tries to scan and fetch Item with valid id.
    */
    public void scanItemValidIdTest() {

        int item_id = 10;
        String expResult = "Add 1 item with item id: 10\n" + "Item ID: 10\n" + "Item name: pear\n" + "Item cost: 5.0 SEK\n" + "VAT: 12%\n" + "Item description: green\n" + "\n" + "Total cost (incl VAT): 5.0 SEK\n" + "Total VAT: 0.6 SEK\n";
        String result = ctrl.getItem(item_id);
        String testMsg = "Scanned Item with valid id: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }

    @Test
    /**
     * Tries to scan and fetch Item with invalid id.
     */
    public void scanItemInvalidIdTest() {

        int item_id = -1;

        String expResult = "ItemID: -1 is Invalid.";
        String result = ctrl.getItem(item_id);
        String testMsg = "Scanned Item with Invalid id: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }

    @Test
    /**
     * Uses invalid Quantity ( quantity <= 0) for scanItem().
     */
    public void scanItemInvalidQuantity() {

        int item_id = 10;
        int quantity = 0;

        String expResult = "ItemID: 10 is Invalid.";
        String result = ctrl.getItem(item_id, quantity);
        String testMsg = "Scanned Item with Invalid Quantity: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
        
    }


    @Test
    /**
     * Tries more instances of same Item than there exists in database.
     */
    public void scanItemNoQuantityInDatabase() {

        int item_id = 10;
        int quantity = 2;

        String expResult = "ItemID: 10 is Invalid.";
        String result = ctrl.getItem(item_id, quantity);
        String testMsg = "Scanned Item with Higher Quantity than Inventory holds: \n" + "expResult:\n" + expResult + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }
}

