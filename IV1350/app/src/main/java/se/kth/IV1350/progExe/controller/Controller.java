package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.*;

/**
 * The Controller class is responsible for handling all communication between
 * the view and the model.
 * 
 * This class contains methods for initiating a new sale, fetching items, ending
 * a sale, processing payments, and applying discounts.
 */
public class Controller {

    private ExternalAccountingSys externalAccountingSys;
    private ExternalInventorySys externalInventorySys;
    private ExternalDiscountSys externalDiscountSys;

    private SalesHandler salesHandler;
    private CashRegister cashRegister;
    private Printer printer;

    /**
     * Constructs a new Controller object.
     * 
     * This constructor initializes the Controller with the provided external
     * systems, Printer, and cash register.
     *
     * @param externalAccountingSys The external accounting system to be used by the
     *                              Controller.
     * @param externalInventorySys  The external inventory system to be used by the
     *                              Controller.
     * @param externalDiscountSys   The external discount system to be used by the
     *                              Controller.
     * @param printer               The Printer to be used by the Controller.
     * @param cashRegister          The cash register to be used by the Controller.
     */
    public Controller(ExternalAccountingSys externalAccountingSys,
            ExternalInventorySys externalInventorySys, ExternalDiscountSys externalDiscountSys,
            Printer printer, CashRegister cashRegister) {

        this.externalAccountingSys = externalAccountingSys;
        this.externalDiscountSys = externalDiscountSys;
        this.externalInventorySys = externalInventorySys;

        this.printer = printer;
        this.cashRegister = cashRegister;

    };

    /**
     * Initiates a new sale.
     * 
     * This method creates a new SalesHandler object with a unique ID generated by
     * the external accounting system.
     */
    public void newSale() {

        salesHandler = new SalesHandler(externalAccountingSys.newID());
    }

    /**
     * Fetches an item based on the provided item ID.
     *
     * @param itemID The ID of the item to fetch.
     * @return ItemPackageDTO contaning all relevant View Layer information
     *         (ItemDTO, Quantity, runningTotalCost, runningTotalVAT).
     */
    public ItemPackageDTO getItem(int itemID) throws OperationFailedException {

        try {
            return getItem(itemID, 1);
        } catch (OperationFailedException ope) {
            throw new OperationFailedException(ope.getMessage(), ope);
        }
    }

    /**
     * Fetches a specified quantity of an item based on the provided item ID.
     * 
     * @param itemID   The ID of the item to fetch.
     * @param quantity The quantity of the item to fetch.
     * @return ItemPackageDTO contaning all relevant View Layer information
     *         (ItemDTO, Quantity, runningTotalCost, runningTotalVAT).
     */
    public ItemPackageDTO getItem(int itemID, int quantity) throws OperationFailedException {

        try {
            ItemDTO itemDTO = externalInventorySys.getItem(itemID, quantity);
            salesHandler.addItem(itemDTO, quantity);

            SaleDTO saleDTO = salesHandler.getSaleDTO();
            ItemPackageDTO salePackageDTO = new ItemPackageDTO(itemDTO, quantity, saleDTO.getSalePrice(), saleDTO.getSaleVAT());
            return salePackageDTO;

        } catch (DatabaseConnectionException dbce) {
            throw new OperationFailedException(dbce.getMessage(), dbce);
        } catch (InvalidIdentifierException ide) {
            throw new OperationFailedException(ide.getMessage(), ide);
        } catch (InvalidQuantityException iqe) {
            throw new OperationFailedException(iqe.getMessage(), iqe);
        } catch (InvalidAddItemCallException iae) {
            throw new OperationFailedException(iae.getMessage(), iae);
        }
    }

    /**
     * Ends ongoing Sale.
     * 
     * Tells salesHandler to end current Sale.
     * The returned saleDTO is sent to check for discounts.
     * A new updated saleDTO is then returned.
     * 
     * @return saleDTO.
     */
    public SaleDTO endSale() {

        SaleDTO saleDTO = salesHandler.endSale();
        findDiscount(saleDTO);
        SaleDTO updatedSaleDTO = salesHandler.getSaleDTO();
        return updatedSaleDTO;

    }

    /**
     * Processes the payment for the current sale.
     * 
     * In case transaction succeeds then function calls updateSaleSystem which
     * updates External Systems and prints receipts.
     * PaymentDTO then returns relevant payment information.
     *
     * @param enumType The type of payment.
     * @param amountPaid The amount paid by the customer.
     * @return PaymentDTO.
     */
    public PaymentDTO Payment(PaymentType enumType, double amountPaid) throws OperationFailedException {

        PaymentDTO paymentDTO = new PaymentDTO(amountPaid, enumType, salesHandler.getSaleDTO());

        try {
            salesHandler.transaction(paymentDTO);
            updateSaleSystem();
            return paymentDTO;
        }
        catch (TransactionFailedException tfe) {
            throw new OperationFailedException(tfe.getMessage(), tfe);
        }
    }

    /**
     * Updates External Systems and logs Receipt.
     */
    private void updateSaleSystem() {

        ReceiptDTO receiptDTO = salesHandler.getReceiptDTO();
        PaymentDTO paymentDTO = receiptDTO.getReceiptPayment();
        SaleDTO saleDTO = receiptDTO.getReceiptSale();

        cashRegister.updateCashRegister(paymentDTO);
        externalAccountingSys.logReceipt(receiptDTO);
        externalInventorySys.updateItemQuantity(saleDTO.getSaleItemList());
        printer.printReceipt(receiptDTO);
    }

    /**
     * Tries to find and apply discount if it exists.
     * 
     * @param saleDTO
     */
    private void findDiscount(SaleDTO saleDTO) {
        // DiscountDTO discountDTO = externalDiscountSys.getDiscountByItemList(saleDTO.getSaleItemList());
        // salesHandler.applyDiscount(discountDTO);
    }

    /**
     * Retrieves and applies a discount based on the provided discount ID.
     *
     * @param discountID The ID of the discount to retrieve and apply.
     * @return indicating whether the discount was successfully retrieved and
     *         applied.
     */
    public void getDiscountFromID(int discountID) {

        // DiscountDTO discountDTO = externalDiscountSys.getDiscount(discountID);
        // boolean discountExists = salesHandler.applyDiscount(discountDTO);

    }
}
