package backend.items;

import java.util.HashMap;
import java.time.Instant;

/**
 * A class that defines a warehouse Item.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Item {
    // The fields.
    public String name;
    public String description;
    public int quantity;
    public String supplier;
    public String supplierId;
    public float price;
    public String id;
    
    /**
     * Add a new warehouse item.
     * Info needed are: name, description, available quantity, supplier name and price.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param quantity the available quantity of the item
     * @param supplier the supplier of the item
     * @param price the price of the item
     */
    public Item(String name, String description, int quantity, String supplier, String supplierId, float price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplier = supplier;
        this.supplierId = supplierId;
        this.price = price;
        String rand = String.valueOf((int)(Math.random() * 1000));
        this.id = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
    }
    
    /**
     * Get a HashMap with all the item data.
     * The keys are the Data enum values.
     * The values are the item data.
     * @return a HashMap with all the Item data.
     */
    public HashMap<Data, String> getAllData() {
        HashMap<Data, String> itemData = new HashMap<>();
        itemData.put(Data.ID, this.id);
        itemData.put(Data.NAME, this.name);
        itemData.put(Data.DESCRIPTION, this.description);
        itemData.put(Data.QUANTITY, String.valueOf(this.quantity));
        itemData.put(Data.SUPPLIER, this.supplier);
        itemData.put(Data.SUPPLIER_ID, this.supplierId);
        itemData.put(Data.PRICE, String.valueOf(this.price));
        return itemData;
    }
    
    /**
     * Get Order Info.
     * The keys are the Data enum values.
     * The values are the item data.
     * @return a HashMap with all the Item data.
     */
    public String getOrderInfo() {
        return "<html>Name: " + this.name + "<br>" + "Description: " + this.description + "<br>" + "Price: £" + this.price + "</html>";
    }
    
    /**
     * Setters
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public float getPrice() {
        return this.price;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getSupplierId() {
        return this.supplierId;
    }
}
