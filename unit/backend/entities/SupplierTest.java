import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import backend.entities.Supplier;
import backend.entities.Data;

/**
 * Test class for Supplier.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class SupplierTest {
    @Test
    public void testAdd() {
        // act
        Supplier supplier = new Supplier().add("name", "12345678", "email@test.com", "1 address London", "87654321", "10-20-30");
        
        // assert
        assertNotNull(supplier);
        assertEquals("name", supplier.getName());
        assertEquals(14, supplier.getId().length());       
        HashMap<Data, String> allData = supplier.getAllData();
        assertEquals("name", allData.get(Data.NAME));
        assertEquals("12345678", allData.get(Data.CRN));
        assertEquals("email@test.com", allData.get(Data.EMAIL));
        assertEquals("1 address London", allData.get(Data.ADDRESS));
        assertEquals("87654321", allData.get(Data.BANK_ACCOUNT));
        assertEquals("10-20-30", allData.get(Data.SORT_CODE));
    }
    
    @Test
    public void testUpdate() {
        // act
        Supplier supplier = new Supplier().add("name", "12345678", "email@test.com", "1 address London", "87654321", "10-20-30");
        
        // assert
        HashMap<Data, String> allData = supplier.getAllData();
        assertEquals("name", allData.get(Data.NAME));
        assertEquals("12345678", allData.get(Data.CRN));
        assertEquals("email@test.com", allData.get(Data.EMAIL));
        assertEquals("1 address London", allData.get(Data.ADDRESS));
        assertEquals("87654321", allData.get(Data.BANK_ACCOUNT));
        assertEquals("10-20-30", allData.get(Data.SORT_CODE));
        
        // act
        supplier.update(Data.NAME, "name2");
        supplier.update(Data.CRN, "00001111");
        supplier.update(Data.EMAIL, "email2@test.com");
        supplier.update(Data.ADDRESS, "2 address Dublin");
        supplier.update(Data.BANK_ACCOUNT, "11112222");
        supplier.update(Data.SORT_CODE, "30-20-10");
        // assert
        HashMap<Data, String> allNewData = supplier.getAllData();
        assertEquals("name2", allNewData.get(Data.NAME));
        assertEquals("00001111", allNewData.get(Data.CRN));
        assertEquals("email2@test.com", allNewData.get(Data.EMAIL));
        assertEquals("2 address Dublin", allNewData.get(Data.ADDRESS));
        assertEquals("11112222", allNewData.get(Data.BANK_ACCOUNT));
        assertEquals("30-20-10", allNewData.get(Data.SORT_CODE));
    }
}