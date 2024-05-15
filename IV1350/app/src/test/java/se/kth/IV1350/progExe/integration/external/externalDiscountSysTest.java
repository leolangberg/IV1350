package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.DiscountType;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.controller.OperationFailedException;

public class externalDiscountSysTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Printer printer;
    private static CashRegister cashRegister;

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
    public void setUp() throws OperationFailedException {

        ctrl.newSale();

    }

    @After
    public void tearDown() throws DatabaseException{
        ExternalInventorySys.databaseInstance().clear();
    }

    @AfterClass
    public static void termProgExe() {
        // delete all
    }

    /*
     * Test of getDiscount by id method
     */
    // @Test
    public void GetDiscountByIdTest() throws DatabaseException {

        DiscountDTO dummy = new DiscountDTO(DiscountType.NUMERAL, 10.0, 1);

        DiscountDTO test = externalDiscountSys.getDiscountByID(1);

        double expected = dummy.getDiscountValue();

        double result = test.getDiscountValue();

        assertEquals(expected, result, 0.01);
    }

    /*
     * Test of getDiscount by price method
     */
    @Test
    public void GetDiscountByTotalPriceTest() {

        double result = externalDiscountSys.getDiscountByTotalPrice(100.0);
        assertEquals(0.0, result, 0.01);
    }

    /*
     * Test of getDiscount by item list method
     */
    //@Test
    public void getDiscountTest() throws DatabaseException {

        ItemDTO testItemDTO = new ItemDTO(15, "pear", "green", 50.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(testItemDTO, 1);

        Double testresult = externalDiscountSys.getDiscountByItemList(itemList);
        assertEquals(0.0, testresult, 0.01);

    }

}
