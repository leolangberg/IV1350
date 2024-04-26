package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.ENUM.PaymentType;

/**
 * The PaymentDTO class is a DTO (Data Transfer Object) that contains information about a payment.
 * 
 * This class contains the payment ID, type, total price, discount applied, total VAT, amount paid, and change.
 */
public class PaymentDTO {

    private final int payment_id;
    private final PaymentType enumType;
    private final double totalPrice;
    private final double discountApplied;
    private final double totalVAT;
    private final double amountPaid;
    private final double change;

    /**
     * Constructs a new PaymentDTO object.
     * 
     * This constructor initializes the PaymentDTO with the provided payment_id, enumType, totalPrice, discountApplied, totalVAT, and amountPaid.
     * It also calculates the change by subtracting the totalPrice from the amountPaid.
     *
     * @param payment_id The ID of the payment.
     * @param enumType The type of the payment.
     * @param totalPrice The total price of the items.
     * @param discountApplied The discount applied to the total price.
     * @param totalVAT The total VAT applied to the items.
     * @param amountPaid The amount paid by the customer.
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

    /**
     * Constructs a new PaymentDTO object.
     * 
     * This constructor initializes the PaymentDTO with the provided amountPaid, enumType, and saleDTO.
     * It sets the payment_id to the saleID of the saleDTO, the totalPrice to the salePrice of the saleDTO, the discountApplied to the saleDiscount of the saleDTO, and the totalVAT to the saleVAT of the saleDTO.
     * It also calculates the change by subtracting the totalPrice from the amountPaid.
     *
     * @param amountPaid The amount paid by the customer.
     * @param enumType The type of the payment.
     * @param saleDTO The SaleDTO from which the payment_id, totalPrice, discountApplied, and totalVAT are derived.
     */
    public PaymentDTO(double amountPaid, PaymentType enumType, SaleDTO saleDTO) {

        this.payment_id = saleDTO.getSaleID();
        this.enumType = enumType;
        this.totalPrice = saleDTO.getSalePrice();
        this.discountApplied = saleDTO.getSaleDiscount();
        this.totalVAT = saleDTO.getSaleVAT();
        this.amountPaid = amountPaid;

        this.change = amountPaid - totalPrice;
    }

    /**
     * Retrieves the payment ID of this PaymentDTO.
     * 
     * @return The payment_id of this PaymentDTO.
     */
    public int getPaymentID() {

        return payment_id;
    }

    /**
     * Retrieves the payment type of this PaymentDTO.
     * 
     * @return The enumType of this PaymentDTO.
     */
    public PaymentType getPaymentType() {

        return enumType;
    }

    /**
     * Retrieves the total price of this PaymentDTO.
     * 
     * @return The totalPrice of this PaymentDTO.
     */
    public double getPaymentPrice() {

        return totalPrice;
    }

    /**
     * Retrieves the discount applied to this PaymentDTO.
     * 
     * @return The discountApplied of this PaymentDTO.
     */
    public double getPaymentDiscount() {

        return discountApplied;
    }

    
    /**
     * Retrieves the total VAT of this PaymentDTO.
     * 
     * @return The totalVAT of this PaymentDTO.
     */
    public double getPaymentVAT() {
        return totalVAT;
    }

    /**
     * Retrieves the amount paid in this PaymentDTO.
     * 
     * @return The amountPaid of this PaymentDTO.
     */
    public double getPaymentPaid() {
        return amountPaid;
    }

    /**
     * Retrieves the change in this PaymentDTO.
     * 
     * @return The change of this PaymentDTO.
     */
    public double getPaymentChange() {
        return change;
    }

}



