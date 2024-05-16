package se.kth.IV1350.progExe.model.discount;


/**
 * Class representing Percentage Discounts. 
 * Uses polymorphism to be a subtype of 'Discount'.
 */
public class PercentageDiscount implements Discount {

    private final int discountID;
    private final double percentage;

    /**
     * Constructs a new Percentage Discount object.
     *
     * @param value The value of the discount.
     * @param discountID The ID of the discount, which can represent either a customerID or an itemID.
     */
    public PercentageDiscount(int discountID, double percentage) {

        this.discountID = discountID;
        this.percentage = percentage;
    }

    /**
     * Applies Discount onto given sum.
     * @return updated sum.
     */
    public double applyDiscount(double sum) {
        return sum * (1 - percentage);
    }

    /**
     * Retrieves discount value.
     * 
     * @return the Percentage value.
     */
    public double getDiscountValue() { return percentage; }

    /**
     * Retrieves DiscountID.
     * 
     * @return the ID of Discount object.
     */
    public int getDiscountID() { return discountID; }

};
