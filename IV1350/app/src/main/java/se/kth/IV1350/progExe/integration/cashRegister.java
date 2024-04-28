package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.PaymentDTO;

/**
 * Cash Register is supposed to represent info gathered from customer paying for the sale. (e.g
 * Mastercard)
 * 
 */
public class cashRegister {

    int cashier_id;
    double cashAmount;

    /**
     * Constructs a new cashRegister object.
     * 
     * This constructor initializes the cashRegister with a cashAmount of 0.
     * Currently holds dummy data. 
     */
    public cashRegister() {
        this.cashier_id = 1;
        this.cashAmount = 500;
    }

    /**
     * Updates the cash register based on the amount paid and the change.
     * @param amountPaid The amount paid by the customer.
     * @param change The change returned to the customer.
     */
    public void updateCashRegister(PaymentDTO paymentDTO) {
        
        updateCashAmount(paymentDTO.getPaymentPrice());
        returnChange(paymentDTO.getPaymentChange());
        
    }

    /**
     * Updates Cash Amount in Register.
     */
    private void updateCashAmount(double amount) {
        this.cashAmount += amount;
    }

    /*
     * Tells Hardware the correct amount change to return to Customer.
     */
    private void returnChange(double change) {
        //Hardware call.
    }

}


