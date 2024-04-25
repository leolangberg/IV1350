package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;

/*
 * is Item Class then suppose to only have the caller functions for the DTO like an interface for
 * the DTO, or should it just copy over the values?
 * 
 */
public class Item {

   private int item_id;
   private String itemName;
   private String description;
   private double price;
   private double VATrate;

   public Item(ItemDTO itemDTO) {

      this.item_id = itemDTO.getItemID();
      this.itemName = itemDTO.getItemName();
      this.description = itemDTO.getItemDescription();
      this.price = itemDTO.getItemPrice();
      this.VATrate = itemDTO.getItemVAT();

   }

   /*
    * Should these functions be here at all, since they already exist in DTO? Should there maybe
    * also be "Set()" functions?
    */

   public int getItemID() {

      return item_id;
   }

   public String getItemName() {

      return itemName;
   }

   public String getItemDescription() {

      return description;
   }

   public double getItemPrice() {

      return price;
   }

   public double getItemVAT() {

      return VATrate;
   }



}
