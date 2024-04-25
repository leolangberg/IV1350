package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.*;
import java.time.LocalDateTime;

/**
 * if SaleCreator can only hold 1 sale in current list then it would be like storing it temporary?
 */
public class SalesHandler {

    private Sale currentSale;
    private Payment currentPayment;
    private Receipt currentReceipt;

    private boolean saleCompleted;
    private boolean paymentCompleted;


    public SalesHandler(int sale_id) {

        currentSale = new Sale(sale_id);

        // saleCompleted = false;
        paymentCompleted = false;
    }


    /**
     * Package currentSale as a DTO.
     * 
     * @return saleDTO.
     */
    public SaleDTO getSaleDTO() {

        return new SaleDTO(currentSale);

    }


    /**
     * Creates a DTO version of current Payment.
     */
    public PaymentDTO getPaymentDTO() {

        return new PaymentDTO(currentPayment);
    }


    /**
     * Creates a DTO version of Receipt.
     */
    public ReceiptDTO getReceiptDTO() {

        return new ReceiptDTO(currentReceipt);
    }


    /**
     * Checks wether sale is completed or not
     *
     */
    public boolean addItem(ItemDTO itemDTO) {

        if (!saleCompleted) {
            currentSale.addItem(itemDTO);
            return true;
        }
        return false;
    }

    /**
     * Determines wether discount is Percentage % or Numeral. if discountDTO == null then it
     * discount does not exist and discountApplied should already automatically remain 0.
     * 
     * Returns true or false depending on if discount exists or not.
     *
     */
    public boolean applyDiscount(DiscountDTO discountDTO) {

        if (discountDTO == null) {
            return false; // discount should be 0 automatically in payment.
        }

        switch (discountDTO.getDiscountType()) {
            case PERCENTAGE:
                currentSale.applyPercentageDiscount(discountDTO.getDiscountValue());
                break;
            case NUMERAL:
                currentSale.applyNumeralDiscount(discountDTO.getDiscountValue());
                break;

            default:
                return false;
        }

        return true;
    }



    public boolean Payment(PaymentDTO paymentDTO) {

        if (!paymentCompleted) {
            currentPayment = new Payment(paymentDTO);
            paymentCompleted = true;
            saleCompleted = true; // once payment is initialized you cannot add more items.
            return true;
        }
        return false;
    }



    public boolean transaction(PaymentDTO paymentDTO) {

        if (paymentDTO.getPaymentPrice() > paymentDTO.getPaymentPaid()) {
            return false;
        } // not enough money for transaction

        currentPayment = new Payment(paymentDTO);
        currentReceipt = new Receipt(new ReceiptDTO(123, currentSale, currentPayment)); // localtime

        return true;
    }



}
