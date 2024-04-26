package se.kth.IV1350.progExe.model.DTO;


/**
 * The ItemDTO class is a DTO (Data Transfer Object) that contains information about an item.
 * 
 * This class contains the item ID, name, description, price, and VAT rate.
 */
public class ItemDTO {

    private final int item_id;
    private final String itemName;
    private final String description;
    private final double price;
    private final double VATrate;


    /**
     * Constructs a new ItemDTO object.
     * 
     * This constructor initializes the ItemDTO with the provided item_id, itemName, description, price, and VATrate.
     *
     * @param item_id The ID of the item.
     * @param itemName The name of the item.
     * @param description The description of the item.
     * @param price The price of the item.
     * @param VATrate The VAT rate of the item.
     */
    public ItemDTO(int item_id, String itemName, String descprition, double price, double VATrate) {
        this.item_id = item_id;
        this.itemName = itemName;
        this.description = descprition;
        this.price = price;
        this.VATrate = VATrate;

    };


    /**
     * Retrieves the item ID of this ItemDTO.
     * 
     * @return The item_id of this ItemDTO.
     */
    public int getItemID() {

        return item_id;
    }

    /**
     * Retrieves the item name of this ItemDTO.
     * 
     * @return The itemName of this ItemDTO.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Retrieves the item description of this ItemDTO.
     * 
     * @return The description of this ItemDTO.
     */
    public String getItemDescription() {
        return description;
    }

    /**
     * Retrieves the item price of this ItemDTO.
     * 
     * @return The price of this ItemDTO.
     */
    public double getItemPrice() {
        return price;
    }

    /**
     * Retrieves the VAT rate of this ItemDTO.
     * 
     * @return The VATrate of this ItemDTO.
     */
    public double getItemVAT() {
        return VATrate;
    }

}
