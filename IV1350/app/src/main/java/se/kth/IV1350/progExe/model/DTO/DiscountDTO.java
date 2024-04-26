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
    private final Integer discount_id;

    /**
     * Constructs a new DiscountDTO object.
     *
     * @param discountType The type of the discount.
     * @param value The value of the discount.
     * @param discount_id The ID of the discount, which can represent either a customer_id or an item_id.
     */
    public DiscountDTO(DiscountType discountType, double value, int discount_id) {

        this.discountType = discountType;
        this.value = value;
        this.discount_id = discount_id;
    }

    /**
     * Constructs a new DiscountDTO object without a discount_id.
     * @param discountType The type of the discount.
     * @param value The value of the discount.
     */
    public DiscountDTO(DiscountType discountType, double value) {

        this.discountType = discountType;
        this.value = value;
        this.discount_id = null;
    }
    /**
     * Retrieves the discount type of this DiscountDTO.
     * 
     * @return The DiscountType of this DiscountDTO.
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Retrieves the discount value of this DiscountDTO.
     * 
     * @return The value of this DiscountDTO.
     */
    public double getDiscountValue() {
        return value;
    }

    /**
     * Retrieves the discount ID of this DiscountDTO.
     * 
     * @return The discount_id of this DiscountDTO.
     */
    public int getDiscountID() {
        return discount_id;
    }

};


