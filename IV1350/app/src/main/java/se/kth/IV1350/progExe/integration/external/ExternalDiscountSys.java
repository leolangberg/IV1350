package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseConnectionException;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.integration.external.Exceptions.InvalidIdentifierException;
import se.kth.IV1350.progExe.model.discount.CompositeDiscount;
import se.kth.IV1350.progExe.model.discount.Discount;
import se.kth.IV1350.progExe.model.discount.NumericalDiscount;
import se.kth.IV1350.progExe.model.discount.PercentageDiscount;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;
import se.kth.IV1350.progExe.model.Exceptions.InvalidCallException;
import java.util.Map;

/**
 * The ExternalDiscountSys class is responsible for handling all communication
 * with the Discount database.
 * 
 * Contains methods for retrieving discounts based on discount IDs, total prices, and item lists.
 */
public class ExternalDiscountSys {

     /**
     * Uses GoF 'Singleton' Pattern to create a singular static instance
     * of the AccountingSystem Database.
     */
    private static final DiscountSysDatabase DATABASE = new DiscountSysDatabase();

    /**
     * The call method for using the 'Singleton' database.
     * @return Database Instance (singleton).
     * @throws DatabaseConnectionException in case connection to database fails.
     */
    public static DiscountSysDatabase databaseInstance() throws DatabaseConnectionException { 
        if(DATABASE.databaseConnection != true) {
            throw new DatabaseConnectionException("No connection to Database.");
        }
        return DATABASE; 
    }

    /**
     * Constructs a new ExternalDiscountSys object.
     */
    public ExternalDiscountSys() {}

    public CompositeDiscount collectSaleDiscounts(SaleDTO saleDTO) throws DatabaseException, InvalidCallException {
        return databaseInstance().collectSaleDiscounts(saleDTO);
    }

    /**
     * 'When passed the customer id, it tells a percentage to be reduced from the
     * total cost of the entire sale. The percentage is zero if there’s no discount.'
     *
     * @param discountID The ID of the discount to retrieve.
     * @return DiscountDTO representing the discount.
     */
    public Discount getCustomerDiscount(int customerID) throws DatabaseException {
        return databaseInstance().findCustomerDiscount(customerID);
    }


    /**
     * When passed the total cost of the entire sale, it tells a percentage to be
     * reduced from this total cost. The percentage is zero if there’s no discount.
     * 
     * @param totalprice total cost of the entire sale.
     * @return Discount containing percentage to be reduced.
     */
    public Discount getDiscountByTotalPrice(double totalprice) throws DatabaseException, InvalidCallException {
        return databaseInstance().findCostDiscount(totalprice);
    }

    /**
     * 
     * When passed a list of all bought items, it tells a sum to be reduced from the
     * total cost of the entire sale by matching each ItemID to potential DiscountID. 
     * Returns discount of 0 if there’s no discount.
     * 
     * @param itemlist list of all bought items.
     * @return numerical number to be reduced.
     */
    public Discount getDiscountByItemList(Map<ItemDTO, Integer> itemList) throws DatabaseException {
        return databaseInstance().getDiscountByItemList(itemList);
    }

    /**
     * Discount System Database ('singleton', thus 'static').
     * 
     * @itemDiscountDatabase holds discounts specific items.
     * @customerDicsountDatbase holds discounts for specific customers.
     * @costDiscountDatabase holds discounts for specific amounts, where the index
     *                       of the array corresponds to the cost. 
     *                       (e.g array[100] holds the discount for paying 100 kr).
     */
    public static class DiscountSysDatabase {

        private Discount[] itemDiscountDatabase;
        private Discount[] customerDiscountDatabase;
        private Discount[] costDiscountDatabase;
        private boolean databaseConnection;

        /**
         * Constructs a new DiscountSysDatabase object.
         * 
         * This constructor initializes the DiscountSysDatabase with a new DiscountDTO
         * array and fills it with discount scripts.
         */
        public DiscountSysDatabase() {
            this.itemDiscountDatabase = new Discount[100];
            this.customerDiscountDatabase = new Discount[100];
            this.costDiscountDatabase = new Discount[101];
            this.databaseConnection = true;
            fillDiscountDatabaseScript();
        }

        public CompositeDiscount collectSaleDiscounts(SaleDTO saleDTO) throws DatabaseException, InvalidCallException {

            CompositeDiscount compositeDiscount = new CompositeDiscount();
            compositeDiscount = getDiscountByItemList(saleDTO.getSaleItemList(), compositeDiscount);
            Discount totalPriceDiscount = findCostDiscount(saleDTO.getSalePrice());
            compositeDiscount.addDiscount(totalPriceDiscount);
            return compositeDiscount;
        }

        /**
         * Retrieves a discount based on the provided discount ID.
         *
         * @param discountID The ID of the discount to retrieve.
         * @return A DiscountDTO representing the discount.
         * @throws InvalidIdentifierException in case ID is invalid.
         */
        public Discount findItemDiscount(int discountID) throws InvalidIdentifierException {
            if (itemDiscountDatabase[discountID] == null) {
                throw new InvalidIdentifierException("discountID: " + discountID + " is invalid.\n");
            }
            return itemDiscountDatabase[discountID];
        }

        /**
         * Retrieves a discount based on the provided discount ID.
         *
         * @param discountID The ID of the discount to retrieve.
         * @return A DiscountDTO representing the discount.
         * @throws InvalidIdentifierException in case ID is invalid.
         */
        public Discount findCustomerDiscount(int customerID) throws InvalidIdentifierException {
            if (customerDiscountDatabase[customerID] == null) {
                throw new InvalidIdentifierException("customerID: " + customerID + " is invalid.\n");
            }
            return customerDiscountDatabase[customerID];
        }

        /**
         * Checks for viable discounts from the given totalprice.
         * 
         * @param totalcost cost of entire sale.
         * @return discount related to closest price discount margin.
         */
        public Discount findCostDiscount(double totalcost) throws InvalidIdentifierException, InvalidCallException {
            int index = (int) Math.floor(totalcost);
            if( totalcost < 0) {
                throw new InvalidIdentifierException("totalcost: " + totalcost + " is invalid.");
            } else if( index > costDiscountDatabase.length) {
                index = costDiscountDatabase.length - 1;
            }
            while(index > 0) {
                if(costDiscountDatabase[index] != null) {
                    System.out.println("DiscountDatbase: costDiscount: " + costDiscountDatabase[index].getDiscountValue());
                    return costDiscountDatabase[index];
                }
                index--;
            }

            return new NumericalDiscount(0, 0);
        }

        /**
         * "When passed a list of all bought items, it tells a sum to be reduced from the
         * total cost of the entire sale by matching each ItemID to potential DiscountID. 
         * Returns discount of 0 if there’s no discount."
         * 
         * This is implemented by having an O(n^2) double for-loop iterating over all items
         * individually to then find the total sum to be reduced. 
         * @param itemList Map of ItemDTO's and their respective Quantities.
         * @return sum is packaged as a Discount and returned.
         */
        public Discount getDiscountByItemList(Map<ItemDTO, Integer> itemList) throws DatabaseException {
            double sum = 0;

            for (Map.Entry<ItemDTO, Integer> entry : itemList.entrySet()) {

                ItemDTO itemDTO = entry.getKey();
                int quantityOfItem = entry.getValue();

                for(int i = 0; i < quantityOfItem; i++) {
                    try {
                        Discount discount = findItemDiscount(itemDTO.getItemID());
                        double reducedItemCost = discount.applyDiscount(itemDTO.getItemPrice());
                        sum += itemDTO.getItemPrice() - reducedItemCost;
                        
                    } catch (InvalidIdentifierException ide) {
                        continue;
                    }
                }
            }  

            return new NumericalDiscount(0, sum);
        }

        public CompositeDiscount getDiscountByItemList(Map<ItemDTO, Integer> itemList, CompositeDiscount compositeDiscount) throws DatabaseException {

            for (Map.Entry<ItemDTO, Integer> entry : itemList.entrySet()) {

                ItemDTO itemDTO = entry.getKey();
                int quantityOfItem = entry.getValue();

                for(int i = 0; i < quantityOfItem; i++) {
                    try {
                        Discount discount = findItemDiscount(itemDTO.getItemID());
                        compositeDiscount.addDiscount(discount);
                        
                    } catch (InvalidIdentifierException ide) {
                        continue;
                    }
                }
            }  

            return compositeDiscount;
        }

        public void storeItemDiscount(Discount discount) throws InvalidCallException {
            itemDiscountDatabase[discount.getDiscountID()] = discount;
        } 

        /**
         * Fills database with dummy data.
         */
        private void fillDiscountDatabaseScript() {

            costDiscountDatabase[100] = new PercentageDiscount(100, 0.1);
            costDiscountDatabase[50] = new PercentageDiscount(50, 0.05);
            costDiscountDatabase[0] = new PercentageDiscount(0, 0);

            customerDiscountDatabase[1] = new PercentageDiscount(1, 0.5);

            itemDiscountDatabase[1] = new NumericalDiscount(1, 2);
            itemDiscountDatabase[2] = new NumericalDiscount(2, 2);
        }


        /**
         * Changes state of connectivity to database. (For testing purposes, see Seminar Task 4)
         * @param connection true or false.
         */
        public void setDatabaseConnection(boolean connection) {
            this.databaseConnection = connection;
        }
    }
}
