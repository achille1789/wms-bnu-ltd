package src.backend;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

import src.backend.warehouseitems.*;
import src.utils.Logger;

/**
 * A class that handles the list of warehouse Items.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class InventoryManager {
    // The fields.
    private List<Item> itemsList = new LinkedList<>();
    
    /**
     * Get list index of item that matches the passed Id.
     * @param id of the item
     * @return the index of the item
     */
     private int getItemIndex(String id) {
        int index = -1;
        for (int i = 0; i < this.itemsList.size(); i++) {
            if (id.equals(this.itemsList.get(i).getId())) {
                index = i;
                break;
            }
        }
        return index;
     }
    
    /**
     * Set all the item details to create a new Item.
     * If data is invalid print an error message.
     * @param name the name of the item
     * @param description the description of the item
     * @param quantity the available quantity of the item
     * @param supplier the name of the supplier
     * @param supplierId the id of the supplier
     * @param supplierPrice the supplier price of the item
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
     * Get Item quantity
     * @param id the name of the item
     * @return the quantity of the item
     */
    public int getItemQuantity(String id) {
        int index = getItemIndex(id);
        if (index > -1) {
            int quantity = this.itemsList.get(index).getQuantity();
            Logger.info(this.itemsList.get(index).getName() + " currently on stock: " + quantity + " units");
            return quantity;
        }
        Logger.error("Item not found");
        return index;
    }
    
    /**
     * Update available quantity of the passed Item.
     * @param name the name of the item
     * @param quantity the new quantity of the item
     */
    public void updateItemQuantity(String id, int quantity) {
        int index = getItemIndex(id);
        if (index > -1) {
            this.itemsList.get(index).setQuantity(quantity);
            String itemName = this.itemsList.get(index).getName();
            if (quantity == 0) {
                Logger.error("ALERT: new " + itemName + " quantity is 0");
            } else if (quantity < 10) {
                Logger.warn("ALERT: new " + itemName + " quantity is " + quantity);
            } else {
                Logger.info(itemName + " quantity updated: " + this.itemsList.get(index).getQuantity());
            }
        } else {
            Logger.error("Item not found, quantity not updated");
        }
    }
    
    /**
     * Delete passed Items.
     * @param id the id of the item
     */
    public void deleteItem(String id) {
        int index = getItemIndex(id);
        if (index > -1) {
            this.itemsList.remove(index);
            Logger.info("Item deleted");
        } else {
            Logger.error("Item not found, item not deleted");
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
