package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.DiscountType;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.integration.external.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;

public class salesHandlerTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Printer printer;
    private static cashRegister cashRegister;
    private static SalesHandler salesHandler;

    private int saleID = 1;

    ExternalAccountingSys.AccountingSysDatabase database = externalAccountingSys.database;
    ExternalAccountingSys.AccountingSysDatabase.linkedListStruct linkedList = database.receiptlog;

    @BeforeClass
    public static void initProgExe() {

        externalAccountingSys = new ExternalAccountingSys();
        externalDiscountSys = new ExternalDiscountSys();
        externalInventorySys = new ExternalInventorySys();
        printer = new Printer();
        cashRegister = new cashRegister();

        ctrl = new Controller(externalAccountingSys, externalInventorySys, externalDiscountSys, printer, cashRegister);

    }

    @Before
    public void setUp() {

        salesHandler = new SalesHandler(saleID);

    }

    @After
    public void tearDown() {
        externalInventorySys.database.clear();
    }

    @AfterClass
    public static void termProgExe() {
        // delete all
    }

    /*
     * Test case for the SalesHandler constructor
     */
    @Test
    public void testSalesHandlerConstructor() {
        assertNotNull(salesHandler.getSaleDTO());
        assertEquals(saleID, salesHandler.getSaleDTO().getSaleID());
        assertFalse(salesHandler.isSaleCompleted());
    }

    /*
     * Test case for when the sale is completed
     */
    @Test
    public void testEndSale() {
        SaleDTO saleDTO = salesHandler.endSale();

        assertTrue(salesHandler.isSaleCompleted());
        assertNotNull(saleDTO);
        assertEquals(saleID, saleDTO.getSaleID());
    }

    /*
     * Test case for when the sale is not completed
     */
    @Test
    public void testTransaction() {
        PaymentDTO paymentDTO = new PaymentDTO(1, PaymentType.CASH, 100.0, 10.0, 20.0, 120.0);

        boolean transactionResult = salesHandler.transaction(paymentDTO);

        assertTrue(transactionResult);
        assertEquals(paymentDTO, salesHandler.getPaymentDTO());
        assertNotNull(salesHandler.getReceiptDTO());
    }

    /*
     * Test case for when the payment is not enough
     */
    @Test
    public void testTransactionNotEnoughMoney() {
        PaymentDTO paymentDTO = new PaymentDTO(1, PaymentType.CASH, 200.0, 10.0, 20.0, 120.0);

        boolean transactionResult = salesHandler.transaction(paymentDTO);

        assertFalse(transactionResult);
        assertNull(salesHandler.getPaymentDTO());
        assertNull(salesHandler.getReceiptDTO());
    }

    /*
     * Test case for when the discount is a numeral discount
     */
    @Test
    public void testApplyDiscount() {
        DiscountDTO discountDTO = new DiscountDTO(DiscountType.PERCENTAGE, 0.1);

        boolean result = salesHandler.applyDiscount(discountDTO);

        assertTrue(result);
        assertEquals(salesHandler.getSaleDTO().getSaleDiscount(),
                salesHandler.getSaleDTO().getSalePrice() * discountDTO.getDiscountValue(), 0.0001);
    }

    /*
     * Test case for when the discount is null
     */
    @Test
    public void testApplyDiscountNull() {
        boolean result = salesHandler.applyDiscount(null);

        assertFalse(result);
    }

}