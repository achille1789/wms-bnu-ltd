import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import backend.*;
import backend.orders.*;
import backend.warehouseitems.*;

/**
 * Test class for OrderManager.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class OrderManagerTest {
    @Test
    public void addOrderTest() {
        // arrange
        OrderItem orderItem1 = new OrderItem("item1", 2, "itemId-1", 2 * 10.25f); // 2
        OrderItem orderItem2 = new OrderItem("item2", 3, "itemId-2", 3 * 10.00f); // 3
        OrderItem orderItem3 = new OrderItem("item1", 5, "itemId-1", 5 * 10.25f); // 5
        LinkedList <OrderItem> orderItems1 = new LinkedList<OrderItem>();
        orderItems1.add(orderItem1);
        orderItems1.add(orderItem2);
        orderItems1.add(orderItem3);
        LinkedList <OrderItem> orderItems2 = new LinkedList<OrderItem>();
        orderItems2.add(orderItem1);
        orderItems2.add(orderItem2);
        LinkedList <OrderItem> orderItems3 = new LinkedList<OrderItem>();
        orderItems3.add(orderItem1);
        OrderManager orderManager = new OrderManager();
        orderManager.addOrder("entityId-1", 141.00f, orderItems1, OrderStatus.PENDING);
        orderManager.addOrder("entityId-1", 200.00f, orderItems2, OrderStatus.PENDING);
        orderManager.addOrder("entityId-2", 100.00f, orderItems3, OrderStatus.PENDING);

        // act
        List<Order> orders = orderManager.getOrdersList();
        
        // assert
        assertEquals(3, orders.size());
        assertEquals(3, orders.get(0).getOrderItems().size());
        assertEquals(2, orders.get(1).getOrderItems().size());
        assertEquals(1, orders.get(2).getOrderItems().size());
    }
    
    @Test
    public void getEntityOrdersTest() {
        // arrange
        OrderItem orderItem1 = new OrderItem("item1", 2, "itemId-1", 2 * 10.25f); // 2
        OrderItem orderItem2 = new OrderItem("item2", 3, "itemId-2", 3 * 10.00f); // 3
        OrderItem orderItem3 = new OrderItem("item1", 5, "itemId-1", 5 * 10.25f); // 5
        LinkedList <OrderItem> orderItems1 = new LinkedList<OrderItem>();
        orderItems1.add(orderItem1);
        orderItems1.add(orderItem2);
        orderItems1.add(orderItem3);
        LinkedList <OrderItem> orderItems2 = new LinkedList<OrderItem>();
        orderItems2.add(orderItem1);
        orderItems2.add(orderItem2);
        LinkedList <OrderItem> orderItems3 = new LinkedList<OrderItem>();
        orderItems3.add(orderItem1);
        OrderManager orderManager = new OrderManager();
        orderManager.addOrder("entityId-1", 141.00f, orderItems1, OrderStatus.PENDING);
        orderManager.addOrder("entityId-1", 200.00f, orderItems2, OrderStatus.PENDING);
        orderManager.addOrder("entityId-2", 100.00f, orderItems3, OrderStatus.PENDING);

        // act
        List<Order> orders = orderManager.getEntityOrders("entityId-1");
        
        // assert
        assertEquals(2, orders.size());
    }
    
    @Test
    public void getPendingOrdersTest() {
        // arrange
        OrderItem orderItem1 = new OrderItem("item1", 2, "itemId-1", 2 * 10.25f); // 2
        OrderItem orderItem2 = new OrderItem("item2", 3, "itemId-2", 3 * 10.00f); // 3
        OrderItem orderItem3 = new OrderItem("item1", 5, "itemId-1", 5 * 10.25f); // 5
        LinkedList <OrderItem> orderItems1 = new LinkedList<OrderItem>();
        orderItems1.add(orderItem1);
        orderItems1.add(orderItem2);
        orderItems1.add(orderItem3);
        LinkedList <OrderItem> orderItems2 = new LinkedList<OrderItem>();
        orderItems2.add(orderItem1);
        orderItems2.add(orderItem2);
        LinkedList <OrderItem> orderItems3 = new LinkedList<OrderItem>();
        orderItems3.add(orderItem1);
        OrderManager orderManager = new OrderManager();
        orderManager.addOrder("entityId-1", 141.00f, orderItems1, OrderStatus.PENDING);
        orderManager.addOrder("entityId-1", 200.00f, orderItems2, OrderStatus.PENDING);
        orderManager.addOrder("entityId-2", 100.00f, orderItems3, OrderStatus.SHIPPED);
        orderManager.addOrder("entityId-2", 100.00f, orderItems3, OrderStatus.DELIVERED);
        
        // assert
        assertEquals(4, orderManager.getOrdersList().size());
        
        // act
        List<Order> orders = orderManager.getPendingOrders();
        
        // assert
        assertEquals(2, orders.size());
    }
    
    @Test
    public void setNewOrderStatusTest() {
        // arrange
        OrderItem orderItem1 = new OrderItem("item1", 2, "itemId-1", 2 * 10.25f); // 2
        OrderItem orderItem2 = new OrderItem("item2", 3, "itemId-2", 3 * 10.00f); // 3
        OrderItem orderItem3 = new OrderItem("item1", 5, "itemId-1", 5 * 10.25f); // 5
        LinkedList <OrderItem> orderItems = new LinkedList<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        OrderManager orderManager = new OrderManager();
        orderManager.addOrder("entityId-1", 141.00f, orderItems, OrderStatus.PENDING);
        orderManager.addOrder("entityId-1", 200.00f, orderItems, OrderStatus.PENDING);
        
        // assert
        assertEquals(2, orderManager.getPendingOrders().size());
        
        // act
        orderManager.setNewOrderStatus(OrderStatus.DELIVERED);
        
        // assert
        assertEquals(0, orderManager.getPendingOrders().size());
    }
}