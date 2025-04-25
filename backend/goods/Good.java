package backend.goods;

import java.util.HashMap;
import java.time.Instant;

/**
 * A class that defines a warehouse Good.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Good {
    // The fields.
    public String name;
    public String description;
    public int quantity;
    public String supplier;
    public float price;
    public String id;
    
    /**
     * Add a new warehouse good.
     * Info needed are: name, description, available quantity and supplier name.
     */
    public Good(String name, String description, int quantity, String supplier, float price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplier = supplier;
        this.price = price;
        String rand = String.valueOf((int)(Math.random() * 1000));
        this.id = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
    }
    
    /**
     * Get a HashMap with all the good data.
     * The keys are the Data enum values.
     * The values are the good data.
     * @return a HashMap with all the Good data.
     */
    public HashMap<Data, String> getAllData() {
        HashMap<Data, String> goodData = new HashMap<>();
        goodData.put(Data.ID, this.id);
        goodData.put(Data.NAME, this.name);
        goodData.put(Data.DESCRIPTION, this.description);
        goodData.put(Data.QUANTITY, String.valueOf(this.quantity));
        goodData.put(Data.SUPPLIER, this.supplier);
        goodData.put(Data.PRICE, String.valueOf(this.price));
        return goodData;
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
