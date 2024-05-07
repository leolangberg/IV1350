package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.Sale;
import java.util.*;

/**
 * The SaleDTO class is a DTO (Data Transfer Object) that contains information about a sale.
 * 
 * @saleID This Sales unique identifier.
 * @itemList Maps each itemDTO in Sale to respective ItemID. 
 * @totalPrice Represents total Price of entire Sale (including VAT).
 * @totalVAT Represents total VAT of entire Sale (numeral). Note (itemDTO.VATrate is percentage).
 * @totalDiscount Represents total Discount sum of entire Sale (numeral).
 */
public class SaleDTO {

    private final int saleID;
    private final Map<ItemDTO, Integer> itemList;
    private final double totalPrice;
    private final double totalVAT;
    private final double totalDiscount;

    /**
     * Constructs a new SaleDTO object.
     * 
     * This constructor initializes the SaleDTO with the provided saleID, itemList, totalPrice, totalVAT, and totalDiscount.
     *
     * @param saleID The ID of the sale.
     * @param itemList The list of items in the sale, represented as a map with ItemDTO as the key and quantity as the value.
     * @param totalPrice The total price of the items in the sale.
     * @param totalVAT The total VAT applied to the items in the sale.
     * @param totalDiscount The total discount applied to the sale.
     */
    public SaleDTO(int saleID, Map<ItemDTO, Integer> itemList, double totalPrice, double totalVAT,
            double totalDiscount) {

        this.saleID = saleID;
        this.itemList = Collections.unmodifiableMap(itemList);
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalDiscount = totalDiscount;

    }

    /**
     * Constructs a new SaleDTO object from a Sale object.
     * 
     * This constructor initializes the SaleDTO with:
     * sale_id, itemList, totalPrice, totalVAT, and totalDiscount from the provided Sale object.
     *
     * @param sale The Sale object from which the SaleDTO is created.
     */
    public SaleDTO(Sale sale) {

        this.saleID = sale.getSaleID();
        this.itemList = Collections.unmodifiableMap(sale.getSaleItemList());
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
        return saleID;
    }

    /**
     * Retrieves the item list of this SaleDTO.
     * 
     * @return The itemList of this SaleDTO,
     *         represented as a map with ItemDTO as the key and quantity as the value.
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
