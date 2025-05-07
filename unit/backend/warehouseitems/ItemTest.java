import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import backend.warehouseitems.Item;
import backend.warehouseitems.ItemData;

/**
 * Test class for Item.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class ItemTest {
    @Test
    public void ItemTest() {
        // act
        Item item = new Item("name1", "description 1", 100, "supplier1", "supplierId-1", 20.55f);
        // assert
        assertNotNull(item);
        assertEquals("name1", item.getName());
        assertEquals(100, item.getQuantity());
        assertEquals(20.55f, item.getSupplierPrice());
        assertEquals(14, item.getId().length());   
        assertEquals("description 1", item.getDescription());
        assertEquals("supplierId-1", item.getSupplierId());

        // act
        HashMap<ItemData, String> allData = item.getAllData();
        // assert
        assertEquals("name1", allData.get(ItemData.NAME));
        assertEquals("100", allData.get(ItemData.QUANTITY));
        assertEquals("20.55", allData.get(ItemData.SUPPLIER_PRICE));
        assertEquals(14, allData.get(ItemData.ID).length());
        assertEquals("description 1", allData.get(ItemData.DESCRIPTION));
        assertEquals("supplierId-1", allData.get(ItemData.SUPPLIER_ID));
        assertEquals("supplier1", allData.get(ItemData.SUPPLIER));
        assertEquals("28.77", allData.get(ItemData.CUSTOMER_PRICE));
    }
    
    @Test
    public void setQuantityTest() {
        // act
        Item item = new Item("name1", "description 1", 100, "supplier1", "supplierId-1", 20.55f);
        HashMap<ItemData, String> allData = item.getAllData();
        // assert
        assertNotNull(item);
        assertEquals(100, item.getQuantity());
        assertEquals("100", allData.get(ItemData.QUANTITY));

        // act
        item.setQuantity(200);
        // assert
        assertEquals(200, item.getQuantity());
        HashMap<ItemData, String> allNewData = item.getAllData();
        assertEquals("200", allNewData.get(ItemData.QUANTITY));
    }
}