package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.ENUM.PaymentType;

/**
 * The PaymentDTO class is a DTO (Data Transfer Object) that contains information about a payment.
 * 
 * This class contains the payment ID, type, total price, discount applied, total VAT, amount paid, and change.
 */
public class PaymentDTO {

    private final int paymentID;
    private final PaymentType enumType;
    private final double totalPrice;
    private final double discountApplied;
    private final double totalVAT;
    private final double amountPaid;
    private final double change;

    /**
     * Constructs a new PaymentDTO object.
     * 
     * This constructor initializes the PaymentDTO with the provided paymentID, enumType, totalPrice, discountApplied, totalVAT, and amountPaid.
     * It also calculates the change by subtracting the totalPrice from the amountPaid.
     *
     * @param paymentID The ID of the payment.
     * @param enumType The type of the payment.
     * @param totalPrice The total price of the items.
     * @param discountApplied The discount applied to the total price.
     * @param totalVAT The total VAT applied to the items.
     * @param amountPaid The amount paid by the customer.
     */
    public PaymentDTO(int paymentID, PaymentType enumType, double totalPrice,
            double discountApplied, double totalVAT, double amountPaid) {
        this.paymentID = paymentID;
        this.enumType = enumType;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;

        this.change = amountPaid - totalPrice;

    }

    /**
     * Constructs a new PaymentDTO object.
     * 
     * This constructor initializes the PaymentDTO with the provided amountPaid, enumType, and saleDTO.
     * It sets the paymentID to the saleID of the saleDTO, the totalPrice to the salePrice of the saleDTO, the discountApplied to the saleDiscount of the saleDTO, and the totalVAT to the saleVAT of the saleDTO.
     * It also calculates the change by subtracting the totalPrice from the amountPaid.
     *
     * @param amountPaid The amount paid by the customer.
     * @param enumType The type of the payment.
     * @param saleDTO The SaleDTO from which the paymentID, totalPrice, discountApplied, and totalVAT are derived.
     */
    public PaymentDTO(double amountPaid, PaymentType enumType, SaleDTO saleDTO) {

        this.paymentID = saleDTO.getSaleID();
        this.enumType = enumType;
        this.totalPrice = saleDTO.getSalePrice();
        this.discountApplied = saleDTO.getSaleDiscount();
        this.totalVAT = saleDTO.getSaleVAT();
        this.amountPaid = amountPaid;

        this.change = amountPaid - totalPrice;
    }

    /**
     * Retrieves PaymentID.
     * 
     * @return The paymentID of this PaymentDTO.
     */
    public int getPaymentID() {

        return paymentID;
    }

    /**
     * Retrieves PaymentType.
     * 
     * @return The enumType of this PaymentDTO.
     */
    public PaymentType getPaymentType() {

        return enumType;
    }

    /**
     * Retrieves Total Price.
     * 
     * @return The totalPrice of this PaymentDTO.
     */
    public double getPaymentPrice() {

        return totalPrice;
    }

    /**
     * Retrieves the discount applied.
     * 
     * @return The discountApplied of this PaymentDTO.
     */
    public double getPaymentDiscount() {

        return discountApplied;
    }

    
    /**
     * Retrieves total VAT.
     * 
     * @return The totalVAT of this PaymentDTO.
     */
    public double getPaymentVAT() {
        return totalVAT;
    }

    /**
     * Retrieves Amount Paid.
     * 
     * @return The amountPaid of this PaymentDTO.
     */
    public double getPaymentPaid() {
        return amountPaid;
    }

    /**
     * Retrieves payment Change.
     * 
     * @return The change of this PaymentDTO.
     */
    public double getPaymentChange() {
        return change;
    }

}



