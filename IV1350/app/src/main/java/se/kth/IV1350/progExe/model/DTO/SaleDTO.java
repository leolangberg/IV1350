package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.Sale;
import java.util.*;

public class SaleDTO {

    private final int sale_id;
    private final List<ItemDTO> itemList;
    private final double totalPrice;
    private final double totalVAT;
    private final double totalDiscount;

    public SaleDTO(int sale_id, List<ItemDTO> itemList, double totalPrice, double totalVAT,
            double totalDiscount) {

        this.sale_id = sale_id;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalDiscount = totalDiscount;

    }

    /*
     * Direct method to turn Sale into SaleDTO
     */
    public SaleDTO(Sale sale) {

        this.sale_id = sale.getSaleID();
        this.itemList = sale.getSaleItemList();
        this.totalPrice = sale.getSalePrice();
        this.totalVAT = sale.getSaleVAT();
        this.totalDiscount = sale.getSaleDiscount();

    }

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


}
