package se.kth.IV1350.progExe.controller;



import se.kth.IV1350.progExe.view.View;
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

    private static View view;
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
        view = new View(ctrl);

    }

    @Before
    public void setUp() {
        externalInventorySys.database.addItem(new ItemDTO(1, "apple", "Granny Smith", 5.00, 0.12), 1);
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

        int item_id = 1;

        boolean expResult = true;
        boolean result = view.scanItem(item_id);
        assertEquals( "Scanned Item with valid id: expResult: " + expResult + "  result: " + result, expResult, result);
    }

    @Test
    /**
     * Tries to scan and fetch Item with invalid id.
     */
    public void scanItemInvalidIdTest() {

        int item_id = 2;

        boolean expResult = false;
        boolean result = view.scanItem(2);
        assertEquals("Scanned Item with invalid id: expResult: " + expResult + "  result: " + result, expResult, result);
    }

    @Test
    /**
     * Tries more instances of same Item than there exists in database.
     */
    public void scanItemNoQuantityInDatabase() {

        int item_id = 1;
        int quantity = 2;

        boolean expResult = false;
        boolean result = view.scanItem(item_id, quantity);
        assertEquals("Scan Item with no more in stock/database expResult: " + expResult + "  result: " + result, expResult, result);
    }

    @Test
    /**
     * Uses invalid Quantity ( quantity <= 0) for scanItem().
     */
    public void scanItemInvalidQuantity() {

        int item_id = 1;
        int quantity = 0;

        boolean expResult = false;
        boolean result = view.scanItem(item_id, quantity);
        assertEquals("Scan Item with invalid (<= 0) Quantity: expResult: " + expResult + "  result: " + result, expResult, result);
    }


}

