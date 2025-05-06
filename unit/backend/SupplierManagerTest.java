import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import backend.*;
import backend.entities.*;

/**
 * Test class for SupplierManager.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class SupplierManagerTest {
    @Test
    public void addSupplierTest() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // act
        SupplierManager supplierManager = new SupplierManager(false); 
        supplierManager.addSupplier("name1", "12345678", "email1", "address1", "11112222", "10-20-30");
        supplierManager.addSupplier("name2", "87654321", "email2", "address2", "33334444", "40-50-60");
        System.setOut(originalOut);      
        // assert
        assertEquals(true, outContent.toString().contains("[INFO]"));
        assertEquals(true, outContent.toString().contains("NAME=name1"));
        assertEquals(true, outContent.toString().contains("NAME=name2"));
        List<Entity> suppliersList = supplierManager.getEntitiesList();
        assertNotNull(suppliersList);
        assertEquals(2, suppliersList.size());
    }

    @Test
    public void getEntityDataTest() {       
        // arrange
        SupplierManager supplierManager = new SupplierManager(false); 
        supplierManager.addSupplier("name1", "12345678", "email1", "address1", "11112222", "10-20-30");
        supplierManager.addSupplier("name2", "87654321", "email2", "address2", "33334444", "40-50-60");
        // act
        List<Entity> suppliersList = supplierManager.getEntitiesList();
        String supplier1Id = suppliersList.get(0).getId();
        String supplier2Id = suppliersList.get(1).getId();
        // assert
        HashMap<Data, String> supplier1Data = supplierManager.getEntityData(supplier1Id);
        assertEquals("name1", supplier1Data.get(Data.NAME));
        HashMap<Data, String> supplier2Data = supplierManager.getEntityData(supplier2Id);
        assertEquals("name2", supplier2Data.get(Data.NAME));
        assertEquals(null, supplierManager.getEntityData("random-id"));
    }
    
    @Test
    public void updateEntityDataTest() {       
        // arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        SupplierManager supplierManager = new SupplierManager(false); 
        supplierManager.addSupplier("name1", "12345678", "email1", "address1", "11112222", "10-20-30");
        String supplier1Id = supplierManager.getEntitiesList().get(0).getId();
        
        // act
        HashMap<Data, String> newData = new HashMap<Data, String>();
        newData.put(Data.NAME, "name-1");
        newData.put(Data.CRN, "22334455");
        newData.put(Data.EMAIL, "email-1");
        newData.put(Data.ADDRESS, "address-1");
        newData.put(Data.BANK_ACCOUNT, "33334444");
        newData.put(Data.SORT_CODE, "55-66-77");
        supplierManager.updateEntityData(supplier1Id, newData);  
        
        // assert
        HashMap<Data, String> supplier1Data = supplierManager.getEntityData(supplier1Id);
        assertEquals("name-1", supplier1Data.get(Data.NAME));
        assertEquals("22334455", supplier1Data.get(Data.CRN));
        assertEquals("email-1", supplier1Data.get(Data.EMAIL));
        assertEquals("address-1", supplier1Data.get(Data.ADDRESS));
        assertEquals("33334444", supplier1Data.get(Data.BANK_ACCOUNT));
        assertEquals("55-66-77", supplier1Data.get(Data.SORT_CODE));
        assertEquals(true, outContent.toString().contains("[INFO]"));
        assertEquals(true, outContent.toString().contains("NAME=name-1"));
        
        // act
        HashMap<Data, String> anotherSupplierData = new HashMap<Data, String>();
        anotherSupplierData.put(Data.NAME, "name");
        anotherSupplierData.put(Data.CRN, "xxx");
        anotherSupplierData.put(Data.EMAIL, "email");
        anotherSupplierData.put(Data.ADDRESS, "address");
        anotherSupplierData.put(Data.BANK_ACCOUNT, "xxx");
        newData.put(Data.SORT_CODE, "aa-bb-cc");
        supplierManager.updateEntityData("random-id", anotherSupplierData);
        System.setOut(originalOut); 
        
        // assert
        assertEquals(true, outContent.toString().contains("[ERROR] - Supplier not found, data not updated"));
    }
    
    @Test
    public void deleteEntityTest() {       
        // arrange
        SupplierManager supplierManager = new SupplierManager(false); 
        supplierManager.addSupplier("name1", "12345678", "email1", "address1", "11112222", "10-20-30");
        supplierManager.addSupplier("name2", "87654321", "email2", "address2", "33334444", "40-50-60");
        // act
        List<Entity> suppliersList = supplierManager.getEntitiesList();
        String supplier1Id = suppliersList.get(0).getId();
        String supplier2Id = suppliersList.get(1).getId();
        assertEquals(2, suppliersList.size());
        supplierManager.deleteEntity(supplier1Id);
        // assert
        assertEquals(1, suppliersList.size());
        HashMap<Data, String> supplier2Data = supplierManager.getEntityData(supplier2Id);
        assertEquals("name2", supplier2Data.get(Data.NAME));
    }
    
    @Test
    public void getEntityByIdTest() {       
        // arrange
        SupplierManager supplierManager = new SupplierManager(false); 
        supplierManager.addSupplier("name1", "12345678", "email1", "address1", "11112222", "10-20-30");
        supplierManager.addSupplier("name2", "87654321", "email2", "address2", "33334444", "40-50-60");
        
        // act
        List<Entity> suppliersList = supplierManager.getEntitiesList();
        String supplier1Id = suppliersList.get(0).getId();
        String supplier2Id = suppliersList.get(1).getId();
        
        // assert
        HashMap<Data, String> supplier1Data = supplierManager.getEntityData(supplier1Id);
        assertEquals("name1", supplier1Data.get(Data.NAME));
        assertEquals(null, supplierManager.getEntityData("random-id"));
    }
}