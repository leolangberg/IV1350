package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.*;
import java.time.LocalDateTime;

/**
 * if SaleCreator can only hold 1 sale in current list then it would be like storing it temporary?
 */
public class SalesHandler {

    private Sale currentSale;
    private PaymentDTO currentPayment;
    private ReceiptDTO currentReceipt;

    private boolean saleCompleted;


    public SalesHandler(int sale_id) {

        currentSale = new Sale(sale_id);
        saleCompleted = false;
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

        return currentPayment;
    }


    /**
     * Creates a DTO version of Receipt.
     */
    public ReceiptDTO getReceiptDTO() {

        return currentReceipt;
    }


    /**
     * Adds ItemDTOs to current Sale. 
     * Checks wether sale is completed or not
     */
    public boolean addItem(ItemDTO itemDTO) {

        if (!saleCompleted) {
            currentSale.addItem(itemDTO);
            return true;
        }
        return false;
    }

    public SaleDTO endSale() {

        saleCompleted = true;
        return getSaleDTO();
    }

    public boolean transaction(PaymentDTO paymentDTO) {

        if (paymentDTO.getPaymentPrice() > paymentDTO.getPaymentPaid()) {
            return false;
        } // not enough money for transaction

        currentPayment = paymentDTO;
        currentReceipt = new ReceiptDTO(getSaleDTO(), paymentDTO);

        return true;
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
}
