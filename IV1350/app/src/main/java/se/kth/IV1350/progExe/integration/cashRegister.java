package se.kth.IV1350.progExe.integration;

/**
 * Cash Register is suppose to represent info gathered from customer paying for the sale. (e.g
 * Mastercard)
 * 
 * Also kind of does not make sense... view layer instead?
 */
public class cashRegister {

    private int cashier_id;
    private double cashAmount;

    public cashRegister() {

    }

    public void updateCashRegister(double amountPaid, double change) {

        cashAmount += amountPaid;
        cashAmount -= change; // return change to customer
    }

}


