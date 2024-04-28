package se.kth.IV1350.progExe.controller;

import static org.junit.Assert.assertEquals;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.IV1350.progExe.integration.Display;
import se.kth.IV1350.progExe.integration.cashRegister;
import se.kth.IV1350.progExe.integration.external.ExternalAccountingSys;
import se.kth.IV1350.progExe.integration.external.ExternalDiscountSys;
import se.kth.IV1350.progExe.integration.external.ExternalInventorySys;

public class endSaleTest {

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
    }

    @Test
    public void endSaleValidTest() {

        String expResult = "End Sale:\n" + //
                        "Total cost (incl VAT): 0.0 SEK\n" + //
                        "Total VAT: 0.0 SEK"
                        + "\n" + "";
        String result = ctrl.endSale();
        assertEquals(expResult, result);

    }
}