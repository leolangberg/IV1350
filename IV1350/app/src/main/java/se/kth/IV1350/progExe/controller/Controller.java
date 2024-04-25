package se.kth.IV1350.progExe.controller;

import se.kth.IV1350.progExe.integration.*;
import se.kth.IV1350.progExe.integration.external.*;
import se.kth.IV1350.progExe.model.*;
import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.*;

public class Controller {

    private SalesHandler salesHandler;
    private Display display;
    private cashRegister cashRegister;

    private ExternalAccountingSys externalAccountingSys;
    private ExternalInventorySys externalInventorySys;
    private ExternalDiscountSys externalDiscountSys;

    public Controller(ExternalAccountingSys externalAccountingSys,
            ExternalInventorySys externalInventorySys, ExternalDiscountSys externalDiscountSys,
            Display display, cashRegister cashRegister) {

        this.externalAccountingSys = externalAccountingSys;
        this.externalDiscountSys = externalDiscountSys;
        this.externalInventorySys = externalInventorySys;

        this.display = display;
        this.cashRegister = cashRegister;

    };


    /**
     * newSale() turn into boolean?
     * 
     * Function first finds new original index from externalAccountingSystem, then initiates a
     * newSale() for the salesHandler to create. All currently ongoing sales are stores in the
     * salesHandler.
     */
    public void newSale() {

        salesHandler = new SalesHandler(externalAccountingSys.newID());

    }

    /**
     * Question is where should you place the null logic to see if said Item actually exists?
     *
     * Tries to fetch item from saleDB->externalInventory. If it does not exist return false
     * Otherwise add item to current sale in salesHandler. Then print current ItemDTO info to
     * Display.
     */
    public boolean getItem(int item_id) {

        ItemDTO itemDTO = externalInventorySys.getItem(item_id);
        if (itemDTO == null) {
            return false;
        }
        salesHandler.addItem(itemDTO);
        display.PrintItem(itemDTO);
        return true;
    }

    /**
     * For manually entering values. loop for entire quantity. if items run out of stock (null) then
     * return false.
     */
    public boolean getItem(int item_id, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (getItem(item_id) == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * Asks salesHandler to create DTO version of current Sale Display SaleDTO.
     * 
     * COULD POTENTIALLY BE NULL VALUE?
     * 
     * maybe directly link newPayment() in endSale()
     */
    public void endSale() {

        SaleDTO saleDTO = salesHandler.getSaleDTO();
        DiscountDTO discountDTO = externalDiscountSys.getDiscount(saleDTO.getSaleItemList());
        salesHandler.applyDiscount(discountDTO); // updates saleDTO

        display.PrintSale(salesHandler.getSaleDTO());

    }

    /**
     * Receives paymentType & amountPaid from View. Initiates a new Payment via paymentHandler.
     * Requirement 12. Meaning that discount must be applied before this operation.
     */
    public boolean Payment(PaymentType enumType, double amountPaid) {

        SaleDTO saleDTO = salesHandler.getSaleDTO();
        PaymentDTO paymentDTO =
                new PaymentDTO(saleDTO.getSaleID(), enumType, saleDTO.getSalePrice(),
                        saleDTO.getSaleDiscount(), saleDTO.getSaleVAT(), amountPaid);
        boolean paymentSuccess = salesHandler.transaction(paymentDTO);

        if (paymentSuccess) {
            // CREATE RECEIPT
            ReceiptDTO receiptDTO = salesHandler.getReceiptDTO();
            display.PrintReceipt(receiptDTO);
            cashRegister.updateCashRegister(amountPaid, paymentDTO.getPaymentChange());
            externalAccountingSys.logReceipt(receiptDTO);
            externalInventorySys.updateItemQuantity(saleDTO.getSaleItemList());
            return true;
        } else {
            // PAYMENT FAILURE
            return false;
        }
    }

    /**
     * Tries to find personal discount if it exists. we dont know if person has % discount or
     * numeral.
     * 
     * would this boolean be considered "logic" in controller?
     */
    public boolean getDiscountFromID(int discount_id) {

        DiscountDTO discountDTO = externalDiscountSys.getDiscount(discount_id);
        boolean discountExists = salesHandler.applyDiscount(discountDTO);

        if (discountExists) {
            display.PrintSale(salesHandler.getSaleDTO()); // print updated price after discount

            return true;
        } else {
            // discount does not exists.
            return false;
        }
    }
}
