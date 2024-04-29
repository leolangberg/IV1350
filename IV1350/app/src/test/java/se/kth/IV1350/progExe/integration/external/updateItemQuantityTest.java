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

public class updateItemQuantityTest {

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
        externalInventorySys.database.addItem(new ItemDTO(10, "pear", "green", 5.00, 0.12), 5);

        
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
    public void updateItemQuantityValidTest() {
        
        ItemDTO testItemDTO = new ItemDTO(10, "pear", "green", 50.00, 0.12);
        Map<ItemDTO, Integer> itemList = new HashMap<>();
        itemList.put(testItemDTO, 4);

        int initialQuantity = externalInventorySys.getItemQuantity(testItemDTO.getItemID());
        externalInventorySys.updateItemQuantity(itemList);
        int finalQuantity = externalInventorySys.getItemQuantity(testItemDTO.getItemID());
    
        assertEquals(initialQuantity - 4, finalQuantity);
        
        
        
    }
}