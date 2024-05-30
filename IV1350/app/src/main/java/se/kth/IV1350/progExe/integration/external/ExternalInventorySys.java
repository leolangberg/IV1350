package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseConnectionException;
import se.kth.IV1350.progExe.integration.external.Exceptions.DatabaseException;
import se.kth.IV1350.progExe.integration.external.Exceptions.InvalidIdentifierException;
import se.kth.IV1350.progExe.integration.external.Exceptions.InvalidQuantityException;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import java.util.Map;


/**
 * ExternalInventorySys acts an intermediary (DAO) for the Inventory Database.
 * It is not the database itself.
 */
public class ExternalInventorySys {

     /**
     * Uses GoF 'Singleton' Pattern to create a singular static instance
     * of the AccountingSystem Database.
     */
    private static final InventorySysDatabase DATABASE = new InventorySysDatabase();

    /**
     * The call method for using the 'Singleton' database.
     * @return Database Instance (singleton).
     * @throws DatabaseConnectionException in case connection to database fails.
     */
    public static InventorySysDatabase databaseInstance() throws DatabaseConnectionException { 
        if(DATABASE.databaseConnection != true) {
            throw new DatabaseConnectionException("No connection to Database.");
        }
        return DATABASE; 
    }

    /**
     * Constructs a new ExternalInventorySys object.
     */
    public ExternalInventorySys() {}

    /**
     * Retrieves an item based on the provided item ID.
     *
     * @param itemID The ID of the item to retrieve.
     * @return An ItemDTO representing the item, or throw error if null.
     * @throws DatabaseExcpetion in case an Excpetion is catched regarding database calls.
     */
    public ItemDTO getItem(int itemID, int quantity) throws DatabaseException {
            return databaseInstance().getItem(itemID, quantity);
    }

    /**
     * Updates the quantity of items in the inventory.
     *
     * @param itemList A map of ItemDTOs and their quantities to be updated in the inventory.
     * @throws DatabaseException in case call to database fails.
     */
    public void updateItemQuantity(Map<ItemDTO, Integer> itemList) throws DatabaseException {
        
        try{
            for (Map.Entry<ItemDTO, Integer> entry : itemList.entrySet()) {
                ItemDTO itemDTO = entry.getKey();
                int quantity = entry.getValue();
                databaseInstance().updateItemQuantity(itemDTO, quantity);
            }
        } catch(DatabaseConnectionException dce) {
            throw new DatabaseException(dce.getMessage(), dce);
        }
    }



    /**
    * Retrieves the quantity of an item in the inventory based on the provided item ID.
    * 
    * @param itemID The ID of the item to retrieve the quantity for.
    * @return The quantity of the item, or throw error if itemID does not exist.
    * @throws DatabaseExcpetion in case an Excpetion is catched regarding database calls.
    */
    public int getItemQuantity(int itemID) throws DatabaseException {
        try {
            return databaseInstance().getItemQuantity(itemID);
        } catch (DatabaseException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe);
        }
    }

    /**
     * Inventory System Database ('singleton', thus 'static').
     */
    public static class InventorySysDatabase {
    
        private Shelf[] inventory;
        private boolean databaseConnection;
    
        /**
        * Constructs a new InventorySysDatabase object.
        */
        public InventorySysDatabase() {
    
            this.inventory = new Shelf[100];
            this.databaseConnection = true;
            fillInventoryScript();
    
        }
    
            private class Shelf {
    
                ItemDTO itemDTO;
                int quantity;
    
                /**
                 * This constructor initializes a Shelf with the provided itemDTO and quantity.
                 * @param itemDTO The itemDTO to be stored in the shelf.
                 * @param quantity The quantity of the item on the shelf.
                 */
                public Shelf(ItemDTO itemDTO, int quantity) {
                    this.itemDTO = itemDTO;
                    this.quantity = quantity;
                }
            }
    
    
        /**
        * Adds an item to the inventory.
        *
        * @param itemDTO The itemDTO to be added to the inventory.
        * @param quantity The quantity of the item to be added.
        * @return A boolean indicating whether the item was successfully added to the inventory.
        */
        public boolean addItem(ItemDTO itemDTO, int quantity) {
    
            inventory[itemDTO.getItemID()] = new Shelf(itemDTO, quantity);
            return true;
        }
    
        /**
        * Retrieves an item from the inventory based on the provided item ID.
        * 
        * This method attempts to find a shelf in the inventory using the provided ID. 
        * If the shelf exists and the quantity is greater than 0, it returns the itemDTO stored in the shelf. 
        * If the shelf does not exist or the quantity is < 0, it returns null.
        *
        * @param itemID The ID of the item to retrieve.
        * @return An ItemDTO representing the item, or null if the item does not exist or the quantity is 0.
        * @throws InvalidIdentiferException if ID is invalid.
        * @throws InvalidQuantityExcpetion if Quantity is invalid.
        * @throws DatabaseConnectionExcpetion if connection to database is not established. 
        */
        public ItemDTO getItem(int itemID, int quantity) throws InvalidIdentifierException, InvalidQuantityException {

            if(itemID == -1) {
                //setDatabaseConnection(false);
            }

            if(itemID < 0 || itemID > inventory.length) {
                throw new InvalidIdentifierException("ItemID: " + itemID + " is invalid.");
            } else if (inventory[itemID] == null) {
                throw new InvalidIdentifierException("ItemID: " + itemID + " is invalid.");
            } 
            Shelf shelf = inventory[itemID];

            if(shelf.quantity <= 0) {
                throw new InvalidQuantityException("Quantity for ItemID: " + itemID + " is " + shelf.quantity + ".");
            } else if(quantity <= 0) {
                throw new InvalidQuantityException("Quantity provided: " + quantity + " is invalid.");
            } else if (shelf.quantity < quantity) {
                throw new InvalidQuantityException("itemID: " + itemID + " Quantity provided: " + quantity + " Quantity in database: " + shelf.quantity);
            }

            return shelf.itemDTO;
        }

        /**
        * Updates the quantity of a specific item in the inventory.
        *
        * @param itemDTO The item to update in the inventory. This object contains the item ID.
        * @param quantity The quantity to subtract from the current quantity of the item in the inventory.
        * @return true if the quantity was successfully updated, false if the item does not exist in the inventory,
        *         if the item ID is out of range, or if the quantity on the shelf is less than the quantity to be subtracted.
        */
        public boolean updateItemQuantity(ItemDTO itemDTO, int quantity) {
            int itemID = itemDTO.getItemID();
            if(itemID < 0 || itemID >= inventory.length || inventory[itemID] == null) {
                return false;
            }
            Shelf shelf = inventory[itemID];
            if(shelf.quantity < quantity) {
                return false;
            }
            shelf.quantity -= quantity;
            return true;
        }


        /**
         * Clears the inventory.
         * 
         * This method initializes the Inventory with a new Shelf array of size 100.
         */
        public void clear() {
            inventory = new Shelf[100];
        }

        /**
         * Fills the inventory with inventory scripts.
         * 
         * This method adds three items to the inventory: an apple, a banana, and an orange.
         */
        private void fillInventoryScript() {
            addItem(new ItemDTO(1, "Apple", "Granny Smith", 5.00, 0.12), 10);
            addItem(new ItemDTO(2, "Banana", "chiquita", 7.00, 0.12), 10);
            addItem(new ItemDTO(3, "Orange", "", 15.00, 0.12), 5);
        }

        /**
        * Retrieves the quantity of an item in the inventory based on the provided item ID.
        * 
        * @param itemID The ID of the item to retrieve the quantity for.
        * @return The quantity of the item, or throw error if it itemID does not exist.
        * @throws InvalidQuantityExcpetion if Quantity is invalid.
        * @throws DatabaseConnectionExcpetion if connection to database is not established. 
        */
        public int getItemQuantity(int itemID) throws InvalidIdentifierException, DatabaseConnectionException {
            if(itemID < 0 || itemID >= inventory.length || inventory[itemID] == null) {
              throw new InvalidIdentifierException("ItemID: " + itemID + " is invalid.");
            }   
            return inventory[itemID].quantity;
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
