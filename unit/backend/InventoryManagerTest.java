import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import src.backend.*;
import src.backend.orders.*;
import src.backend.warehouseitems.*;

/**
 * Test class for InventoryManager.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class InventoryManagerTest {
    @Test
    public void addItemTest() {
        // arrange
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 10, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 20, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);

        // act
        List<Item> items = inventoryManager.getItemsList();
        
        // assert
        assertEquals(3, items.size());
    }

    @Test
    public void getItemQuantityTest() {
        // arrange
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 10, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 20, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);
        String itemId1 = inventoryManager.getItemsList().get(0).getId();
        String itemId2 = inventoryManager.getItemsList().get(1).getId();
        String itemId3 = inventoryManager.getItemsList().get(2).getId();

        // act
        int quantity1 = inventoryManager.getItemQuantity(itemId1);
        int quantity2 = inventoryManager.getItemQuantity(itemId2);
        int quantity3 = inventoryManager.getItemQuantity(itemId3);
        
        // assert
        assertEquals(10, quantity1);
        assertEquals(20, quantity2);
        assertEquals(30, quantity3);
        assertEquals(-1, inventoryManager.getItemQuantity("random-id"));
    }
    
    @Test
    public void updateItemQuantityTest() {
        // arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 10, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 20, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);
        String itemId1 = inventoryManager.getItemsList().get(0).getId();
        String itemId2 = inventoryManager.getItemsList().get(1).getId();
        String itemId3 = inventoryManager.getItemsList().get(2).getId();

        // act
        inventoryManager.updateItemQuantity(itemId1, 5);
        inventoryManager.updateItemQuantity(itemId2, 0);
        inventoryManager.updateItemQuantity(itemId3, 25);
        System.setOut(originalOut);
        
        // assert
        assertEquals(true, outContent.toString().contains("[ERROR] - ALERT: new item quantity is 0"));
        assertEquals(true, outContent.toString().contains("[WARN] - ALERT: new item quantity is 5"));
        assertEquals(true, outContent.toString().contains("[INFO] - Item quantity updated: 25"));
        assertEquals(5, inventoryManager.getItemQuantity(itemId1));
        assertEquals(0, inventoryManager.getItemQuantity(itemId2));
        assertEquals(25, inventoryManager.getItemQuantity(itemId3));
    }
    
    @Test
    public void deleteItemTest() {
        // arrange
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 10, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 20, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);
        String itemId1 = inventoryManager.getItemsList().get(0).getId();

        // assert
        List<Item> items = inventoryManager.getItemsList();
        assertEquals(3, items.size());
        assertEquals("item1", items.get(0).getName());
        
        // act
        inventoryManager.deleteItem(itemId1);
        items = inventoryManager.getItemsList();
        
        // assert
        assertEquals(2, items.size());
        assertEquals("item2", items.get(0).getName());
        assertEquals("item3", items.get(1).getName());
    }
    
    @Test
    public void getSupplierItemsTest() {
        // arrange
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 10, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 20, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);

        // act
        List<Item> items = inventoryManager.getSupplierItems("supplierId-1");
        
        // assert
        assertEquals(2, items.size());
        assertEquals("item1", items.get(0).getName());
        assertEquals("item2", items.get(1).getName());
    }
    
    @Test
    public void getItemsLowStockTest() {
        // arrange
        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addItem("item1", "description1", 0, "supplier1", "supplierId-1", 10.00f);
        inventoryManager.addItem("item2", "description2", 5, "supplier1", "supplierId-1", 20.00f);
        inventoryManager.addItem("item3", "description3", 30, "supplier3", "supplierId-3", 30.00f);

        // act
        List<Item> items = inventoryManager.getItemsLowStock();
        
        // assert
        assertEquals(2, items.size());
        assertEquals("item1", items.get(0).getName());
        assertEquals("item2", items.get(1).getName());
    }
}