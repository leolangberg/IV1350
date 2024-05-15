package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;

public class externalAccountingSysTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Printer printer;
    private static CashRegister cashRegister;
    private static ReceiptDTO receiptDTO;

    @BeforeClass
    public static void initProgExe() {

        externalAccountingSys = new ExternalAccountingSys();
        externalDiscountSys = new ExternalDiscountSys();
        externalInventorySys = new ExternalInventorySys();
        printer = new Printer();
        cashRegister = new CashRegister();

        ctrl = new Controller(externalAccountingSys, externalInventorySys, externalDiscountSys, printer, cashRegister);

    }

    @Before
    public void setUp() {

        ctrl.newSale();

        ItemDTO itemDTO = new ItemDTO(10, "pear", "green", 5.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(itemDTO, 1);

        externalInventorySys.database.addItem(itemDTO, 1);
    
        SaleDTO saleDTO = new SaleDTO(1, itemList, 100.0, 20.0, 10.0);

        PaymentDTO paymentDTO = new PaymentDTO(1, PaymentType.CASH, 100.0, 10.0, 20.0, 120.0);

        receiptDTO = new ReceiptDTO(saleDTO, paymentDTO);

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
     * Test of logReceipt method
     * newID method returns a new unique ID each time it's called,
     * we can test it by calling it twice and checking that the two IDs are not the
     * same.
     */
    @Test
    public void logReceiptTest() throws DatabaseException {

        externalAccountingSys.logReceipt(receiptDTO);
        ReceiptDTO loggedReceipt = ExternalAccountingSys.databaseInstance().lookupReceipt(receiptDTO.getReceiptSale().getSaleID());

        assertEquals(receiptDTO, loggedReceipt);
    }

    /*
     * Test of newID method
     * (Once a receipt is logged a new original ID shall be returned)
     */
    @Test
    public void newIDTest() throws DatabaseException {

        int id1 = externalAccountingSys.newID();
        externalAccountingSys.logReceipt(receiptDTO);
        int id2 = externalAccountingSys.newID();

        assertNotEquals(id1, id2);
    }
}
