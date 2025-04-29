package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

import backend.items.*;
import backend.Logger;

/**
 * A class that handles the list of warehouse Items.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class ItemsList {
    // The fields.
    private List<Item> itemsList = new LinkedList<>();
    
    /**
     * @param prepopulated to instantiate the class with 2 warehouse items for debugging
     */
    public ItemsList(boolean prepopulated) {
        if (prepopulated) {
            Item item1 = new Item("Brick", "Construction bricks", 100, "Hello&Sons ltd", "random-id1", 5);
            Item item2 = new Item("Cement", "Construction cement", 50, "Bye&Friends ltd", "random-id2", 15);
            this.itemsList.add(item1);
            this.itemsList.add(item2);
            Logger.info("Added 2 warehouse items for debugging");
        }
    }
    
    // TODO: handle wrong input data
    /**
     * Set all the item details to create a new Item.
     * If data is invalid print an error message.
     * @param name the name of the item
     * @param description the description of the item
     * @param quantity the available quantity of the item
     * @param supplier the name of the supplier
     */
    public void addItem(String name, String description, int quantity, String supplier, String supplierId, float supplierPrice) {
        Item item = new Item(name, description, quantity, supplier, supplierId, supplierPrice);
        this.itemsList.add(item);
        Logger.info("Item added in warehouse, data: " + this.itemsList.getLast().getAllData().toString());
    }
    
    /**
     * Get the number of items in the list.
     * @return the list of items
     */
    public List<Item> getItemsList() {
        return this.itemsList;
    }
    
    /**
     * Get data of the passed id Item.
     * @param id of the item
     * @return a HashMap with all the Item data
     */
    public HashMap<ItemData, String> getItemData(String id) {
        HashMap<ItemData, String> itemData = null;
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (id.equals(this.itemsList.get(i).getId())) {
                itemData = this.itemsList.get(i).getAllData();
                break;
            }
        }
        if (itemData == null) {
            Logger.error("Item not found");
        }
        return itemData;
    }
    
    /**
     * Get Item quantity
     * @param name the name of the item
     * @return the quantity of the item
     */
    public int getItemQuantity(String id) {
        int quantity = -1;
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (id.equals(this.itemsList.get(i).getId())) {
                quantity = this.itemsList.get(i).getQuantity();
                break;
            }
        }
        if (quantity == -1) {
            Logger.error("Item not found, quantity not updated");
        }
        Logger.info("Current Item quantity: " + quantity);
        return quantity;
    }
    
    /**
     * Update available quantity of the passed Item.
     * @param name the name of the item
     * @param quantity the new quantity of the item
     */
    public void updateItemQuantity(String id, int quantity) {
        boolean found = false;
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (id.equals(this.itemsList.get(i).getId())) {
                this.itemsList.get(i).setQuantity(quantity);
                if (quantity == 0) {
                    Logger.error("ALERT: new item quantity is 0");
                } else if (quantity < 10) {
                    Logger.warn("ALERT: new item quantity is " + quantity);
                } else {
                    Logger.info("Item quantity updated: " + this.itemsList.get(i).getQuantity());
                }
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Item not found, quantity not updated");
        }
    }
    
    /**
     * Delete passed Items.
     * @param id the id of the item
     */
    public void deleteItem(String id) {
        boolean found = false;
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (id.equals(this.itemsList.get(i).getId())) {
                this.itemsList.remove(i);
                Logger.info("Item deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Item not found, data not updated");
        }
    }
    
    /**
     * Get Supplier List Items
     * @param supplierId the id of the supplier
     * @return the list of the items
     */
    public List<Item> getSupplierItems(String supplierId) {
        List<Item> supplierItems = new ArrayList<>();
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (supplierId.equals(this.itemsList.get(i).getSupplierId())) {
                supplierItems.add(this.itemsList.get(i));
            }
        }
        return supplierItems;
    }
    
    /**
     * Get Item with low stock level
     * @return the list of the items 
     */
    public List<Item> getItemsLowStock() {
        List<Item> itemsLowStock = new ArrayList<>();
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (this.itemsList.get(i).getQuantity() < 10) {
                itemsLowStock.add(this.itemsList.get(i));
            }
        }
        return itemsLowStock;
    }
 }
