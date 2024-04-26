package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;


/**
 * The View class is responsible for handling all communication between the user interface and the controller.
 * 
 * This class contains methods for initiating a new sale, scanning items, ending a sale, applying discounts, and processing payments.
 */
public class View {

    private Controller ctrl;

    /**
     * View Constructor.
     * @param ctrl Controller object.
     */
    public View(Controller ctrl) {

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

        ctrl.endSale();

    }

     /**
     * Scans an item.
     * 
     * This method tells the controller to get an item with the provided item_id. If the item cannot be found, it returns false.
     * 
     * @param item_id The ID of the item to be scanned.
     * @return True if the item was found, false otherwise.
     */
    public boolean scanItem(int item_id) {

        return ctrl.getItem(item_id);

    }

    /**
     * Scans an item and specifies a quantity.
     * 
     * This method tells the controller to get an item with the provided item_id and quantity. If the item cannot be found, it returns false.
     * 
     * @param item_id The ID of the item to be scanned.
     * @param quantity The quantity of the item to be scanned.
     * @return True if the item was found, false otherwise.
     */
    public boolean scanItem(int item_id, int quantity) {
        return ctrl.getItem(item_id, quantity);
    }

    /**
     * Updates the quantity of an item.
     * 
     * This method tells the controller to update the quantity of an item with the provided item_id. If the item cannot be found, it returns false.
     * 
     * @param item_id The ID of the item to be updated.
     * @param quantity The new quantity of the item.
     * @return True if the item was found and the quantity was updated, false otherwise.
     */
    public boolean updateItemQuantity(int item_id, int quantity) {
        return ctrl.getItem(item_id, quantity);
    }

    /**
     * Applies a personal discount based on a customer ID.
     * 
     * This method tells the controller to apply a discount based on the provided customer_id. If the discount cannot be applied, it returns false.
     * 
     * @param customer_id The ID of the customer for whom the discount is to be applied.
     * @return True if the discount was applied, false otherwise.
     */
    public boolean getPersonalDiscount(int customer_id) {
        return ctrl.getDiscountFromID(customer_id);
    }

    /**
     * Creates a new payment.
     * 
     * This method tells the controller to process a payment with the provided PaymentType and amount. The discount must be applied before this operation.
     * 
     * @param enumType The type of the payment.
     * @param amountPaid The amount of the payment.
     */
    public void newPayment(PaymentType enumType, double amountPaid) {
        ctrl.Payment(enumType, amountPaid);
    }
}
