package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.ItemPackageDTO;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;

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
    public void itemInfo(ItemDTO itemDTO, int quantity) {

        String header = "Add " + quantity + " item with item id: " + itemDTO.getItemID() + "\n";
        String itemID = "Item ID: " + itemDTO.getItemID() + "\n";
        String itemName = "Item name: " + itemDTO.getItemName() + "\n";
        String itemCost = "Item cost: " + itemDTO.getItemPrice() + " SEK" + "\n";
        String itemVAT = "VAT: " + (int) (itemDTO.getItemVAT() * 100) + "%" + "\n";
        String itemDesc = "Item description: " + itemDTO.getItemDescription() + "\n";

        String itemInfo = header + itemID + itemName + itemCost + itemVAT + itemDesc + "\n";
        log(itemInfo);
    }

    /**
     * Translates Sale into String information.
     * 
     * @param runningTotalCost ongoing Sale current cost.
     * @param runningTotalVAT ongoing Sale current VAT.
     */
    public void saleInfo(double runningTotalCost, double runningTotalVAT) {

        String cost = "Total cost (incl VAT): " + runningTotalCost + " SEK" + "\n";
        String VAT = "Total VAT: " + runningTotalVAT + " SEK" + "\n";
        String saleInfo = cost + VAT;
        log(saleInfo);
    }

    /**
     * Translates data regarding the ending of the current Sale into String.
     * 
     * @param saleDTO Sale to be read.
     */
    public void EndSaleInfo(SaleDTO saleDTO) {

        String header = "End Sale:\n";
        log(header);
        saleInfo(saleDTO.getSalePrice(), saleDTO.getSaleVAT());
    }

    /**
     * Translates successful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    public void paymentSuccess(PaymentDTO paymentDTO) {

        String transaction = "Payment Transaction Successful.\n";
        String change = "Change to give to customer: " + paymentDTO.getPaymentChange() + " SEK" + "\n";
        log(transaction + change);
    }

     /**
     * Translates unsuccessful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    public void paymentFailure(PaymentDTO paymentDTO) {

        String transaction = "Payment Failure." + "\n";
        String cost = "Total cost (incl VAT): " + paymentDTO.getPaymentPrice() + " SEK" + "\n";
        String amountpaid = "Amount Paid: " + paymentDTO.getPaymentPaid() + " SEK" + "\n";
        log(transaction + cost + amountpaid);
    }
}