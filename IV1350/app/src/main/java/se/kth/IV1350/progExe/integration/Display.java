package se.kth.IV1350.progExe.integration;

import se.kth.IV1350.progExe.model.DTO.*;

/**
 * Display showing ongoing sale, scanned items & price. (Printer from Book)
 * 
 */
public class Display {


    public Display() {}

    /**
     * Program also presents item's description, price, and running total (including VAT)
     */
    public void PrintItem(ItemDTO itemDTO) {

        String item_id = "item_id: " + itemDTO.getItemID();
        String itemName = "itemName: " + itemDTO.getItemName();
        String itemPrice = "price: " + itemDTO.getItemPrice();
        String itemVAT = "VAT: " + itemDTO.getItemVAT();
        String itemDesc = "Description: " + itemDTO.getItemDescription();

        System.out.println(
                item_id + " " + itemName + " " + itemPrice + " " + itemVAT + " " + itemDesc);

    }

    public void PrintSale(SaleDTO saleDTO) {

        String salePrice = "price: " + saleDTO.getSalePrice();
        String saleVAT = "VAT: " + saleDTO.getSaleVAT();

        System.out.println("SALE: " + "price: " + salePrice + " " + "VAT:" + saleVAT);
    }

    public void PrintPayment(PaymentDTO paymentDTO) {

        String payment;
    }

    public void PrintReceipt(ReceiptDTO receiptDTO) {

        String time = "time: " + receiptDTO.getReceiptTime();
        String payment;
    }
}
