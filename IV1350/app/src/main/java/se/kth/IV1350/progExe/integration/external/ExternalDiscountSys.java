package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseConnectionException;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.integration.external.Exceptions.InvalidIdentifierException;
import se.kth.IV1350.progExe.model.DTO.DiscountDTO;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.ENUM.DiscountType;
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

    /**
     * Retrieves a discount based on the provided discount ID.
     * 
     * 'When passed the customer id, it tells a percentage to be reduced from the
     * total cost of the entire sale. The percentage is zero if there’s no discount.'
     *
     * @param discountID The ID of the discount to retrieve.
     * @return DiscountDTO representing the discount.
     * @throws DatabaseException if call to database fails.
     */
    public DiscountDTO getDiscountByID(int discountID) throws DatabaseException {
        try{
            return databaseInstance().findDiscount(discountID);
        } catch(DatabaseConnectionException | InvalidIdentifierException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe);
        }
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
    public double getDiscountByItemList(Map<ItemDTO, Integer> itemList) throws DatabaseException {

        double sum = 0;

        try{
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
        catch (DatabaseException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe);
        }
    }

    /**
     * Discount System Database ('singleton', thus 'static').
     */
    public static class DiscountSysDatabase {

        private DiscountDTO[] discountDatabase;
        private boolean databaseConnection;

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
         * @return A DiscountDTO representing the discount.
         * @throws InvalidIdentifierException in case ID is invalid.
         */
        public DiscountDTO findDiscount(int discountID) throws InvalidIdentifierException {
            if (discountDatabase[discountID] == null) {
                throw new InvalidIdentifierException("discountID: " + discountID + " is invalid.");
            }
            return discountDatabase[discountID];
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
