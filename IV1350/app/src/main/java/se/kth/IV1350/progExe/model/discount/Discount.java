package se.kth.IV1350.progExe.model.discount;

import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;

/**
 * GoF 'Strategy' Discount interface for all encapsulating all types of discounts
 * into one clear interactable object.
 */
public interface Discount {

     /**
     * Applies Discount onto given sum.
     * @return updated sum.
     */
    double applyDiscount(double sum);
    
     /**
     * Retrieves discount value.
     * 
     * @return the value stored in discount Object.
     *         can be either Numeral or Percentage.
     */
    double getDiscountValue();

     /**
     * Retrieves DiscountID.
     * 
     * @return the ID of Discount object.
     */
    int getDiscountID();
}
