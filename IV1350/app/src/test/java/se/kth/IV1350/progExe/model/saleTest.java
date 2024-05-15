package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.controller.OperationFailedException;

public class saleTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Printer printer;
    private static CashRegister cashRegister;
    private static Sale sale;

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
        ExternalInventorySys.databaseInstance().addItem(new ItemDTO(15, "pear", "green", 5.00, 0.12), 5);
        sale = new Sale(1);

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
     * Test for adding an item to the sale
     */
    @Test
    public void testAddItem() {

        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 5;

        sale.addItem(itemDTO, quantity);

        assertEquals(quantity, sale.getSaleItemList().get(itemDTO).intValue());
        assertEquals(itemDTO.getItemPrice() * quantity, sale.getSalePrice(), 0.001);
        assertEquals((itemDTO.getItemVAT() * itemDTO.getItemPrice()) * quantity, sale.getSaleVAT(), 0.001);
    }

    /*
     * Test for applying numeral discount
     */
    @Test
    public void testApplyNumeralDiscount() {

        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 10;

        sale.addItem(itemDTO, quantity);

        double initialTotalPrice = 100.0;
        double numeralDiscount = 10.0;

        sale.applyNumeralDiscount(numeralDiscount);

        assertEquals(initialTotalPrice - numeralDiscount, sale.getSalePrice(), 0.001);
        assertEquals(numeralDiscount, sale.getSaleDiscount(), 0.001);
    }

    /*
     * Test for applying percentage discount
     */
    @Test
    public void testApplyPercentageDiscount() {

        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 10;

        sale.addItem(itemDTO, quantity);

        double initialTotalPrice = 100.0;
        double percentageDiscount = 0.1;

        sale.applyPercentageDiscount(percentageDiscount);

        assertEquals(initialTotalPrice * (1 - percentageDiscount), sale.getSalePrice(), 0.001);
        assertEquals(initialTotalPrice * percentageDiscount, sale.getSaleDiscount(), 0.001);
    }

}