package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.controller.OperationFailedException;

import se.kth.IV1350.progExe.logger.ConsoleLogger;
import se.kth.IV1350.progExe.logger.Logger;

import se.kth.IV1350.progExe.model.ENUM.PaymentType;


/**
 * The View class is responsible for handling all communication between the user interface and the controller.
 * 
 * This class contains methods for initiating: 
 * new sale, scanning items, ending a sale, applying discounts, and processing payments.
 * 
 * @Controller instance directing and performing tasks.
 * @Logger Error-message handler. 
 */
public class View {

    private Controller ctrl;
    private Logger logger;

    /**
     * View Constructor.
     * @param ctrl Controller object.
     */
    public View(Controller ctrl) {

        this.logger = new ConsoleLogger();
        this.ctrl = ctrl;
    }

    /**
     * Initiates a new sale.
     * 
     * This method tells the controller to start a new sale.
     */
    public void newSale() {

        ctrl.newSale();

    }

    /**
     * Ends the current sale.
     * 
     * This method tells the controller to end the current sale.
     */
    public void endSale() {

        System.out.println(ctrl.endSale());

    }

     /**
     * Scans an item.
     * 
     * This method tells the controller to get an item with the provided itemID. 
     * If the item cannot be found, Error Message will be returned.
     * 
     * @param itemID The ID of the item to be scanned.
     * @return True if the item was found, false otherwise.
     */
    public void scanItem(int itemID) {

        try {
            System.out.println(ctrl.getItem(itemID)); 

        } catch(OperationFailedException ope) {
            logger.log(ope.getMessage());
        }

    }

    /**
     * Scans an item and specifies a quantity.
     * 
     * This method tells the controller to get an item with the provided itemID and quantity. If the item cannot be found, it returns false.
     * 
     * @param itemID The ID of the item to be scanned.
     * @param quantity The quantity of the item to be scanned.
     * @return True if the item was found, false otherwise.
     */
    public void scanItem(int itemID, int quantity) {

        try {
            System.out.println(ctrl.getItem(itemID, quantity)); 

        } catch(OperationFailedException ope) {
            logger.log(ope.getMessage());
        }
    }


    /**
     * Creates a new payment.
     * 
     * This method tells the controller to process a payment with the provided PaymentType and Amount. 
     * The discount must be applied before this operation.
     * 
     * @param enumType The type of the payment.
     * @param amountPaid The amount of the payment.
     */
    public void payment(PaymentType enumType, double amountPaid) {

        System.out.println("Customer pays: " + amountPaid + " SEK");
        System.out.println(ctrl.Payment(enumType, amountPaid));
    }


     /**
     * Applies a personal discount based on a customer ID.
     * 
     * This method tells the controller to apply a discount based on the provided customerID. 
     * If there is no discount method returns false.
     * 
     * @param customerID The ID of the customer for whom the discount is to be applied.
     * @return True if the discount was applied, false otherwise.
     */
    public boolean getPersonalDiscount(int customerID) {

        return ctrl.getDiscountFromID(customerID);
    }
}
