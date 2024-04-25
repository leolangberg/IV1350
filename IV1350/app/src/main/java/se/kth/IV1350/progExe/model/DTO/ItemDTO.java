package se.kth.IV1350.progExe.model.DTO;


/**
 * DTO (Data Transfer Object) should be treated a read-only class (not an interface)
 * 
 */
public class ItemDTO {

    private final int item_id;
    private final String itemName;
    private final String description;
    private final double price;
    private final double VATrate;


    public ItemDTO(int item_id, String itemName, String descprition, double price, double VATrate) {
        this.item_id = item_id;
        this.itemName = itemName;
        this.description = descprition;
        this.price = price;
        this.VATrate = VATrate;

    };

    public int getItemID() {

        return item_id;
    }

    public String getItemName() {

        return itemName;
    }

    public String getItemDescription() {

        return description;
    }

    public double getItemPrice() {

        return price;
    }

    public double getItemVAT() {
        
        return VATrate;
    }

}
