package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.controller.OperationFailedException;
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
    private StringHandler stringHandler;

    /**
     * View Constructor.
     * @param ctrl Controller object.
     */
    public View(Controller ctrl) {

        this.stringHandler = new StringHandler();
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

        stringHandler.EndSaleInfo(ctrl.endSale());

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
            stringHandler.itemPackageInfo(ctrl.getItem(itemID)); 

        } catch(OperationFailedException ope) {
            stringHandler.log(ope.getMessage());
        }

    }

    /**
     * Scans an item and specifies a quantity.
     * 
     * This method tells the controller to get an item with the provided itemID and quantity. 
     * If the item cannot be found, an Exception is thrown.
     * 
     * @param itemID The ID of the item to be scanned.
     * @param quantity The quantity of the item to be scanned.
     */
    public void scanItem(int itemID, int quantity) {
        try {
            stringHandler.itemPackageInfo(ctrl.getItem(itemID, quantity)); 

        } catch(OperationFailedException ope) {
            stringHandler.log(ope.getMessage());
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

        stringHandler.log("Customer pays: " + amountPaid + " SEK");
        try {
            stringHandler.paymentSuccess(ctrl.Payment(enumType, amountPaid));

        } catch (OperationFailedException ope) {
            stringHandler.log(ope.getMessage());
        }
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
    public void getPersonalDiscount(int customerID) {

        ctrl.getDiscountFromID(customerID);
    }
}
