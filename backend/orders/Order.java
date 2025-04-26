package backend.orders;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Instant;

/**
 * A class that defines Order with many Items.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Order {
    // The fields.
    public String orderId;
    public String entityId;
    public LocalDate date;
    public List<OrderItem> orderItems = new ArrayList<>();
    
    /**
     * Add a new warehouse item.
     * Info needed are: name, description, available quantity, supplier name and price.
     *
     * @param entityId the entity that created the order
     * @param orderItems the list of order items
     */
    public Order(String entityId, List<OrderItem> orderItems) {
        this.entityId = entityId;
        this.orderItems = orderItems;
        this.date = LocalDate.now();
        String rand = String.valueOf((int)(Math.random() * 1000));
        this.orderId = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
    }
    
    /**
     * Get a HashMap with all the item data.
     * The keys are the Data enum values.
     * The values are the item data.
     * @return a HashMap with all the Item data.
     */
    public HashMap<OrderData, String> getAllData() {
        HashMap<OrderData, String> orderData = new HashMap<>();
        orderData.put(OrderData.ORDER_ID, this.orderId);
        orderData.put(OrderData.ENTITY_ID, this.entityId);
        orderData.put(OrderData.DATE, this.date.toString());
        orderData.put(OrderData.ORDER_ITEMS, this.orderItems.toString());
        return orderData;
    }
    
    /**
     * Getters
     */
    public String getOrderId() {
        return this.orderId;
    }
    
    public String getEntityId() {
        return this.entityId;
    }
    
    public LocalDate getDate() {
        return this.date;
    }
    
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }
}
