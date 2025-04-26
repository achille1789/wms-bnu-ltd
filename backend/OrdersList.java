package backend;

import java.util.List;
import java.util.ArrayList;

import backend.orders.*;
import backend.Logger;

/**
 * A class that handles the list of warehouse Items.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class OrdersList {
    // The fields.
    private List<Order> ordersList = new ArrayList<>();
    
    /**
     * Set all the item details to create a new Item.
     * If data is invalid print an error message.
     *
     * @param entityId the id of the entity that created the order
     * @param orderItems the list of order items
     */
    public void addOrder(String entityId, List<OrderItem> orderItems) {
        Order order = new Order(entityId, orderItems);
        this.ordersList.add(order);
        Logger.info("Order added, data: " + this.ordersList.getLast().getAllData().toString());
    }
    
    /**
     * Get the number of orders in the list.
     * @return the list of orders
     */
    public List<Order> getOrdersList() {
        return this.ordersList;
    }
    
    /**
     * Get list of orders for a passed Entity id.
     * @param entityId the id of the Entity that created the order
     * @return an ArrayList with all the Orders
     */
    public List<Order> getEntityOrders(String id) {
        List<Order> entityOrders = new ArrayList<>();
        for (int i = 0; i < this.ordersList.size(); i++) {
            if (id.equals(this.ordersList.get(i).getEntityId())) {
                entityOrders.add(this.ordersList.get(i));
            }
        }
        return entityOrders;
    }
 }
