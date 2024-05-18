package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.discount.CompositeDiscount;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import se.kth.IV1350.progExe.model.Exceptions.TransactionFailedException;

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

    private static final CompositeDiscount COMPOSITE_DISCOUNT = new CompositeDiscount();

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

    public CompositeDiscount compositeDiscountInstance() {
        return COMPOSITE_DISCOUNT;
    }

    /**
     * Adds an observer to the current sale.
     * 
     * @param observer The observer to be added.
     */
    public void addObserver(RevenueObserver observer) {
        currentSale.addObserver(observer);
    }

    /**
     * Adds an item to the current sale if the sale is not completed.
     * 
     * @param itemDTO The item to be added to the sale.
     * @throws InvalidAddItemCallException is thrown if item is added to completed Sale.
     */
    public void addItem(ItemDTO itemDTO, int quantity) throws InvalidCallException {
        if (saleCompleted) {
            throw new InvalidCallException("Cannot Add Items to a completed Sale.");
        }

        currentSale.addItem(itemDTO, quantity);
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
     * @throws TransactionFailedException is thrown if payment is unsuccessful.
     */
    public void transaction(PaymentDTO paymentDTO) throws TransactionFailedException {

        if (paymentDTO.getPaymentPrice() > paymentDTO.getPaymentPaid()) {
            throw new TransactionFailedException("Invalid Payment.");
        }

        currentPayment = paymentDTO;
        currentReceipt = new ReceiptDTO(getSaleDTO(), paymentDTO);
    }

    /**
     * Applies Discounts onto the ongoing Sales total price.
     */
    public void applyDiscount() {
        
        double reducedTotalPrice = compositeDiscountInstance().applyDiscount(this.currentSale.getSalePrice());
        this.currentSale.setSaleDiscount(this.currentSale.getSalePrice() - reducedTotalPrice);
        this.currentSale.setSalePrice(reducedTotalPrice);
        compositeDiscountInstance().removeAll();
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
     * Completes the sale.
     */
    public void completeSale() {
        currentSale.completeSale(getSaleDTO().getSalePrice());
    }

    /**
     * Checks if the sale is completed.
     * 
     * @return True if the sale is completed, false otherwise.
     */
    public boolean isSaleCompleted() { return saleCompleted; }

}
