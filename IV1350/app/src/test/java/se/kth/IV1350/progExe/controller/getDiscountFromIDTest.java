package se.kth.IV1350.progExe.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.IV1350.progExe.integration.Display;
import se.kth.IV1350.progExe.integration.cashRegister;
import se.kth.IV1350.progExe.integration.external.ExternalAccountingSys;
import se.kth.IV1350.progExe.integration.external.ExternalDiscountSys;
import se.kth.IV1350.progExe.integration.external.ExternalInventorySys;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;

public class getDiscountFromIDTest {
    
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
    public void getDiscountFromIDTests() {

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
