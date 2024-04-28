package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.*;

/**
 * The Controller class is responsible for handling all communication between the view and the model.
 * 
 * This class contains methods for initiating a new sale, fetching items, ending a sale, processing payments, and applying discounts.
 */
public class Controller {

    private SalesHandler salesHandler;
    private StringHandler stringHandler;
    private Display display;
    private cashRegister cashRegister;

    private ExternalAccountingSys externalAccountingSys;
    private ExternalInventorySys externalInventorySys;
    private ExternalDiscountSys externalDiscountSys;

    /**
     * Constructs a new Controller object.
     * 
     * This constructor initializes the Controller with the provided external systems, display, and cash register.
     *
     * @param externalAccountingSys The external accounting system to be used by the Controller.
     * @param externalInventorySys The external inventory system to be used by the Controller.
     * @param externalDiscountSys The external discount system to be used by the Controller.
     * @param display The display to be used by the Controller.
     * @param cashRegister The cash register to be used by the Controller.
     */
    public Controller(ExternalAccountingSys externalAccountingSys,
            ExternalInventorySys externalInventorySys, ExternalDiscountSys externalDiscountSys,
            Display display, cashRegister cashRegister) {

        this.externalAccountingSys = externalAccountingSys;
        this.externalDiscountSys = externalDiscountSys;
        this.externalInventorySys = externalInventorySys;

        this.display = display;
        this.cashRegister = cashRegister;
        this.stringHandler = new StringHandler();

    };


    /**
     * Initiates a new sale.
     * 
     * This method creates a new SalesHandler object with a unique ID generated by the external accounting system.
     */
    public void newSale() {

        salesHandler = new SalesHandler(externalAccountingSys.newID());
    }


    /**
     * Fetches an item based on the provided item ID.
     *
     * @param itemID The ID of the item to fetch.
     * @return A String indicating whether the item was successfully retrieved and added to the sale.
     */
    public String getItem(int itemID) {

        return getItem(itemID, 1);
    }

    /**
     * Fetches a specified quantity of an item based on the provided item ID.
     * 
     * @param itemID The ID of the item to fetch.
     * @param quantity The quantity of the item to fetch.
     * @return A String indicating whether the item was successfully retrieved and added to the sale.
     */
    public String getItem(int itemID, int quantity) {

        ItemDTO itemDTO = externalInventorySys.getItem(itemID, quantity);
        if (itemDTO == null) {
            String errorMsg = "ItemID: " + itemID + " is Invalid.";
            return errorMsg;
        }

        salesHandler.addItem(itemDTO, quantity);
        String itemInfo = stringHandler.itemInfo(itemDTO, quantity);
        String saleInfo = stringHandler.saleInfo(salesHandler.getSaleDTO());

        return itemInfo + saleInfo;
    }

    /**
     * Ends ongoing Sale.
     * 
     * Tells salesHandler to end current Sale. 
     * The returned saleDTO is sent to check for discounts.
     * A new updated saleDTO is then sent to StringHandler which will return Sale Information.
     * @return A String containing Sale Information.
     */
    public String endSale() {

        SaleDTO saleDTO = salesHandler.endSale();
        findDiscount(saleDTO);
        SaleDTO updatedSaleDTO = salesHandler.getSaleDTO();
        return stringHandler.EndSaleInfo(updatedSaleDTO);

    }

    /**
     * Processes the payment for the current sale.
     * 
     * In case transaction succeeds then function calls updateSaleSystem which
     * updates External Systems, function then prints receipts and
     * returns payment information.
     *
     * @param enumType The type of payment.
     * @param amountPaid The amount paid by the customer.
     * @return A String indicating whether the payment was successful or not.
     */
    public String Payment(PaymentType enumType, double amountPaid) {

        PaymentDTO paymentDTO = new PaymentDTO(amountPaid, enumType, salesHandler.getSaleDTO());
        boolean paymentSuccess = salesHandler.transaction(paymentDTO);

        if (paymentSuccess) {
            updateSaleSystem();
            display.printReceipt(salesHandler.getReceiptDTO());
            return stringHandler.paymentSuccess(paymentDTO);
        } 
        else {
            return stringHandler.paymentFailure(paymentDTO);
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
    }

    /**
     * Tries to find and apply discount if it exists. 
     * @param saleDTO
     */
    private void findDiscount(SaleDTO saleDTO) {
        DiscountDTO discountDTO = externalDiscountSys.getDiscount(saleDTO.getSaleItemList());
        salesHandler.applyDiscount(discountDTO); 
    }

    /**
     * Retrieves and applies a discount based on the provided discount ID.
     *
     * @param discountID The ID of the discount to retrieve and apply.
     * @return indicating whether the discount was successfully retrieved and applied.
     */
    public boolean getDiscountFromID(int discountID) {

        DiscountDTO discountDTO = externalDiscountSys.getDiscount(discountID);
        boolean discountExists = salesHandler.applyDiscount(discountDTO);

        if (discountExists) {
            stringHandler.saleInfo(salesHandler.getSaleDTO());
            return true;
        } else {
            return false;
        }
    }
}
