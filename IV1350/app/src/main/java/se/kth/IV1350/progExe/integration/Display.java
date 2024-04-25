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
     * Program also presents item's description, price, and running total (including VAT)
     */
    public void printItem(ItemDTO itemDTO) {

        System.out.println("Add 1 item with item id: " + itemDTO.getItemID());
        System.out.println("Item ID: " + itemDTO.getItemID());
        System.out.println("Item name:" + itemDTO.getItemName());
        System.out.println("Item cost: " + itemDTO.getItemPrice());
        System.out.println("VAT:" + itemDTO.getItemVAT());
        System.out.println("Item description: " + itemDTO.getItemDescription());
        System.out.println();

    }


    public void printSale(SaleDTO saleDTO) {

        System.out.println("Total cost (incl VAT): " + saleDTO.getSalePrice() + " SEK");
        System.out.println("Total VAT: " + saleDTO.getSaleVAT() + " SEK");
        System.out.println();
    }


    public void printEndSale(SaleDTO saleDTO) {
        System.out.println("End sale:");
        printSale(saleDTO);
    }


    public void printPayment(PaymentDTO paymentDTO) {

        System.out.println("Customer pays " + paymentDTO.getPaymentPaid() + " SEK");
        System.out.println();
    }


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


    public void printPaymentFailure(PaymentDTO paymentDTO) {

        System.out.println("PAYMENT FAILED");
        System.out.println("Total cost (incl VAT): " + paymentDTO.getPaymentPrice() + " SEK");
        System.out.println("Amount paid: " + paymentDTO.getPaymentPaid() + " SEK");
        System.out.println();
    }


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
        System.out.println("VAT: " + saleDTO.getSaleVAT());
        System.out.printf("%-10s%8s%10s SEK%n", "Cash:", "", paymentDTO.getPaymentPaid());
        System.out.printf("%-10s%8s%10s SEK%n", "Change:", "", paymentDTO.getPaymentChange());

        System.out.println("------------ End receipt ------------");
        System.out.println();
    }

    public void printReturnedChange(double change) {

        System.out.println("Change to give the customer: " + change + " SEK");
    }
}
