package se.kth.IV1350.progExe.controller;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.IV1350.progExe.integration.Display;
import se.kth.IV1350.progExe.integration.cashRegister;
import se.kth.IV1350.progExe.integration.external.ExternalAccountingSys;
import se.kth.IV1350.progExe.integration.external.ExternalDiscountSys;
import se.kth.IV1350.progExe.integration.external.ExternalInventorySys;

public class newSaleTest {

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

    @Test
    public void newSaleIdTest() {

        int expResult = 1;
        int result = externalAccountingSys.newID();
        assertEquals(expResult, result);

    }


}
