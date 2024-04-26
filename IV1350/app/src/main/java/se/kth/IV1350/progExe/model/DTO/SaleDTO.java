package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.Sale;
import java.util.*;

/**
 * The SaleDTO class is a DTO (Data Transfer Object) that contains information about a sale.
 * 
 * This class contains the sale ID, item list, total price, total VAT, and total discount of the sale.
 */
public class SaleDTO {

    private final int sale_id;
    private final Map<ItemDTO, Integer> itemList;
    private final double totalPrice;
    private final double totalVAT;
    private final double totalDiscount;

    /**
     * Constructs a new SaleDTO object.
     * 
     * This constructor initializes the SaleDTO with the provided sale_id, itemList, totalPrice, totalVAT, and totalDiscount.
     *
     * @param sale_id The ID of the sale.
     * @param itemList The list of items in the sale, represented as a map with ItemDTO as the key and quantity as the value.
     * @param totalPrice The total price of the items in the sale.
     * @param totalVAT The total VAT applied to the items in the sale.
     * @param totalDiscount The total discount applied to the sale.
     */
    public SaleDTO(int sale_id, Map<ItemDTO, Integer> itemList, double totalPrice, double totalVAT,
            double totalDiscount) {

        this.sale_id = sale_id;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalDiscount = totalDiscount;

    }

    /**
     * Constructs a new SaleDTO object from a Sale object.
     * 
     * This constructor initializes the SaleDTO with the sale_id, itemList, totalPrice, totalVAT, and totalDiscount from the provided Sale object.
     *
     * @param sale The Sale object from which the SaleDTO is created.
     */
    public SaleDTO(Sale sale) {

        this.sale_id = sale.getSaleID();
        this.itemList = sale.getSaleItemList();
        this.totalPrice = sale.getSalePrice();
        this.totalVAT = sale.getSaleVAT();
        this.totalDiscount = sale.getSaleDiscount();

    }


    /**
     * Retrieves the sale ID of this SaleDTO.
     * 
     * @return The sale_id of this SaleDTO.
     */
    public int getSaleID() {
        return sale_id;
    }

    /**
     * Retrieves the item list of this SaleDTO.
     * 
     * @return The itemList of this SaleDTO, represented as a map with ItemDTO as the key and quantity as the value.
     */
    public Map<ItemDTO, Integer> getSaleItemList() {
        return itemList;
    }

    /**
     * Retrieves the total price of this SaleDTO.
     * 
     * @return The totalPrice of this SaleDTO.
     */
    public double getSalePrice() {
        return totalPrice;
    }

    /**
     * Retrieves the total VAT of this SaleDTO.
     * 
     * @return The totalVAT of this SaleDTO.
     */
    public double getSaleVAT() {
        return totalVAT;
    }

    /**
     * Retrieves the total discount of this SaleDTO.
     * 
     * @return The totalDiscount of this SaleDTO.
     */
    public double getSaleDiscount() {
        return totalDiscount;
    }


}
