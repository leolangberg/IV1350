package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Sale class represents a Sale of Items.
 * 
 * @saleID This Sales unique identifier.
 * @itemList Maps each itemDTO in Sale to respective ItemID. 
 * @totalPrice Represents total Price of entire Sale (including VAT).
 * @totalVAT Represents total VAT of entire Sale (numeral). Note (itemDTO.VATrate is percentage).
 * @totalDiscount Represents total Discount sum of entire Sale (numeral).
 */
public class Sale {

    private int saleID;
    private Map<ItemDTO, Integer> itemList; 
    private double totalPrice;
    private double totalVAT; 
    private double totalDiscount;

    private List<RevenueObserver> observers = new ArrayList<>();
    private double revenue;

    /**
     * Constructs a new Sale object.
     * 
     * This constructor initializes the Sale with the provided sale_id and an empty itemList.
     * @param saleID The ID of the sale.
     */
    public Sale(int saleID) {

        this.saleID = saleID;
        this.itemList = new HashMap<>();

    };

     /**
     * Adds ItemDTO to Sale.
     * Retrieves and updates current quantity of said ItemDTO in Sale.
     * 
     * Function also updates totalPrice & totalVat accordingly. 
     * (Note that totalVAT is numeral while ItemDTO.VAT is percentage). 
     * @param itemDTO Item to be added.
     * @param quantity quantity of said Item.
     */
    public void addItem(ItemDTO itemDTO, int quantity) {

        int CurrentQuantity = itemList.getOrDefault(itemDTO, 0);
        itemList.put(itemDTO, (CurrentQuantity + quantity));

        totalPrice += itemDTO.getItemPrice() * quantity;
        totalVAT += (itemDTO.getItemVAT() * itemDTO.getItemPrice()) * quantity;

    }

    /**
     * Sets a new Sale price.
     * @param price new Sale price.
     */
    public void setSalePrice(double price) { this.totalPrice = price; }

    /**
     * Sets a new Sale discount (Amount).
     * @param discountAmount total amount of discounts.
     */
    public void setSaleDiscount(double discountAmount) { this.totalDiscount = discountAmount; }

    /**
     * Retrieves the sale ID of this Sale.
     * 
     * @return The saleID of this Sale.
     */
    public int getSaleID() { return saleID; }

    /**
     * Retrieves the item list of this Sale.
     * 
     * @return The itemList of this Sale, represented as a map with ItemDTO as the key and quantity as the value.
     */
    public Map<ItemDTO, Integer> getSaleItemList() { return itemList; }

    /**
     * Retrieves the total price of this Sale.
     * 
     * @return The totalPrice of this Sale.
     */
    public double getSalePrice() { return totalPrice; }

    /**
     * Retrieves the total VAT of this Sale.
     * 
     * @return The totalVAT of this Sale.
     */
    public double getSaleVAT() { return totalVAT; }

    /**
     * Retrieves the total discount of this Sale.
     * 
     * @return The totalDiscount of this Sale.
     */
    public double getSaleDiscount() { return totalDiscount; }

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
     * Applies a percentage discount to the total price and updates the total discount.
     * 
     * @param percentage The percentage discount to be applied.
     */
    public void applyPercentageDiscount(double percentage) {
        double discountAmount = this.totalPrice * percentage;
        this.totalDiscount += discountAmount;
        this.totalPrice -= discountAmount;
    }

    /**
     * Adds a new observer to the list of observers.
     * 
     * @param observer The observer to be added.
     */
    public void addObserver(RevenueObserver observer) {
        observers.add(observer);
    }

    /**
     * Completes the sale by adding the amount to the total revenue and notifying the observers.
     * 
     * @param amount The amount to be added to the total revenue.
     */
    public void completeSale(double amount) {
        revenue += amount;
        notifyObservers(amount);
    }

    /**
     * Notifies all observers about the new revenue.
     * 
     * @param amount The amount of the new revenue.
     */
    private void notifyObservers(double amount) {
        for (RevenueObserver observer : observers) {
            observer.newRevenue(amount);
        }
    }


}
