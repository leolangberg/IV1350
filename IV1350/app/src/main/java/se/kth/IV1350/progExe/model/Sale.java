package se.kth.IV1350.progExe.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;



/**
 * Sale class
 * 
 * 
 * itemlist holds all current items totalPrice should be automatically updated totalDiscount also
 * automatic or applied
 * 
 */
public class Sale {

    private int sale_id;
    private List<ItemDTO> itemList; // turn into map with quantity
    private double totalPrice;
    private double totalVAT;
    private double totalDiscount;

    public Sale(int sale_id) {

        this.sale_id = sale_id;
        this.itemList = new ArrayList<>();

    };

    public int getSaleID() {
        
        return sale_id;
    }

    public List<ItemDTO> getSaleItemList() {

        return itemList;
    }

    public double getSalePrice() {
        
        return totalPrice;
    }

    public double getSaleVAT() {

        return totalVAT;
    }

    public double getSaleDiscount() {

        return totalDiscount;
    }


    /**
     * Adds items to item list also automatic updates to other ariables.
     */
    public void addItem(ItemDTO itemDTO) {

        itemList.add(itemDTO);
        totalPrice += itemDTO.getItemPrice() * itemDTO.getItemVAT(); // "including VAT"
        totalVAT += itemDTO.getItemVAT();

    }

    /**
     * Applies numeral discount. (totalDiscount += discount) because there can be multiple discounts
     * applied.
     */
    public void applyNumeralDiscount(double numeral) {

        this.totalDiscount += numeral;
        this.totalPrice = this.totalPrice - numeral;
    }

    /**
     * Applies percentage discount.
     */
    public void applyPercentageDiscount(double percentage) {

        this.totalDiscount *= (1 - percentage);
        this.totalPrice = this.totalPrice * (1 - percentage);
    }

}
