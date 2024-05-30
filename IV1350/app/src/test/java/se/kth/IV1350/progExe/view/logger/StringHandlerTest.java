package se.kth.IV1350.progExe.view.logger;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;
import se.kth.IV1350.progExe.model.discount.CompositeDiscount;
import se.kth.IV1350.progExe.model.discount.Discount;
import se.kth.IV1350.progExe.model.discount.NumericalDiscount;
import se.kth.IV1350.progExe.model.Sale;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class StringHandlerTest {

    private static StringHandler stringHandler;
    
    @BeforeClass
    public static void initProgExe() {
        stringHandler = new StringHandler();
    }

    
    @Test
    public void itemInfoTest() {

        ItemDTO itemDTO = new ItemDTO(15, "pear", "green", 5.00, 0.12);
        String result = stringHandler.itemInfo(itemDTO, 2);

        String header = "Add " + 2 + " item with item id: " + 15 + "\n";
        String itemID = "Item ID: " + 15 + "\n";
        String itemName = "Item name: " + "pear" + "\n";
        String itemCost = "Item cost: " + 5.00 + " SEK" + "\n";
        String itemVAT = "VAT: " + (int) (0.12 * 100) + "%" + "\n";
        String itemDesc = "Item description: " + "green" + "\n";

        String expected = header + itemID + itemName + itemCost + itemVAT + itemDesc + "\n";

        assertEquals(expected, result);
    }

    /* Test to confirm that SaleInfo output is correct. */
    @Test
    public void saleInfoTest() {

        String result = stringHandler.saleInfo(50.0, 10.0);
        String cost = "Total cost (incl VAT): " + 50.0 + " SEK" + "\n";
        String VAT = "Total VAT: " + 10.0 + " SEK" + "\n";
        String excepted = cost + VAT;

        assertEquals(excepted, result);
    }

    @Test
    public void newSaleInfoTest() {

        String result = stringHandler.newSaleInfo(1);
        String expected = "------------ Begin Sale: " + 1 +  " ------------";

        assertEquals(expected, result);
    }

    @Test
    public void endSaleInfoTest() {

        Sale sale = new Sale(85);
        sale.setSalePrice(10.0);
        sale.setSaleDiscount(5.00);
        sale.setSaleVAT(1.00);
        SaleDTO saleDTO = new SaleDTO(sale);

        String result = stringHandler.endSaleInfo(saleDTO);

        String header = "End Sale:\n";
        String discountInfo = "Total Amount of discounts: " + 5.00 + " SEK";
        String cost = "Total cost (incl VAT): " + 10.0 + " SEK" + "\n";
        String VAT = "Total VAT: " + 1.00 + " SEK" + "\n";
        String expected = header + discountInfo + cost + VAT;

        assertEquals(expected, result);
    }

    @Test
    public void paymentSuccessTest() {

        PaymentDTO paymentDTO = new PaymentDTO(85, PaymentType.CARD, 20.0, 10.0, 5.00, 40.0);
        String result = stringHandler.paymentSuccess(paymentDTO);

        String transaction = "Payment Transaction Successful.\n";
        String change = "Change to give to customer: " + 20.0 + " SEK" + "\n";
        String expected = transaction + change;
        assertEquals(expected, result);

    }

    /* Test to confirm that DiscountInfo output is correct. */
    @Test
    public void CustomerDiscountInfoTest() throws InvalidCallException {

        Discount discount = new NumericalDiscount(85, 5.00);
        String result = stringHandler.CustomerDiscountInfo(discount);
    
        String disc = "Discount for customerID: " + 85 + " is valid:";
        String discPercentage =  " (" + (int) (5.00 * 100) + "%)\n" ;
        String expected = disc + discPercentage;

        assertEquals(expected, result);
    }

    /* Test to confirm that Exception is called if DiscountInfo is invalid call. */
    @Test(expected = InvalidCallException.class)
    public void CustomerDiscountInfoInvalidCallTest() throws InvalidCallException {

        Discount discount = new CompositeDiscount();
        String result = stringHandler.CustomerDiscountInfo(discount);
    }
}
