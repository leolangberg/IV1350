package se.kth.IV1350.progExe.model.discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;

public class CompositeDiscount implements Discount {
    private List<Discount> matchingDiscount = new ArrayList<>();

    public void addDiscount(Discount discount) {
        matchingDiscount.add(discount);
    }

    public void removeDiscount(Discount discount) {
        matchingDiscount.remove(discount);
    }


    public double applyDiscount(double sum) {

        Collections.reverse(matchingDiscount);
        double dif = sum;
        for(Discount discount : matchingDiscount) {
            dif = discount.applyDiscount(dif);
        }
        return dif;
    }

    @Override
    public int getDiscountID() {
        // throw new InvalidCallException("Cannot retrieve DiscountID for CompositeDiscount.");
        return 0;
    }

    @Override
    public double getDiscountValue() {
        // throw new InvalidCallException("Cannot retrieve Discount Value for CompositeDiscount.");
        return 0;
    }

    public void printCompositeDiscount() {
        System.out.println("PRINTOUT");
        for(Discount discount : matchingDiscount) {
            System.out.println("Discount: " + discount.getDiscountID() + " val: " + discount.getDiscountValue());
        }
        System.out.println("End list");
        System.out.println();
    }
}
