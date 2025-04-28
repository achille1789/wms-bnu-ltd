package backend.orders;

import java.util.HashMap;

/**
 * A class that defines an Item that is part of a Order.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class OrderItem {
    // The fields.
    private String name;
    private int quantity;
    private String itemId;
    private float cost;
    
    /**
     * Add a new order item.
     * Info needed are: name, quantity, itemId name and cost.
     *
     * @param name the name of the item
     * @param quantity the quantity of the item
     * @param itemId the itemId of the item
     * @param cost the cost of the item
     */
    public OrderItem(String name, int quantity, String itemId, float cost) {
        this.name = name;
        this.quantity = quantity;
        this.itemId = itemId;
        this.cost = cost;
    }
    
    /**
     * Get a HashMap with all the order item data.
     * The keys are the Data enum values.
     * The values are the item data.
     * @return a HashMap with all the Item data.
     */
    public HashMap<OrderItemData, String> getAllData() {
        HashMap<OrderItemData, String> orderItemData = new HashMap<>();
        orderItemData.put(OrderItemData.NAME, this.name);
        orderItemData.put(OrderItemData.QUANTITY, String.valueOf(this.quantity));
        orderItemData.put(OrderItemData.ITEM_ID, this.itemId);
        orderItemData.put(OrderItemData.COST, String.valueOf(this.cost));
        return orderItemData;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    public float getCost() {
        return this.cost;
    }
    public String getItemId() {
        return this.itemId;
    }
}
