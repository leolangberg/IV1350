package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.ENUM.DiscountType;

/**
 * The DiscountDTO class is a DTO (Data Transfer Object) that contains information about a discount.
 * 
 * This class contains the discount type, value, and ID of the discount.
 */
public class DiscountDTO {

    private final DiscountType discountType;
    private final double value;
    private final Integer discountID;

    /**
     * Constructs a new DiscountDTO object.
     *
     * @param discountType The type of the discount.
     * @param value The value of the discount.
     * @param discountID The ID of the discount, which can represent either a customerID or an itemID.
     */
    public DiscountDTO(DiscountType discountType, double value, int discountID) {

        this.discountType = discountType;
        this.value = value;
        this.discountID = discountID;
    }

    /**
     * Constructs a new DiscountDTO object without a discount_id.
     * @param discountType The type of the discount.
     * @param value The value of the discount.
     */
    public DiscountDTO(DiscountType discountType, double value) {

        this.discountType = discountType;
        this.value = value;
        this.discountID = null;
    }
    /**
     * Retrieves DiscountType.
     * 
     * @return The DiscountType of this DiscountDTO.
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Retrieves discount value.
     * 
     * @return The value of this DiscountDTO.
     */
    public double getDiscountValue() {
        return value;
    }

    /**
     * Retrieves DiscountID.
     * 
     * @return The discount_id of this DiscountDTO.
     */
    public int getDiscountID() {
        return discountID;
    }

};


