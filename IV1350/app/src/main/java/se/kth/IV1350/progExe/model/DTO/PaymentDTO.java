package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.ENUM.PaymentType;

public class PaymentDTO {

    private final int payment_id;
    private final PaymentType enumType;
    private final double totalPrice;
    private final double discountApplied;
    private final double totalVAT;
    private final double amountPaid;
    private final double change;

    /**
     * Perform payment math and logic here. change = amountpaid - totalPrice VAT should be
     * calcualted in Sale already.
     *
     */
    public PaymentDTO(int payment_id, PaymentType enumType, double totalPrice,
            double discountApplied, double totalVAT, double amountPaid) {
        this.payment_id = payment_id;
        this.enumType = enumType;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;

        this.change = amountPaid - totalPrice;

    }

    public PaymentDTO(double amountPaid, PaymentType enumType, SaleDTO saleDTO) {

        this.payment_id = saleDTO.getSaleID();
        this.enumType = enumType;
        this.totalPrice = saleDTO.getSalePrice();
        this.discountApplied = saleDTO.getSaleDiscount();
        this.totalVAT = saleDTO.getSaleVAT();
        this.amountPaid = amountPaid;

        this.change = amountPaid - totalPrice;
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


