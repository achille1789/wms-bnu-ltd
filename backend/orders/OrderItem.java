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
    public String name;
    public int quantity;
    public String supplier;
    public float price;
    
    /**
     * Add a new order item.
     * Info needed are: name, quantity, supplier name and price.
     *
     * @param name the name of the item
     * @param quantity the quantity of the item
     * @param supplier the supplier of the item
     * @param price the price of the item
     */
    public OrderItem(String name, int quantity, String supplier, float price) {
        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.price = price;
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
        orderItemData.put(OrderItemData.SUPPLIER, this.supplier);
        orderItemData.put(OrderItemData.PRICE, String.valueOf(this.price));
        return orderItemData;
    }
}
