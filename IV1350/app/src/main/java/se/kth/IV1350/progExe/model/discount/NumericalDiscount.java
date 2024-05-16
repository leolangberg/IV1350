package se.kth.IV1350.progExe.model.discount;

/**
 * Class representing Numerical Discounts. 
 * Uses polymorphism to be a subtype of 'Discount'.
 */
public class NumericalDiscount implements Discount {

    private final int discountID;
    private final double value;

    /**
     * Constructs a new Numerical Discount object.
     *
     * @param value The value of the discount.
     * @param discountID The ID of the discount, which can represent either a customerID or an itemID.
     */
    public NumericalDiscount(int discountID, double value) {

        this.discountID = discountID;
        this.value = value;
    }

    /**
     * Applies Discount onto given sum.
     * @return updated sum.
     */
    public double applyDiscount(double sum) {
        return sum -= value;
    }

    /**
     * Retrieves discount value.
     * 
     * @return the Numerical value.
     */
    public double getDiscountValue() { return value; }

    /**
     * Retrieves DiscountID.
     * 
     * @return the ID of Discount object.
     */
    public int getDiscountID() { return discountID; }


};
