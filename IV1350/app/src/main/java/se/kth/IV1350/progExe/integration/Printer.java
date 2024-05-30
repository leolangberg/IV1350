package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.*;
import java.util.Map;

/**
 * Printer shows and prints receipt of Sale. (Printer from Book)
 * 
 */
public class Printer {

    /**
     * Initiates a new instance of Printer.
     */
    public Printer() {}


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
        double brutto = 0;

        for(Map.Entry<ItemDTO, Integer> entry :  itemlist.entrySet())
        {
            ItemDTO itemDTO = entry.getKey();
            Integer quantity = entry.getValue();
            brutto += (itemDTO.getItemPrice() * quantity);
            System.out.printf("%-10s%-3s%-2s%-2s%10s SEK%n", itemDTO.getItemName(), quantity, "x", itemDTO.getItemPrice(), (itemDTO.getItemPrice() * quantity));
        }
        System.out.println();
        System.out.printf("%-10s%8s%10s SEK%n", "Brutto:", "", brutto);
        System.out.printf("%-10s%8s%10s SEK%n", "Discount:", "", (saleDTO.getSaleDiscount() * (-1)));
        System.out.printf("%-10s%8s%10s SEK%n", "Total:", "", saleDTO.getSalePrice());
        System.out.printf("%-10s%8s%10s SEK%n", "VAT:", "", saleDTO.getSaleVAT());
        System.out.println();
        System.out.printf("%-10s%8s%10s SEK%n", "Cash:", "", paymentDTO.getPaymentPaid());
        System.out.printf("%-10s%8s%10s SEK%n", "Change:", "", paymentDTO.getPaymentChange());

        System.out.println("------------ End receipt ------------");
        System.out.println();
    }
}
