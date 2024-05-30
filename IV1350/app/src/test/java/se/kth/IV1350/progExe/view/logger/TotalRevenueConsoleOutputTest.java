package se.kth.IV1350.progExe.view.logger;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;


public class TotalRevenueConsoleOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    TotalRevenueConsoleOutput consoleOutput = new TotalRevenueConsoleOutput();


    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /* Test to confirm that display of total Income is correct. */
    @Test
    public void ShowTotalIncomeTest() {

        consoleOutput.newRevenue(5.00);
        String result = outContent.toString();
        String expected = "Total Revenue: " + 5.00 + "\n";

        assertEquals(expected, result);
    }
}
