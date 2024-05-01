package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.*;
import java.util.Map;

/**
 * Display shows and prints receipt of Sale. (Printer from Book)
 * 
 */
public class Display {

    /**
     * Initiates a new instance of Display.
     */
    public Display() {}


    /**
     * Prints the details of a receipt.
     * 
     * This method prints the time of the sale, the items in the sale, the total VAT, the total cost (including VAT), 
     * the amount paid by the customer in SEK, and the change to give the customer in SEK.
     * 
     * @param receiptDTO The ReceiptDTO whose details are to be printed.
     */
    public void printReceipt(ReceiptDTO receiptDTO) {

        System.out.println("------------ Begin receipt ------------");
        System.out.printf("%-10s%8s%10s%n", "Time of Sale:", receiptDTO.getReceiptTime(), "");
        System.out.println();

        SaleDTO saleDTO = receiptDTO.getReceiptSale();
        PaymentDTO paymentDTO = receiptDTO.getReceiptPayment();
        Map<ItemDTO, Integer> itemlist = saleDTO.getSaleItemList();

        for(Map.Entry<ItemDTO, Integer> entry :  itemlist.entrySet())
        {
            ItemDTO itemDTO = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.printf("%-10s%-3s%-2s%-2s%10s SEK%n", itemDTO.getItemName(), quantity, "x", itemDTO.getItemPrice(), (itemDTO.getItemPrice() * quantity));
        }

        System.out.printf("%-10s%8s%10s SEK%n", "Total:", "", saleDTO.getSalePrice());
        System.out.printf("%-10s%8s%10s SEK%n", "VAT:", "", saleDTO.getSaleVAT());
        System.out.println();
        System.out.printf("%-10s%8s%10s SEK%n", "Cash:", "", paymentDTO.getPaymentPaid());
        System.out.printf("%-10s%8s%10s SEK%n", "Change:", "", paymentDTO.getPaymentChange());

        System.out.println("------------ End receipt ------------");
        System.out.println();
    }


    /**
     * Prints the details of an update to the external inventory.
     * 
     * This method prints a message indicating that sale info has been sent to the external accounting system,
     * and then prints the details of each item in the sale, including the item and the quantity,
     * indicating that the external inventory system has been told to decrease the inventory quantity of each item.
     * 
     * @param saleDTO The SaleDTO whose details are to be printed.
     */
    public void printUpdateExternalInventory(SaleDTO saleDTO) {

        System.out.println("Sent sale info to external accounting system.");

        Map<ItemDTO, Integer> itemlist = saleDTO.getSaleItemList();
        for(Map.Entry<ItemDTO, Integer> entry :  itemlist.entrySet())
        {
            ItemDTO itemDTO = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println("Told external inventory system to decrease inventory quantity of item: " + itemDTO + " by " + quantity + "units.");
        }
        System.out.println();
        
    }
}
