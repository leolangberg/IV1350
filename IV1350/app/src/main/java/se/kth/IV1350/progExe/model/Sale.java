package se.kth.IV1350.progExe.model;

import java.util.HashMap;
import java.util.Map;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;



/**
 * The Sale class represents a Sale of Items.
 * 
 * @sale_id This Sales unique identifier.
 * @itemList Maps each itemDTO in Sale to respective Quantity. 
 * @totalPrice Represents total Price of entire Sale (including VAT).
 * @totalVAT Represents total VAT of entire Sale (numeral). Note (itemDTO.VATrate is percentage).
 * @totalDiscount Represents total Discount sum of entire Sale (numeral).
 */
public class Sale {

    private int sale_id;
    private Map<ItemDTO, Integer> itemList; 
    private double totalPrice;
    private double totalVAT; 
    private double totalDiscount;

    /**
     * Constructs a new Sale object.
     * 
     * This constructor initializes the Sale with the provided sale_id and an empty itemList.
     *
     * @param sale_id The ID of the sale.
     */
    public Sale(int sale_id) {

        this.sale_id = sale_id;
        this.itemList = new HashMap<>();

    };

    /**
     * Retrieves the sale ID of this Sale.
     * 
     * @return The sale_id of this Sale.
     */
    public int getSaleID() {
        
        return sale_id;
    }

    /**
     * Retrieves the item list of this Sale.
     * 
     * @return The itemList of this Sale, represented as a map with ItemDTO as the key and quantity as the value.
     */
    public Map<ItemDTO, Integer> getSaleItemList() {

        return itemList;
    }


    /**
     * Retrieves the total price of this Sale.
     * 
     * @return The totalPrice of this Sale.
     */
    public double getSalePrice() {
        return totalPrice;
    }

    /**
     * Retrieves the total VAT of this Sale.
     * 
     * @return The totalVAT of this Sale.
     */
    public double getSaleVAT() {
        return totalVAT;
    }

    /**
     * Retrieves the total discount of this Sale.
     * 
     * @return The totalDiscount of this Sale.
     */
    public double getSaleDiscount() {
        return totalDiscount;
    }

    /**
     * Adds ItemDTO to Sale.
     * Retrieves and updates current quantity of said ItemDTO in Sale.
     * 
     * Function also updates totalPrice & totalVat accordingly. 
     * (Note that totalVAT is numeral while ItemDTO.VAT is percentage). 
     */
    public void addItem(ItemDTO itemDTO) {

        int quantity = itemList.getOrDefault(itemDTO, 0);
        itemList.put(itemDTO, (quantity + 1));

        totalPrice += itemDTO.getItemPrice();
        totalVAT += (itemDTO.getItemVAT() * itemDTO.getItemPrice());

    }

    /**
     * Applies a numeral discount to the total price and total discount.
     * 
     * @param numeral The numeral discount to be applied.
     */
    public void applyNumeralDiscount(double numeral) {

        this.totalDiscount += numeral;
        this.totalPrice = this.totalPrice - numeral;
    }

    /**
     * Applies a percentage discount to the total price and total discount.
     * 
     * @param percentage The percentage discount to be applied.
     */
    public void applyPercentageDiscount(double percentage) {

        this.totalDiscount *= (1 - percentage);
        this.totalPrice = this.totalPrice * (1 - percentage);
    }
}
