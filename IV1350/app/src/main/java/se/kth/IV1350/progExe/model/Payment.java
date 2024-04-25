package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.ENUM.PaymentType;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;

public class Payment {

    private int payment_id;
    private PaymentType enumType;
    private double totalPrice = 0;
    private double discountApplied = 0;
    private double totalVAT = 0;
    private double amountPaid = 0;
    private double change = amountPaid - totalPrice;

    public Payment(PaymentDTO paymentDTO) {
        this.payment_id = paymentDTO.getPaymentID();
        this.enumType = paymentDTO.getPaymentType();
        this.totalPrice = paymentDTO.getPaymentPrice();
        this.discountApplied = paymentDTO.getPaymentDiscount();
        this.totalVAT = paymentDTO.getPaymentVAT();
        this.amountPaid = paymentDTO.getPaymentPaid();
        this.change = paymentDTO.getPaymentChange();
    }

    public int getPaymentID() {

        return payment_id;
    }

    public PaymentType getPaymentType() {

        return enumType;
    }

    public double getPaymentPrice() {

        return totalPrice;
    }

    public double getPaymentDiscount() {

        return discountApplied;
    }

    public double getPaymentVAT() {

        return totalVAT;
    }

    public double getPaymentPaid() {

        return amountPaid;
    }

    public double getPaymentChange() {

        return change;
    }



}
