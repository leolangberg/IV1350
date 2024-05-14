package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.*;
import se.kth.IV1350.progExe.model.ENUM.DiscountType;

import java.util.*;

/**
 * The ExternalDiscountSys class is responsible for handling all communication
 * with the Discount database.
 * 
 * Contains methods for retrieving discounts based on discount IDs, total prices, and item lists.
 */
public class ExternalDiscountSys {

    public DiscountSysDatabase database;

    /**
     * Constructs a new ExternalDiscountSys object.
     * 
     * This constructor initializes the ExternalDiscountSys with a new DiscountSysDatabase.
     */
    public ExternalDiscountSys() {
        this.database = new DiscountSysDatabase();
    }

    /**
     * Retrieves a discount based on the provided discount ID.
     *
     * @param discountID The ID of the discount to retrieve.
     * @return A DiscountDTO representing the discount, or null if the discount does
     *         not exist.
     */
    public DiscountDTO getDiscountByID(int discountID) {
        return database.findDiscount(discountID);
    }

    /**
     * When passed the total cost of the entire sale, it tells a percentage to be
     * reduced from this total cost. The percentage is zero if there’s no discount.
     */
    public double getDiscountByTotalPrice(double totalprice) {
        double percentage = 0;
        return percentage;
    }

    /**
     * FOR NOW DOES NOTHING. (WORK IN PROGRESS)
     * 
     * When passed a list of all bought items, it tells a sum to be reduced from the
     * total cost of the entire sale by matching each ItemID to potential DiscountID. 
     * Returns discount of 0 if there’s no discount.
     */
    public double getDiscountByItemList(Map<ItemDTO, Integer> itemList) {

        double sum = 0;

        for (Map.Entry<ItemDTO, Integer> entry : itemList.entrySet()) {

            ItemDTO itemDTO = entry.getKey();
            DiscountDTO discountDTO = getDiscountByID(itemDTO.getItemID());
            if(discountDTO != null)
            {
                sum += discountDTO.getDiscountValue();
            }
        }  
        return sum;
    }

    /**
     * DISCOUNT DATABASE
     */
    public class DiscountSysDatabase {

        private DiscountDTO[] discountDatabase;

        /**
         * Constructs a new DiscountSysDatabase object.
         * 
         * This constructor initializes the DiscountSysDatabase with a new DiscountDTO
         * array and fills it with discount scripts.
         */
        public DiscountSysDatabase() {
            this.discountDatabase = new DiscountDTO[100];
            fillDiscountScript();
        }

        /**
         * Fills the discount database with discount scripts.
         * 
         * This method currently adds two discount scripts to the discount database.
         */
        private void fillDiscountScript() {
            discountDatabase[1] = new DiscountDTO(DiscountType.NUMERAL, 10.00, 1);
            discountDatabase[2] = new DiscountDTO(DiscountType.PERCENTAGE, 0.20, 2);
            discountDatabase[50] = new DiscountDTO(DiscountType.NUMERAL, 10, 50);
        }

        /**
         * Retrieves a discount based on the provided discount ID.
         *
         * @param discountID The ID of the discount to retrieve.
         * @return A DiscountDTO representing the discount, or null if the discount does
         *         not exist.
         */
        public DiscountDTO findDiscount(int discountID) {
            if (discountDatabase[discountID] == null) {
                return null;
            }
            return discountDatabase[discountID];
        }

    }

}
