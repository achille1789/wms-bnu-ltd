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
    public float price;
    public String id;
    
    /**
     * Add a new warehouse item.
     * Info needed are: name, description, available quantity and supplier name.
     */
    public Item(String name, String description, int quantity, String supplier, float price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplier = supplier;
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
        itemData.put(Data.PRICE, String.valueOf(this.price));
        return itemData;
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
}
