package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.controller.OperationFailedException;
import se.kth.IV1350.progExe.integration.external.ExternalAccountingSys;
import se.kth.IV1350.progExe.integration.external.ExternalDiscountSys;
import se.kth.IV1350.progExe.integration.external.ExternalInventorySys;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;

public class cashRegisterTest {

    private static CashRegister cashRegister;
    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Printer printer;

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
    public void setUp() throws OperationFailedException, DatabaseException {

        ctrl.newSale();
        ExternalInventorySys.databaseInstance().addItem(new ItemDTO(10, "pear", "green", 5.00, 0.12), 5);

    }

    @After
    public void tearDown() throws DatabaseException {
        ExternalInventorySys.databaseInstance().clear();
    }

    @AfterClass
    public static void termProgExe() {
        // delete all
    }

    /*
     * Test of updateCashRegister method
     */

    @Test
    public void testUpdateCashRegister() {
        double initialCashAmount = cashRegister.cashAmount;
        double paymentPrice = 100.0;
        double paymentChange = 20.0;
        PaymentDTO paymentDTO = new PaymentDTO(1, PaymentType.CASH, 80, 0, 0.12, 100);

        cashRegister.updateCashRegister(paymentDTO);

        assertEquals(initialCashAmount + (paymentPrice - paymentChange), cashRegister.cashAmount, 0.0);
    }
}