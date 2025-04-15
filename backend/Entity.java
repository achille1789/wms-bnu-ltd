import java.util.HashMap;
import java.util.LinkedList;

/**
 * An abstract class that has some methods to handle Users and Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
abstract class Entity {
    // The fields.
    protected HashMap<String, String> entityInfo = new HashMap<>();
    protected LinkedList<String> orderHistory = new LinkedList<>(); // TODO: set a proper type
    
    /**
     * Add a new order.
     */
    public abstract void addNewOrder();
    
    /**
     * Update entity info.
     */
    public void update(String key, String value) {
        entityInfo.replace(key, value);
        System.out.println(entityInfo);
        System.out.println("New info: " + entityInfo);
    }
    
    /**
     * Get all entity info.
     */
    public HashMap<String, String> getAllInfo() {
        return entityInfo;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return entityInfo.get("name");
    }
}
