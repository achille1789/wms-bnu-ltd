package entities;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that has some methods to handle Customers and Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
abstract class Entity {
    // The fields.
    protected HashMap<Data, String> entityData = new HashMap<>();
    protected List<String> orderHistory = new ArrayList<>(); // TODO: set a proper type
    
    /**
     * Add a new order.
     */
    public abstract void addNewOrder();
    
    /**
     * Update entity data.
     */
    public void update(Data key, String value) {
        entityData.replace(key, value);
    }
    
    /**
     * Get all entity data.
     */
    public HashMap<Data, String> getAllData() {
        return entityData;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return entityData.get(Data.NAME);
    }
}
