package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.DiscountType;

import java.util.*;

public class ExternalDiscountSys {

    // Assume discounts are stored in a hash-map related array where: array[id]
    public DiscountSysDatabase database;

    public ExternalDiscountSys() {
        this.database = new DiscountSysDatabase();
    };

    /**
     * When passed the customer id, it tells a percentage to be reduced from the total cost of the
     * entire sale. The percentage is zero if there’s no discount.
     * 
     * Make sure to check wether customer_id actually exists. If it does not then return 0, meaning
     * no discount.
     */
    public DiscountDTO getDiscount(int discount_id) {

        return database.findDiscount(discount_id);

    }

    /**
     * When passed the total cost of the entire sale, it tells a percentage to be reduced from this
     * total cost. The percentage is zero if there’s no discount.
     */
    public double getDiscount(double totalprice) {
        return 0;
    }

    /**
     * When passed a list of all bought items, it tells a sum to be reduced from the total cost of
     * the entire sale. The sum is zero if there’s no discount.
     */
    public DiscountDTO getDiscount(List<ItemDTO> itemlist) {

        double sum = 0;
        // lookup on each specific item
        // lookup on item combination discounts etc.

        // creates a discountDTO out of the found discounts
        DiscountDTO discountDTO = new DiscountDTO(DiscountType.NUMERAL, sum, 0);
        return null;

    }




    /**
     * DATABASE
     */
    public class DiscountSysDatabase {
    
        private DiscountDTO[] discountDatabase;
    
        public DiscountSysDatabase() {
            this.discountDatabase = new DiscountDTO[100];
            fillDiscountScript();
        }
    
        private void fillDiscountScript() {
            discountDatabase[0] = new DiscountDTO(DiscountType.NUMERAL, 10.00, 1 );
            discountDatabase[1] = new DiscountDTO(DiscountType.PERCENTAGE, 0.20, 2 );
        
        }
    
        public DiscountDTO findDiscount(int discount_id) {
            
            if(discountDatabase[discount_id] == null) {
                return null;
            }
            return discountDatabase[discount_id];
        }
    
    
    }

}
