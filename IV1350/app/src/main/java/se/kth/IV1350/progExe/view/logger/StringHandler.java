package se.kth.IV1350.progExe.view.logger;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.ItemPackageDTO;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import se.kth.IV1350.progExe.model.discount.Discount;

/**
 * Prints log messages to console (equivalent to ConsoleLogger from book).
 */
public class StringHandler {
    
    /**
     * Prints the specified string to console.
     * 
     * @param message The string that is to be printed.
     */
    public void log(String message) {
        System.out.println(message);
    } 

     /**
     * Translates ItemDTO, Quantity, runningTotalCost & runningTotalVAT into String information.
     * 
     * @param itemPackageDTO contains all relevant information to be read.
     */
    public void itemPackageInfo(ItemPackageDTO itemPackageDTO) {

        itemInfo(itemPackageDTO.getPackageItemDTO(), itemPackageDTO.getPackageItemQuantity());
        saleInfo(itemPackageDTO.getPackageRunningTotal(), itemPackageDTO.getPackageRunningVAT());
    };

     /**
     * Translates ItemDTO and Quantity into String information.
     * 
     * @param itemDTO Item to be read.
     * @param quantity Quantity to be read.
     */
    public String itemInfo(ItemDTO itemDTO, int quantity) {

        String header = "Add " + quantity + " item with item id: " + itemDTO.getItemID() + "\n";
        String itemID = "Item ID: " + itemDTO.getItemID() + "\n";
        String itemName = "Item name: " + itemDTO.getItemName() + "\n";
        String itemCost = "Item cost: " + itemDTO.getItemPrice() + " SEK" + "\n";
        String itemVAT = "VAT: " + (int) (itemDTO.getItemVAT() * 100) + "%" + "\n";
        String itemDesc = "Item description: " + itemDTO.getItemDescription() + "\n";

        String itemInfo = header + itemID + itemName + itemCost + itemVAT + itemDesc + "\n";
        log(itemInfo);
        return itemInfo;
    }

    /**
     * Translates Sale into String information.
     * 
     * @param runningTotalCost ongoing Sale current cost.
     * @param runningTotalVAT ongoing Sale current VAT.
     */
    public String saleInfo(double runningTotalCost, double runningTotalVAT) {

        String cost = "Total cost (incl VAT): " + runningTotalCost + " SEK" + "\n";
        String VAT = "Total VAT: " + runningTotalVAT + " SEK" + "\n";
        String saleInfo = cost + VAT;
        log(saleInfo);
        return saleInfo;
    }

    /**
     * Displays that a new instance of Sale has been created.
     * @param saleID id of new Sale.
     */
    public String newSaleInfo(int saleID) {
        String newSaleInfo = "------------ Begin Sale: " + saleID +  " ------------";
        log(newSaleInfo);
        return newSaleInfo;
    }

    /**
     * Translates data regarding the ending of the current Sale into String.
     * 
     * @param saleDTO Sale to be read.
     */
    public String endSaleInfo(SaleDTO saleDTO) {

        String header = "End Sale:\n";
        String discountInfo = "Total Amount of discounts: " + saleDTO.getSaleDiscount() + " SEK";
        log(header + discountInfo);
        String saleInfo = saleInfo(saleDTO.getSalePrice(), saleDTO.getSaleVAT());
        return header + discountInfo + saleInfo;
    }

    /**
     * Translates successful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    public String paymentSuccess(PaymentDTO paymentDTO) {

        String transaction = "Payment Transaction Successful.\n";
        String change = "Change to give to customer: " + paymentDTO.getPaymentChange() + " SEK" + "\n";
        log(transaction + change);
        return transaction + change;
    }

    /**
     * Shows Customer discount Applied.
     * @param discount discount found for Customer ID.
     * @throws InvalidCallException if call to retrieve discountID is invalid.
     */
    public String CustomerDiscountInfo(Discount discount) throws InvalidCallException {
        try {
            String disc = "Discount for customerID: " + discount.getDiscountID() + " is valid:";
            String discPercentage =  " (" + (int) (discount.getDiscountValue() * 100) + "%)\n" ;
            log(disc + discPercentage);
            return disc + discPercentage;
        } finally {}
    }
}