package se.kth.IV1350.progExe.model.DTO;

/**
 * Intended as return package for View Layer containing
 * all relevant ongoing Item and Sale Information.
 */
public class ItemPackageDTO {
    
    private final ItemDTO itemDTO;
    private final int quantity;
    private final double runningTotalCost;
    private final double runningTotalVAT;

    /**
     * Creates a new SalePackageDTO.
     * 
     * @param itemDTO Item to be transferred.
     * @param quantity quantity of Item.
     * @param runningTotalCost ongoing total cost (inc. VAT)
     * @param runningTotalVAT ongoing total VAT.
     */
    public ItemPackageDTO(ItemDTO itemDTO, int quantity, double runningTotalCost, double runningTotalVAT) {

        this.itemDTO = itemDTO;
        this.quantity = quantity;
        this.runningTotalCost = runningTotalCost;
        this.runningTotalVAT = runningTotalVAT;
    }

    public ItemDTO getPackageItemDTO() { return itemDTO; }

    public int getPackageItemQuantity() { return quantity; }

    public double getPackageRunningTotal() { return runningTotalCost; }

    public double getPackageRunningVAT() { return runningTotalVAT; }


}
