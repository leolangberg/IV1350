package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.controller.Controller;
import se.kth.IV1350.progExe.model.ENUM.PaymentType;


public class View {

    private Controller ctrl;

    /**
     * View Constructor.
     */
    public View(Controller ctrl) {

        this.ctrl = ctrl;
    }

    /**
     * View tells controller to start new sale. (Cashier presses button on PoS).
     */
    public void newSale() {

        ctrl.newSale();

    }

    /**
     * Where does the payment come from? view or controller?
     */
    public void endSale() {

        ctrl.endSale();

    }

    /**
     * scanItem makes ctrl.getItem() which then returns a boolean. If return false it is indicating
     * that the scanned item cannot be found.
     * 
     */
    public boolean scanItem(int item_id) {

        return ctrl.getItem(item_id);

    }

    /**
     * Enter item manually.
     */
    public boolean scanItem(int item_id, int quantity) {

        return ctrl.getItem(item_id, quantity);
    }

    /**
     * UpdateQuantity() is just the same as entering manually? Otherwise how is it suppose to know
     * which item to update?
     */
    public boolean updateItemQuantity(int item_id, int quantity) {

        return ctrl.getItem(item_id, quantity);
    }

    /**
     * If customer wants to apply discount based on customer_id.
     */
    public boolean getPersonalDiscount(int customer_id) {

        return ctrl.getDiscountFromID(customer_id);
    }

    /**
     * Create a new payment. Requirement 12. Meaning that discount must be applied before this
     * operation.
     */
    public void newPayment(PaymentType enumType, double amountPaid) {

        ctrl.Payment(enumType, amountPaid);
    }
}
