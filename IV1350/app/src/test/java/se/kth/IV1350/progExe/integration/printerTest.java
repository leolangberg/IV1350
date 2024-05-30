package se.kth.IV1350.progExe.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.integration.external.ExternalAccountingSys;
import se.kth.IV1350.progExe.integration.external.ExternalDiscountSys;
import se.kth.IV1350.progExe.integration.external.ExternalInventorySys;
import se.kth.IV1350.progExe.model.DTO.ReceiptDTO;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.view.View;
import static org.junit.Assert.assertEquals;

public class printerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    private static View view;
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
        view = new View(ctrl);

    }

    @Before
    public void setUpStreams() {
        view.newSale();
        view.scanItem(1);
        view.scanItem(2, 5);
        view.endSale(); 
        System.setOut(new PrintStream(outContent));
        view.payment(PaymentType.CASH, 100);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /* Test to confirm that the receipt output is correct. */
    @Test
    public void printReceiptTest() {

       
        ReceiptDTO receiptDTO = ctrl.getReceiptDTO();

        String expected = "";
        String result = outContent.toString();

        expected += "Customer pays: 100.0 SEK\n";
        expected += "------------ Begin receipt ------------\n";
        expected += String.format("%-10s%8s%10s%n", "Time of Sale:", receiptDTO.getReceiptTime(), "");
        expected += "\n";

        expected += String.format("%-10s%-3s%-2s%-2s%10s SEK%n", "Banana", 5, "x", 7.0, 35.0);
        expected += String.format("%-10s%-3s%-2s%-2s%10s SEK%n", "Apple", 1, "x", 5.0, 5.0);
        expected += "\n";

        
        expected += String.format("%-10s%8s%10s SEK%n", "Brutto:", "", 40.0);
        expected += String.format("%-10s%8s%10s SEK%n", "Discount:", "", -12.0);
        expected += String.format("%-10s%8s%10s SEK%n", "Total:", "", 28.0);
        expected += String.format("%-10s%8s%10s SEK%n", "VAT:", "", 4.8);
        expected += "\n";
        expected += String.format("%-10s%8s%10s SEK%n", "Cash:", "", 100.0);
        expected += String.format("%-10s%8s%10s SEK%n", "Change:", "", 72.0);

        expected += ("------------ End receipt ------------\n");
        expected += "\n";
        expected += "Payment Transaction Successful.\n";
        expected += "Change to give to customer: 72.0 SEK\n";
        expected += "\n";

        assertEquals(expected, result);
    }
}
