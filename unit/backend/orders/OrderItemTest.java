import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import backend.orders.*;

/**
 * Test class for OrderItem.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class OrderItemTest {
    @Test
    public void OrderItemTest() {
        // act
        OrderItem orderItem = new OrderItem("item1", 10, "itemId-1", 20.55f);
        // assert
        assertNotNull(orderItem);
        assertEquals("item1", orderItem.getName());
        assertEquals(20.55f, orderItem.getCost());
        assertEquals("itemId-1", orderItem.getItemId());
        assertEquals(10, orderItem.getQuantity());

        // act
        HashMap<OrderItemData, String> allData = orderItem.getAllData();
        // assert
        assertEquals("item1", allData.get(OrderItemData.NAME));
        assertEquals("10", allData.get(OrderItemData.QUANTITY));
        assertEquals("itemId-1", allData.get(OrderItemData.ITEM_ID));
        assertEquals("20.55", allData.get(OrderItemData.COST));
    }
}