package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.*;

public class StringHandler {

    public StringHandler() {
    };

    public String itemInfo(ItemDTO itemDTO, int quantity) {

        String header = "Add " + quantity + " item with item id: " + itemDTO.getItemID() + "\n";
        String itemID = "Item ID: " + itemDTO.getItemID() + "\n";
        String itemName = "Item name: " + itemDTO.getItemName() + "\n";
        String itemCost = "Item cost: " + itemDTO.getItemPrice() + " SEK" + "\n";
        String itemVAT = "VAT: " + (int) (itemDTO.getItemVAT() * 100) + "%" + "\n";
        String itemDesc = "Item description: " + itemDTO.getItemDescription() + "\n";

        String itemInfo = header + itemID + itemName + itemCost + itemVAT + itemDesc + "\n";
        return itemInfo;
    }

    public String saleInfo(SaleDTO saleDTO) {

        String cost = "Total cost (incl VAT): " + saleDTO.getSalePrice() + " SEK" + "\n";
        String VAT = "Total VAT: " + saleDTO.getSaleVAT() + " SEK" + "\n";
        String saleInfo = cost + VAT;
        return saleInfo;
    }

    public String EndSaleInfo(SaleDTO saleDTO) {

        String header = "End Sale:\n";
        String saleInfo = saleInfo(saleDTO);
        return header + saleInfo;
    }

    public String paymentSuccess(PaymentDTO paymentDTO) {

        String transaction = "Payment Transaction Successful.\n";
        String change = "Change to give to customer: " + paymentDTO.getPaymentChange() + " SEK" + "\n";
        return transaction + change;
    }

    public String paymentFailure(PaymentDTO paymentDTO) {

        String transaction = "Payment Failure." + "\n";
        String cost = "Total cost (incl VAT): " + paymentDTO.getPaymentPrice() + " SEK" + "\n";
        String amountpaid = "Amount Paid: " + paymentDTO.getPaymentPaid() + " SEK" + "\n";
        return transaction + cost + amountpaid;
    }
}
