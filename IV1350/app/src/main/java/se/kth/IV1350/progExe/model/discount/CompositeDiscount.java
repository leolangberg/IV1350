package se.kth.IV1350.progExe.model.discount;

import java.util.ArrayList;
import java.util.List;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import java.util.Collections;

/**
 * CompositeDiscount uses GoF pattern 'Composite' which makes 
 * Discounts and Lists of Discounts handled similarly. 
 */
public class CompositeDiscount implements Discount {
    private List<Discount> matchingDiscount = new ArrayList<>();

    /**
     * Adds a Discount to the List.
     * @param discount object to be added.
     */
    public void addDiscount(Discount discount) {
        matchingDiscount.add(discount);
    }

    /**
     * Removes a Discount from the List.
     * @param discount object to be removed.
     */
    public void removeDiscount(Discount discount) {
        matchingDiscount.remove(discount);
    }

    /**
     * resets the composite Discount list.
     */
    public void removeAll() {
        matchingDiscount = new ArrayList<>();
    }


    /**
     * Iterates through entire List and perform the individually
     * defined 'applyDiscount' method for each Discount.
     * List is reversed so that Customer Discount always is applied lastly (Business logic).
     * @param sum corresponds to value onto which discounts will be applied.
     * @return sum after applied Discounts.
     */
    public double applyDiscount(double sum) {
        Collections.reverse(matchingDiscount);
        for(Discount discount : matchingDiscount) {
            sum = discount.applyDiscount(sum);
        }
        return sum;
    }

    /**
     * Retrieving DiscountID for a composite List is not possible.
     */
    @Override
    public int getDiscountID() throws InvalidCallException {
        throw new InvalidCallException("Cannot retrieve DiscountID for CompositeDiscount.");
    }

    /**
     * Retrieving Discount Value for a composite List is not possible.
     */
    @Override
    public double getDiscountValue() throws InvalidCallException {
        throw new InvalidCallException("Cannot retrieve Discount Value for CompositeDiscount.");
    }
}
