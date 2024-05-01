package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.SalesHandler;
import se.kth.IV1350.progExe.model.StringHandler;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;

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
    private static Printer printer;
    private static cashRegister cashRegister;
    private static StringHandler stringHandler;
    private static SalesHandler salesHandler;

    @BeforeClass
    public static void initProgExe() {

        externalAccountingSys = new ExternalAccountingSys();
        externalDiscountSys = new ExternalDiscountSys();
        externalInventorySys = new ExternalInventorySys();
        stringHandler = new StringHandler();
        printer = new Printer();
        cashRegister = new cashRegister();
        salesHandler = new SalesHandler(5);

        ctrl = new Controller(externalAccountingSys, externalInventorySys, externalDiscountSys, printer, cashRegister);
    }

    @Before
    public void setUp() {

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
        // delete all
    }

    /**
     * Test for newSale() method
     * ID 1 is already created in setUp()
     */
    @Test
    public void newSaleIdTest() {

        int expResult = 2; // 1 is already created in setUp()
        int result = externalAccountingSys.newID();
        assertEquals(expResult, result);

    }

    /**
     * Test for endSale() method
     */
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
    /**
     * Tries to fetch Item with valid id.
     */
    public void scanItemValidIdTest() {

        int itemID = 10;
        String expResult = "Add 1 item with item id: 10\n" + "Item ID: 10\n" + "Item name: pear\n"
                + "Item cost: 5.0 SEK\n" + "VAT: 12%\n" + "Item description: green\n" + "\n"
                + "Total cost (incl VAT): 5.0 SEK\n" + "Total VAT: 0.6 SEK\n";
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
        String testMsg = "Scanned Item with Higher Quantity than Inventory holds: \n" + "expResult:\n" + expResult
                + "Result:\n" + result;

        assertEquals(testMsg, expResult, result);
    }

    @Test
    /**
     * Tries to pay with valid payment.
     */
    public void ValidPaymentTest() {

        PaymentType paymentType = PaymentType.CASH;
        double amountPaid = 100.0;

        String result = ctrl.Payment(paymentType, amountPaid);

        assertEquals(stringHandler.paymentSuccess(new PaymentDTO(amountPaid, paymentType, salesHandler.getSaleDTO())),
                result);
    }

    @Test
    /**
     * Tries to pay with invalid payment.
     */
    public void InvalidPaymentTest() {

        ctrl.getItem(10);
        PaymentType paymentType = PaymentType.CASH;
        double amountPaid = 0.0;

        String result = ctrl.Payment(paymentType, amountPaid);

        assertEquals("Payment Failure.\n" + //
                "Total cost (incl VAT): 5.0 SEK\n" + //
                "Amount Paid: 0.0 SEK\n" + //
                "", result);
    }

    @Test
    /**
     * Tries to apply a discount with valid discount ID.
     */
    public void getDiscountFromIDValidTest() {

        int existingDiscountID = 1;
        boolean result = ctrl.getDiscountFromID(existingDiscountID);
        assertTrue(result);

    }

    @Test
    /**
     * Tries to apply a discount with invalid discount ID.
     */
    public void getDiscountFromIDInvalidTest() {

        int nonExistingDiscountID = 15;
        boolean result = ctrl.getDiscountFromID(nonExistingDiscountID);
        assertFalse(result);
    }

}
