package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.Map;
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
    public void tearDown() throws DatabaseException {
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

    @Test
    public void unchangableItemListTest() {
        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 1;
        sale.addItem(itemDTO, quantity);

        SaleDTO saleDTO = new SaleDTO(sale);
        sale.addItem(itemDTO, quantity);

        int saleDTOlistlength = 0;
        int salelistlength = 0;
        for (Map.Entry<ItemDTO, Integer> entry : saleDTO.getSaleItemList().entrySet()) {
            System.out.println("saleDTO" + "key: " + entry.getKey() + "  val: " + entry.getValue());
            saleDTOlistlength += (entry.getValue());
        }
        for(Map.Entry<ItemDTO, Integer> entry : sale.getSaleItemList().entrySet()) {
            System.out.println("sale: " + "key: " + entry.getKey() + "  val: " + entry.getValue());
            salelistlength += (entry.getValue());
        }
        assertNotEquals(salelistlength, saleDTOlistlength);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addToUnchangableItemListTest() {
        ItemDTO itemDTO = new ItemDTO(20, "popcorn", "cheddar", 10.00, 0.12);
        int quantity = 1;
        sale.addItem(itemDTO, quantity);

        SaleDTO saleDTO = new SaleDTO(sale);
        saleDTO.getSaleItemList().put(itemDTO, quantity);

    }
}