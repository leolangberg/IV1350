package se.kth.IV1350.progExe.integration.external;


import se.kth.IV1350.progExe.integration.*;
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

public class externalDiscountSysTest {

    private static Controller ctrl;
    private static ExternalAccountingSys externalAccountingSys;
    private static ExternalDiscountSys externalDiscountSys;
    private static ExternalInventorySys externalInventorySys;
    private static Display display;
    private static cashRegister cashRegister;
    
   

    ExternalAccountingSys.AccountingSysDatabase database = externalAccountingSys.database;
    ExternalAccountingSys.AccountingSysDatabase.linkedListStruct linkedList = database.receiptlog;



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

    @After
    public void tearDown() {
        externalInventorySys.database.clear();
    }

    @AfterClass
    public static void termProgExe() {
        //delete all
    }

    // Test of getDiscount by id method
    @Test
    public void GetDiscountByIdTest() {
       
       
        DiscountDTO dummy = new DiscountDTO(DiscountType.NUMERAL, 10.0, 1);
        
        DiscountDTO test = externalDiscountSys.getDiscount(1);

        double expected = dummy.getDiscountValue();

        double result = test.getDiscountValue();

        assertEquals(expected, result, 0.01);
    }

    // Test of getDiscount by price method
    @Test
    public void GetDiscountByTotalPriceTest() {

        double result = externalDiscountSys.getDiscount(100.0);
        assertEquals(0.0, result, 0.01);
    }

    // Test of getDiscount method
    @Test
    public void getDiscountTest() {

        ItemDTO testItemDTO = new ItemDTO(15, "pear", "green", 50.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(testItemDTO, 1);

        DiscountDTO testresult = externalDiscountSys.getDiscount(itemList);

        assertEquals(10, testresult.getDiscountValue(), 0.01);
        assertEquals(DiscountType.NUMERAL, testresult.getDiscountType());
        assertEquals(50, testresult.getDiscountID());
        
    }


}
