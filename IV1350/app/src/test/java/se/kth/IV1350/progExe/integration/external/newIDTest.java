package se.kth.IV1350.progExe.integration.external;

import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.kth.IV1350.progExe.integration.Display;
import se.kth.IV1350.progExe.integration.cashRegister;
import se.kth.IV1350.progExe.controller.Controller;

public class newIDTest {

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
        ctrl.endSale();
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
    public void newIDValidTest() {

        // newID method returns a new unique ID each time it's called,
        // we can test it by calling it twice and checking that the two IDs are not the same.

        int id1 = externalAccountingSys.newID();
        int id2 = externalAccountingSys.newID();

        assertNotEquals(id1, id2);
    }


}
