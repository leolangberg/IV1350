package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.*;
import java.util.Map;

/**
 * Display showing ongoing sale, scanned items & price. (Printer from Book)
 * 
 */
public class Display {


    public Display() {}

    /**
     * Prints the details of an item.
     * 
     * This method prints the ID, name, cost, VAT, and description of the provided ItemDTO.
     * 
     * @param itemDTO The ItemDTO whose details are to be printed.
     */
    public void printItem(ItemDTO itemDTO) {

        System.out.println("Add 1 item with item id: " + itemDTO.getItemID());
        System.out.println("Item ID: " + itemDTO.getItemID());
        System.out.println("Item name: " + itemDTO.getItemName());
        System.out.println("Item cost: " + itemDTO.getItemPrice());
        System.out.println("VAT:" + itemDTO.getItemVAT());
        System.out.println("Item description: " + itemDTO.getItemDescription());
        System.out.println();

    }

    /**
     * Prints the details of a sale.
     * 
     * This method prints the total cost (including VAT) and total VAT of the provided SaleDTO in SEK.
     * 
     * @param saleDTO The SaleDTO whose details are to be printed.
     */
    public void printSale(SaleDTO saleDTO) {

        System.out.println("Total cost (incl VAT): " + saleDTO.getSalePrice() + " SEK");
        System.out.println("Total VAT: " + saleDTO.getSaleVAT() + " SEK");
        System.out.println();
    }


    /**
     * Prints the details of a sale at the end of the sale.
     * 
     * This method prints "End sale:" and then calls printSale with the provided SaleDTO.
     * 
     * @param saleDTO The SaleDTO whose details are to be printed.
     */
    public void printEndSale(SaleDTO saleDTO) {
        System.out.println("End sale:");
        printSale(saleDTO);
    }


    /**
     * Prints the details of a payment.
     * 
     * This method prints the amount paid by the customer in SEK.
     * 
     * @param paymentDTO The PaymentDTO whose details are to be printed.
     */
    public void printPayment(PaymentDTO paymentDTO) {

        System.out.println("Customer pays " + paymentDTO.getPaymentPaid() + " SEK");
        System.out.println();
    }


    /**
     * Prints the details of an update to the external inventory.
     * 
     * This method prints a message indicating that sale info has been sent to the external accounting system, and then prints the details of each item in the sale, including the item and the quantity, indicating that the external inventory system has been told to decrease the inventory quantity of each item.
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


    /**
     * Prints the details of a payment failure.
     * 
     * This method prints "PAYMENT FAILED" and then prints the total cost (including VAT) and the amount paid by the customer in SEK.
     * 
     * @param paymentDTO The PaymentDTO whose details are to be printed.
     */
    public void printPaymentFailure(PaymentDTO paymentDTO) {

        System.out.println("PAYMENT FAILED");
        System.out.println("Total cost (incl VAT): " + paymentDTO.getPaymentPrice() + " SEK");
        System.out.println("Amount paid: " + paymentDTO.getPaymentPaid() + " SEK");
        System.out.println();
    }


    /**
     * Prints the details of a receipt.
     * 
     * This method prints the time of the sale, the items in the sale, the total cost (including VAT), the VAT, the amount paid by the customer in SEK, and the change to give the customer in SEK.
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
     * Prints the details of the returned change.
     * 
     * This method prints the amount of change to give the customer in SEK.
     * 
     * @param change The amount of change to give the customer in SEK.
     */
    public void printReturnedChange(double change) {

        System.out.println("Change to give the customer: " + change + " SEK");
    }
}
