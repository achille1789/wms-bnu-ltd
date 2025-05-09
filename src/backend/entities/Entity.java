package src.backend.entities;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that has some methods to handle Customers and Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public abstract class Entity {
    // The fields.
    private HashMap<Data, String> entityData = new HashMap<>();
    
    /**
     * Update entity data.
     * @param key the key of the data to update
     * @param value the new value of the data
     */
    public void update(Data key, String value) {
        this.entityData.replace(key, value);
    }
    
    /**
     * Get all entity data.
     * The keys are the Data enum values.
     * The values are the entity data.
     * @return a HashMap with all the entity data.
     */
    public HashMap<Data, String> getAllData() {
        return this.entityData;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.entityData.get(Data.NAME);
    }    
    public String getId() {
        return this.entityData.get(Data.ID);
    }
}
