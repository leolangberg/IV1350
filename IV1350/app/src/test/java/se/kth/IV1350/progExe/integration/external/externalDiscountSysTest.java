package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import se.kth.IV1350.progExe.model.discount.Discount;
import se.kth.IV1350.progExe.model.discount.NumericalDiscount;
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
    @Test
    public void GetDiscountByIdTest() throws DatabaseException, InvalidCallException {

        NumericalDiscount dummy = new NumericalDiscount(10, 1);
        ExternalDiscountSys.databaseInstance().storeItemDiscount(dummy);

        Discount test = ExternalDiscountSys.databaseInstance().findItemDiscount(10);

        double expected = dummy.getDiscountValue();

        double result = test.getDiscountValue();

        assertEquals(expected, result, 0.01);
    }

    /**
     * Test of Retrieving discount with Invalid ID.
     */
    @Test(expected = DatabaseException.class)
    public void GetDiscountByInvalidIdTest() throws DatabaseException {

        Discount test = ExternalDiscountSys.databaseInstance().findItemDiscount(84);
    }

    /*
     * Test of getDiscount by price method
     */
    @Test
    public void GetDiscountByTotalPriceTest() throws DatabaseException, InvalidCallException {

        Discount result = ExternalDiscountSys.databaseInstance().findCostDiscount(100);
        assertEquals(0.1, result.getDiscountValue(), 0.01);
    }

    /*
     * Test of getDiscount by item list method
     */
    //@Test
    public void getDiscountTest() throws DatabaseException, InvalidCallException {

        ItemDTO testItemDTO = new ItemDTO(15, "pear", "green", 50.00, 0.12);
        ExternalDiscountSys.databaseInstance().storeItemDiscount(new NumericalDiscount(15, 25));
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(testItemDTO, 1);


        Discount testresult = ExternalDiscountSys.databaseInstance().getDiscountByItemList(itemList);
        assertEquals(25.0, testresult.getDiscountValue(), 0.01);

    }

}
