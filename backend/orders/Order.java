package backend.orders;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Instant;

/**
 * A class that defines Order with many Items.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Order {
    // The fields.
    private String orderId;
    private String entityId;
    private String date;
    private float totalCost;
    private OrderStatus status;
    private List<OrderItem> orderItems = new ArrayList<>();
    
    /**
     * Add a new warehouse item.
     * Info needed are: name, description, available quantity, supplier name and price.
     *
     * @param entityId the entity that created the order
     * @param orderItems the list of order items
     */
    public Order(String entityId, float totalCost, List<OrderItem> orderItems, OrderStatus status) {
        this.entityId = entityId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
        this.status = status;
        LocalDateTime now = LocalDateTime.now();
        this.date = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
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
        orderData.put(OrderData.TOTAL_COST, String.valueOf(this.totalCost));
        orderData.put(OrderData.STATUS, this.status.name());
        String items = "";
        for (int i = 0; i < this.orderItems.size(); i++) {
            items += this.orderItems.get(i).getAllData().toString();
        }
        orderData.put(OrderData.ORDER_ITEMS, items);
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
    
    public String getDate() {
        return this.date;
    }
    
    public float getTotalCost() {
        return this.totalCost;
    }
    
    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }
    
    public OrderStatus getStatus() {
        return this.status;
    }
    
    /**
     * Setters
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
