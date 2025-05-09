import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.LinkedList;

import src.backend.*;
import src.backend.finances.FinanceItem;
import src.backend.orders.*;

/**
 * Test class for FinancialReporting.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class FinancialReportingTest {
    @Test
    public void getAllTransactionsTest() {
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

        // act
        HashMap<String, FinanceItem> allTransaction = new FinancialReporting(orderManager).getAllTransactions();
        
        // assert
        assertEquals(2, allTransaction.size());
        assertEquals(7, allTransaction.get("item1").getQuantity());
        assertEquals(71.75f, allTransaction.get("item1").getCost());
        assertEquals(3, allTransaction.get("item2").getQuantity());
        assertEquals(30.00f, allTransaction.get("item2").getCost());
    }

    @Test
    public void getAllTransactionsCostTest() {       
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
        orderManager.addOrder("entityId-1", 141.00f, orderItems, OrderStatus.PENDING);
        orderManager.addOrder("entityId-1", 141.00f, orderItems, OrderStatus.PENDING);

        // act
        float cost = new FinancialReporting(orderManager).getAllTransactionsCost();
        
        // assert
        assertEquals(423, cost);
    }
}