package se.kth.IV1350.progExe.startup;

import se.kth.IV1350.progExe.view.View;
import se.kth.IV1350.progExe.view.logger.TotalRevenueConsoleOutput;
import se.kth.IV1350.progExe.view.logger.TotalRevenueFileOutput;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.CashRegister;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.HigherGradeTask2.*;

/**
 * The Main class is the entry point of the application.
 * 
 * This class contains the main method, which initializes the application with a
 * new View and Controller, and runs the script method. If an exception occurs,
 * it is caught and printed to the error output stream.
 */
public class Main {

    static View v;

    /**
     * The main entry point for the application.
     * 
     * This method initializes the application with a new View and Controller, and
     * runs the script method. If an exception occurs, it is caught and printed to
     * the error output stream.
     *
     * @param args An array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        try {
            Controller controller = new Controller(
                new ExternalAccountingSys(),
                new ExternalInventorySys(),
                new ExternalDiscountSys(),
                new Printer(),
                new CashRegister()
            );

            TotalRevenueConsoleOutput revenueView = new TotalRevenueConsoleOutput();
            TotalRevenueFileOutput fileOutput = new TotalRevenueFileOutput();
            controller.addObserver(revenueView);
            controller.addObserver(fileOutput);

            v = new View(controller);
            script();

            //Printouts for Higher Grade Task 2.
            /*
            Inheritance inheritance = new Inheritance();
            Composition composition = new Composition();
            System.out.println("diceroll 1: comp: " + composition.diceRoll() + "  inh: " + inheritance.diceRoll());
            System.out.println("diceroll 2: comp: " + composition.diceRoll() + "  inh: " + inheritance.diceRoll());
            System.out.println("diceroll 3: comp: " + composition.diceRoll() + "  inh: " + inheritance.diceRoll());
            */
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * A script that simulates a sale.
     * 
     * This method simulates a sale by calling the View methods newSale, scanItem,
     * endSale, and newPayment.
     */
    public static void script() {
        // First sale
        v.newSale();
        v.scanItem(1);
        //v.scanItem(-1);
        v.scanItem(-99);
        v.scanItem(2, 5);
        v.endSale(); 
        v.payment(PaymentType.CARD, 10);
        v.payment(PaymentType.CASH, 100);
        // Second sale
        v.newSale();
        v.scanItem(1);
        v.scanItem(2, 5);
        v.endSale(); 
        v.payment(PaymentType.CASH, 100);
    }
}
