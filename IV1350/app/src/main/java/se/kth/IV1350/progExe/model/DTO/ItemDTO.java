package se.kth.IV1350.progExe.model.DTO;


/**
 * The ItemDTO class is a DTO (Data Transfer Object) that contains information about an item.
 * 
 * This class contains the item ID, name, description, price, and VAT rate.
 * Please Note, VATrate is percentage.
 */
public class ItemDTO {

    private final int itemID;
    private final String itemName;
    private final String description;
    private final double price;
    private final double VATrate;


    /**
     * Constructs a new ItemDTO object.
     * 
     * This constructor initializes the ItemDTO with the provided itemID, itemName, description, price, and VATrate.
     *
     * @param itemID The ID of the item.
     * @param itemName The name of the item.
     * @param description The description of the item.
     * @param price The price of the item.
     * @param VATrate The VAT rate of the item (percentage).
     */
    public ItemDTO(int itemID, String itemName, String descprition, double price, double VATrate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.description = descprition;
        this.price = price;
        this.VATrate = VATrate;

    };


    /**
     * Retrieves ItemID.
     * 
     * @return The itemID of this ItemDTO.
     */
    public int getItemID() { return itemID; }

    /**
     * Retrieves Item name.
     * 
     * @return The itemName of this ItemDTO.
     */
    public String getItemName() { return itemName; }

    /**
     * Retrieves Item description.
     * 
     * @return The description of this ItemDTO.
     */
    public String getItemDescription() { return description; }

    /**
     * Retrieves Item Price.
     * 
     * @return The price of this ItemDTO.
     */
    public double getItemPrice() { return price; }

    /**
     * Retrieves Item VATrate.
     * 
     * @return The VATrate of this ItemDTO (percentage).
     */
    public double getItemVAT() { return VATrate; }

}
