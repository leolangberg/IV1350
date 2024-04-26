package se.kth.IV1350.progExe.startup;

import se.kth.IV1350.progExe.view.View;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;


/**
 * The Main class is the entry point of the application.
 * 
 * This class contains the main method, which initializes the application with a new View and Controller, and runs the script method. If an exception occurs, it is caught and printed to the error output stream.
 */
public class Main {

    /**
     * View should become interactive as a execute() function or running blockIntepreter process.
     */
    static View v; 


    /**
     * The main entry point for the application.
     * 
     * This method initializes the application with a new View and Controller, and runs the script method. If an exception occurs, it is caught and printed to the error output stream.
     *
     * @param args An array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        try {
            System.out.println("START");
            v = new View(new Controller(new ExternalAccountingSys(), new ExternalInventorySys(),
                    new ExternalDiscountSys(), new Display(), new cashRegister()));
            script();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * A script that simulates a sale.
     * 
     * This method simulates a sale by calling the View methods newSale, scanItem, endSale, and newPayment.
     */
    public static void script() {
        v.newSale(); //starts sale
        System.out.println();
        System.out.println("NEWSALE");
        System.out.println();
        v.scanItem(1); //scans apple (id: 1, quantity: 1)
        System.out.println();
        System.out.println("SCANITEM");
        System.out.println();
        v.scanItem(2,5); //scans banana (id: 2, quantity: 5)
        v.endSale(); //endsale;
        v.newPayment(PaymentType.CASH, 100);

    }
}
