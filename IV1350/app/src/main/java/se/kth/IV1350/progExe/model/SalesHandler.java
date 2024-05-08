package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.*;

/**
 * The SalesHandler class is responsible for handling sales.
 * 
 * @currentSale represents ongoing Sale. 
 * @currentPayment represent payment connected to ongoing Sale.
 * @currentRecepit represent receipt connected to ongoing Sale.
 * 
 * @saleCompleted is boolean which locks current Sale from adding more items.
 */
public class SalesHandler {

    private Sale currentSale;
    private PaymentDTO currentPayment;
    private ReceiptDTO currentReceipt;

    private boolean saleCompleted;

    /**
     * Constructs a new SalesHandler object.
     * 
     * This constructor initializes the SalesHandler with a new Sale object with the
     * provided saleID and sets saleCompleted to false.
     *
     * @param saleID The ID of the sale.
     */
    public SalesHandler(int saleID) {

        currentSale = new Sale(saleID);
        saleCompleted = false;
    }

    /**
     * Packages the currentSale as a SaleDTO.
     * 
     * @return A SaleDTO object representing the currentSale.
     */
    public SaleDTO getSaleDTO() { return new SaleDTO(currentSale); }

    /**
     * Retrieves the current PaymentDTO.
     * 
     * @return The currentPayment of this SalesHandler.
     */
    public PaymentDTO getPaymentDTO() { return currentPayment; }

    /**
     * Retrieves the current ReceiptDTO.
     * 
     * @return The currentReceipt of this SalesHandler.
     */
    public ReceiptDTO getReceiptDTO() { return currentReceipt; }

    /**
     * Adds an item to the current sale if the sale is not completed.
     * 
     * @param itemDTO The item to be added to the sale.
     * @return True if the item was added, false otherwise.
     */
    public boolean addItem(ItemDTO itemDTO, int quantity) {
        if (saleCompleted) {
            return false;
        }

        currentSale.addItem(itemDTO, quantity);
        return true;
    }

    /**
     * Ends the current sale.
     * 
     * This method sets saleCompleted to true and returns the current SaleDTO.
     * @return The current SaleDTO.
     */
    public SaleDTO endSale() {

        saleCompleted = true;
        return getSaleDTO();
    }

    /**
     * Initiates a payment transaction.
     * 
     * This method checks if the payment is valid and creates a new ReceiptDTO if
     * the payment is successful.
     * 
     * @param paymentDTO The payment to be made.
     * @return True if the payment was successful, false otherwise.
     */
    public boolean transaction(PaymentDTO paymentDTO) {

        if (paymentDTO.getPaymentPrice() > paymentDTO.getPaymentPaid()) {
            return false;
        }

        currentPayment = paymentDTO;
        currentReceipt = new ReceiptDTO(getSaleDTO(), paymentDTO);

        return true;
    }

    /**
     * Applies a discount to the current sale.
     * 
     * This method applies a discount to the current sale based on the provided
     * DiscountDTO.
     * 
     * @param discountDTO The discount to be applied.
     * @return True if the discount was applied, false otherwise.
     */
    public boolean applyDiscount(DiscountDTO discountDTO) {

        if (discountDTO == null) {
            return false; 
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

    /**
     * Checks if the sale is completed.
     * 
     * @return True if the sale is completed, false otherwise.
     */
    public boolean isSaleCompleted() {
        return saleCompleted;
    }

}
