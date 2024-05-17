package se.kth.IV1350.progExe.model.discount;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import se.kth.IV1350.progExe.model.Exceptions.TransactionFailedException;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class DiscountTest {


    @BeforeClass
    public static void initProgExe() {

    }

    @Before
    public void setUp() {


    }

    @After
    public void tearDown() throws DatabaseException{

    }

    @AfterClass
    public static void termProgExe() {
        // delete all
    }

    /**
     * Test to confirm applied discount is correct.
     */
    @Test
    public void ApplyDiscountTest() {

        double startSum = 20;
        Discount numericalDiscount = new NumericalDiscount(0, 10);
        double result = numericalDiscount.applyDiscount(startSum);
        double expectedResult = 10;
        
        assertEquals(expectedResult, result, 0.01);

    }

    /**
     * Test to confirm that it is not possible to ask for getMethods in
     * Composite Discount
     */
    @Test(expected = InvalidCallException.class)
    public void getMethodOnCompositeDiscount() throws InvalidCallException {
        CompositeDiscount compositeDiscount = new CompositeDiscount();
        double value = compositeDiscount.getDiscountValue();
    }

}