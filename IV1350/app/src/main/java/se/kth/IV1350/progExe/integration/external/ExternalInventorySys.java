package se.kth.IV1350.progExe.integration.external;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;


import java.util.*;


/**
 * ExternalInventorySys acts an intermediary (DAO) for the Inventory Database.
 * It is not the database itself.
 */
public class ExternalInventorySys {

    public InventorySysDatabase database;

    public ExternalInventorySys() {
        database = new InventorySysDatabase();
    };


    /**
     * if item exists return item else return null. Decrease quantity in shelf by 1. Returns DTO
     * version of Item.
     */
    public ItemDTO getItem(int item_id) {

        return database.getItem(item_id);

    }


    public void updateItemQuantity(Map<ItemDTO, Integer> itemList) {

    }

    /**
     * DATABASE
     */
    public class InventorySysDatabase {
    
        private Shelf[] Inventory;
    
        public InventorySysDatabase() {
    
            this.Inventory = new Shelf[100];
            fillInventoryScript();
    
        }
    
            private class Shelf {
    
                ItemDTO itemDTO;
                int quantity;
    
                public Shelf(ItemDTO itemDTO, int quantity) {
    
                    this.itemDTO = itemDTO;
                    this.quantity = quantity;
                }
            }
    
    
        public boolean addItem(ItemDTO itemDTO, int quantity) {
    
            Inventory[itemDTO.getItemID()] = new Shelf(itemDTO, quantity);
            return true;
        }
    
        public ItemDTO getItem(int item_id) {
    
            if(Inventory[item_id] == null) {
                return null;
            }
            Shelf shelf = Inventory[item_id];
            if(shelf.quantity <= 0) {
                return null;
            }
            return shelf.itemDTO;
        }

        public void clear() {
            Inventory = new Shelf[100];
        }
    
        private void fillInventoryScript() {
    
            addItem(new ItemDTO(1, "Apple", "Granny Smith", 5.00, 0.12), 10);
            addItem(new ItemDTO(2, "Banana", "chiquita", 7.00, 0.12), 10);
            addItem(new ItemDTO(3, "Orange", "", 15.00, 0.12), 5);
    
        }
    }
    



}
