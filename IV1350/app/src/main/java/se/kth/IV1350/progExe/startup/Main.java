package se.kth.IV1350.progExe.startup;

import se.kth.IV1350.progExe.view.View;
import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;


public class Main {

    /**
     * View should become interactive as a execute() function or running blockIntepreter process.
     */
    static View v; 

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
