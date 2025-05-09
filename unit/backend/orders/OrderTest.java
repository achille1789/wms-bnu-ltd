import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import src.backend.orders.*;

/**
 * Test class for Order.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class OrderTest {
    @Test
    public void OrderTest() {
        // arrange
        OrderItem orderItem1 = new OrderItem("item1", 10, "itemId-1", 10);
        OrderItem orderItem2 = new OrderItem("item2", 20, "itemId-2", 20);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        
        // act
        Order order = new Order("entityId-1", 500, orderItems, OrderStatus.PENDING);
        // assert
        assertNotNull(order);
        assertEquals(14, order.getOrderId().length());
        assertEquals("entityId-1", order.getEntityId());
        assertEquals(500.00, order.getTotalCost());
        assertEquals(2, order.getOrderItems().size());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(21, order.getDate().length());
        
        // act
        HashMap<OrderData, String> allData = order.getAllData();
        // assert
        assertEquals(14, allData.get(OrderData.ORDER_ID).length());
        assertEquals("entityId-1", allData.get(OrderData.ENTITY_ID));
        assertEquals("500.0", allData.get(OrderData.TOTAL_COST));
        assertEquals(true, allData.get(OrderData.ORDER_ITEMS).contains("NAME=item1"));
        assertEquals(true, allData.get(OrderData.ORDER_ITEMS).contains("NAME=item2"));
        assertEquals("PENDING", allData.get(OrderData.STATUS));
        assertEquals(21, allData.get(OrderData.DATE).length());
        
        // act
        order.setStatus(OrderStatus.DELIVERED);
        // assert
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }
}