package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;


import java.util.*;


/**
 * ExternalInventorySys acts an intermediary (DAO) for the Inventory Database.
 * It is not the database itself.
 */
public class ExternalInventorySys {

    public InventorySysDatabase database;

    /**
     * Constructs a new ExternalInventorySys object.
     * 
     * This constructor initializes the ExternalInventorySys with a new InventorySysDatabase.
     */
    public ExternalInventorySys() {
        database = new InventorySysDatabase();
    }

    /**
     * Retrieves an item based on the provided item ID.
     * @param item_id The ID of the item to retrieve.
     * @return An ItemDTO representing the item, or null if the item does not exist.
     */
    public ItemDTO getItem(int item_id, int quantity) {
        return database.getItem(item_id, quantity);
    }

    /**
     * Updates the quantity of items in the inventory.
     *
     * @param itemList A map of ItemDTOs and their quantities to be updated in the inventory.
     */
    public void updateItemQuantity(Map<ItemDTO, Integer> itemList) {
        // method body
    }

    /**
     * DATABASE
     */
    public class InventorySysDatabase {
    
        private Shelf[] Inventory;
    
        /**
        * Constructs a new InventorySysDatabase object.
        * 
        * This constructor initializes the InventorySysDatabase with a new Shelf array and fills it with inventory scripts.
        */
        public InventorySysDatabase() {
    
            this.Inventory = new Shelf[100];
            fillInventoryScript();
    
        }
    
            private class Shelf {
    
                ItemDTO itemDTO;
                int quantity;
    
                /**
                 * Constructs a new Shelf object.
                 * 
                 * This constructor initializes the Shelf with the provided itemDTO and quantity.
                 *
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
        * This method creates a new Shelf with the provided itemDTO and quantity and adds it to the inventory at the index corresponding to the item ID.
        *
        * @param itemDTO The itemDTO to be added to the inventory.
        * @param quantity The quantity of the item to be added.
        * @return A boolean indicating whether the item was successfully added to the inventory.
        */
        public boolean addItem(ItemDTO itemDTO, int quantity) {
    
            Inventory[itemDTO.getItemID()] = new Shelf(itemDTO, quantity);
            return true;
        }
    
        /**
        * Retrieves an item from the inventory based on the provided item ID.
        * 
        * This method attempts to find a shelf in the inventory using the provided ID. 
        * If the shelf exists and the quantity is greater than 0, it returns the itemDTO stored in the shelf. If the shelf does not exist or the quantity is 0, it returns null.
        *
        * @param item_id The ID of the item to retrieve.
        * @return An ItemDTO representing the item, or null if the item does not exist or the quantity is 0.
        */
        public ItemDTO getItem(int item_id, int quantity) {
    
            if(Inventory[item_id] == null) {
                return null;
            }
            Shelf shelf = Inventory[item_id];

            if(shelf.quantity <= 0) {
                return null;
            }
            
            if(shelf.quantity < quantity) {
                return null;
            }

            return shelf.itemDTO;
        }

        /**
         * Clears the inventory.
         * 
         * This method initializes the Inventory with a new Shelf array of size 100.
         */
        public void clear() {
            Inventory = new Shelf[100];
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
    }
    



}
