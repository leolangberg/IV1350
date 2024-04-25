package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.ENUM.DiscountType;

public class DiscountDTO {

    private final DiscountType discountType;
    private final double value;
    private final Integer discount_id;

    /**
     * As long as item_id & customer_id never can be the same number then this constructor would
     * work for both. discount_id = customer_id discount_id = item_id
     */
    public DiscountDTO(DiscountType discountType, double value, int discount_id) {

        this.discountType = discountType;
        this.value = value;
        this.discount_id = discount_id;
    }

    /**
     * Creates DiscountDTO instance without any discount_id. Used in cases of finding total
     * discounts of entire sales. (Not discounts related to specific item_id's or customer_id's)
     *
     */
    public DiscountDTO(DiscountType discountType, double value) {

        this.discountType = discountType;
        this.value = value;
        this.discount_id = null;
    }

    public DiscountType getDiscountType() {

        return discountType;
    }

    public double getDiscountValue() {

        return value;
    }

    public int getDiscountID() {
        
        return discount_id;
    }

};


