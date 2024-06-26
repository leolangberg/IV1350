package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.model.SalesHandler;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

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
    private static CashRegister cashRegister;
    private static SalesHandler salesHandler;

    @BeforeClass
    public static void initProgExe() {

        externalAccountingSys = new ExternalAccountingSys();
        externalDiscountSys = new ExternalDiscountSys();
        externalInventorySys = new ExternalInventorySys();
        printer = new Printer();
        cashRegister = new CashRegister();
        salesHandler = new SalesHandler(5);

        ctrl = new Controller(externalAccountingSys, externalInventorySys, externalDiscountSys, printer, cashRegister);
    }

    @Before
    public void setUp() throws OperationFailedException, DatabaseException  {

        ctrl.newSale();
        ExternalInventorySys.databaseInstance().addItem(new ItemDTO(10, "pear", "green", 5.00, 0.12), 1);

    }

    @After
    public void tearDown() throws DatabaseException, OperationFailedException {
        ExternalInventorySys.databaseInstance().clear();
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
    public void newSaleIdTest() throws DatabaseException {

        int expResult = 2; // 1 is already created in setUp()
        int result = externalAccountingSys.newID();
        assertEquals(expResult, result);

    }

    /**
     * Test for endSale() method
     */
    @Test
    public void endSaleTest() throws OperationFailedException {

        ctrl.getItem(10);

        SaleDTO result = ctrl.endSale();

        ItemDTO item = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(item, 1);
        double totalCost = 5.00;
        double totalVAT = 0.60;
        double totalDiscount = 0.0;
        int saleID = 2;
        SaleDTO expResult = new SaleDTO(saleID, itemList, totalCost, totalVAT, totalDiscount);

        assertEquals(expResult.getSaleID(), result.getSaleID());
        assertEquals(expResult.getSalePrice(), result.getSalePrice(), 0.0001);
        assertEquals(expResult.getSaleVAT(), result.getSaleVAT(), 0.0001);
        assertEquals(expResult.getSaleDiscount(), result.getSaleDiscount(), 0.0001);
    }

    /**
     * Tries to fetch Item with valid id.
     */
    @Test
    public void scanItemValidIdTest() throws OperationFailedException {
        int itemID = 10;
        ItemDTO item = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        int quantity = 1;
        double runningTotalCost = 5.00;
        double runningTotalVAT = 0.60;
        ItemPackageDTO expResult = new ItemPackageDTO(item, quantity, runningTotalCost, runningTotalVAT);

        ItemPackageDTO result = ctrl.getItem(itemID);

        assertEquals(expResult.getPackageItemDTO().getItemID(), result.getPackageItemDTO().getItemID());
        assertEquals(expResult.getPackageItemQuantity(), result.getPackageItemQuantity());
        assertEquals(expResult.getPackageRunningTotal(), result.getPackageRunningTotal(), 0.0001);
        assertEquals(expResult.getPackageRunningVAT(), result.getPackageRunningVAT(), 0.0001);
    }

    @Test(expected = OperationFailedException.class)
    /**
     * Tries to fetch Item with invalid id.
     */
    public void scanItemInvalidIdTest() throws OperationFailedException {
        int itemID = -1;
        ctrl.getItem(itemID);

    }

    @Test(expected = OperationFailedException.class)
    /**
     * Uses invalid Quantity ( quantity <= 0) for getItem().
     */
    public void scanItemInvalidQuantity() throws OperationFailedException {
        int itemID = 10;
        int quantity = 0;
        ctrl.getItem(itemID, quantity);

    }

    @Test(expected =  OperationFailedException.class)
    /**
     * Tries to fetch more instances of same Item than there exists in database.
     */
    public void scanItemNoQuantityInDatabase() throws OperationFailedException {
        int itemID = 10;
        int quantity = 2;
        ItemPackageDTO result = ctrl.getItem(itemID, quantity);
    }

    /**
     * Tries to pay with valid payment.
     */
    @Test
    public void ValidPaymentTest() throws OperationFailedException{

        ctrl.getItem(10);
        PaymentType paymentType = PaymentType.CASH;
        double amountPaid = 100.0;

        ItemDTO item = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(item, 1);
        double totalCost = 5.00;
        double totalVAT = 0.60;
        double totalDiscount = 0.0;
        int saleID = 1;
        SaleDTO DummySale = new SaleDTO(saleID, itemList, totalCost, totalVAT, totalDiscount);

        PaymentDTO result = ctrl.Payment(paymentType, amountPaid);

        double expectedChange = amountPaid - 5.0;
        PaymentDTO expectedResult = new PaymentDTO(amountPaid, paymentType, DummySale);

        assertEquals(expectedResult.getPaymentPrice(), result.getPaymentPrice(), 0.01);
        assertEquals(expectedResult.getPaymentDiscount(), result.getPaymentDiscount(), 0.01);
        assertEquals(expectedResult.getPaymentVAT(), result.getPaymentVAT(), 0.01);
        assertEquals(expectedResult.getPaymentPaid(), result.getPaymentPaid(), 0.01);
        assertEquals(expectedResult.getPaymentChange(), result.getPaymentChange(), 0.01);
    }

    /**
     * Tries to pay with invalid payment.
     */
    @Test(expected = OperationFailedException.class)

    public void InvalidPaymentTest() throws OperationFailedException {

        ctrl.getItem(10);
        PaymentType paymentType = PaymentType.CASH;
        double amountPaid = 0.0;

        ItemDTO item = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(item, 1);
        double totalCost = 5.00;
        double totalVAT = 0.60;
        double totalDiscount = 0.0;
        int saleID = 1;
        SaleDTO DummySale = new SaleDTO(saleID, itemList, totalCost, totalVAT, totalDiscount);

        PaymentDTO result = ctrl.Payment(paymentType, amountPaid);

        double expectedChange = amountPaid - 5.0;
        PaymentDTO expectedResult = new PaymentDTO(amountPaid, paymentType, DummySale);

        assertEquals(expectedResult.getPaymentPrice(), result.getPaymentPrice(), 0.01);
        assertEquals(expectedResult.getPaymentDiscount(), result.getPaymentDiscount(), 0.01);
        assertEquals(expectedResult.getPaymentVAT(), result.getPaymentVAT(), 0.01);
        assertEquals(expectedResult.getPaymentPaid(), result.getPaymentPaid(), 0.01);
        assertEquals(expectedResult.getPaymentChange(), result.getPaymentChange(), 0.01);
    }

}
