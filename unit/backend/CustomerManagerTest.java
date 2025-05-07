import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import backend.*;
import backend.entities.*;

/**
 * Test class for CustomerManager.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomerManagerTest {
    @Test
    public void addCustomerTest() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // act
        CustomerManager customerManager = new CustomerManager(); 
        customerManager.addCustomer("name1", "surname1", "email1", "address1", "1234567887654321");
        customerManager.addCustomer("name2", "surname2", "email2", "address2", "0000111122223333");
        System.setOut(originalOut);      
        // assert
        assertEquals(true, outContent.toString().contains("[INFO]"));
        assertEquals(true, outContent.toString().contains("NAME=name1"));
        assertEquals(true, outContent.toString().contains("NAME=name2"));
        List<Entity> customersList = customerManager.getEntitiesList();
        assertNotNull(customersList);
        assertEquals(2, customersList.size());
    }

    @Test
    public void getEntityDataTest() {       
        // arrange
        CustomerManager customerManager = new CustomerManager(); 
        customerManager.addCustomer("name1", "surname1", "email1", "address1", "1234567887654321");
        customerManager.addCustomer("name2", "surname2", "email2", "address2", "0000111122223333");
        // act
        List<Entity> customersList = customerManager.getEntitiesList();
        String customer1Id = customersList.get(0).getId();
        String customer2Id = customersList.get(1).getId();
        // assert
        HashMap<Data, String> customer1Data = customerManager.getEntityData(customer1Id);
        assertEquals("name1", customer1Data.get(Data.NAME));
        HashMap<Data, String> customer2Data = customerManager.getEntityData(customer2Id);
        assertEquals("name2", customer2Data.get(Data.NAME));
        assertEquals(null, customerManager.getEntityData("random-id"));
    }
    
    @Test
    public void updateEntityDataTest() {       
        // arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        CustomerManager customerManager = new CustomerManager(); 
        customerManager.addCustomer("name1", "surname1", "email1", "address1", "1234567887654321");
        String customer1Id = customerManager.getEntitiesList().get(0).getId();
        
        // act
        HashMap<Data, String> newData = new HashMap<Data, String>();
        newData.put(Data.NAME, "name-1");
        newData.put(Data.SURNAME, "surname-1");
        newData.put(Data.EMAIL, "email-1");
        newData.put(Data.ADDRESS, "address-1");
        newData.put(Data.CREDIT_CARD, "9999888877776666");
        customerManager.updateEntityData(customer1Id, newData);  
        
        // assert
        HashMap<Data, String> customer1Data = customerManager.getEntityData(customer1Id);
        assertEquals("name-1", customer1Data.get(Data.NAME));
        assertEquals("surname-1", customer1Data.get(Data.SURNAME));
        assertEquals("email-1", customer1Data.get(Data.EMAIL));
        assertEquals("address-1", customer1Data.get(Data.ADDRESS));
        assertEquals("9999888877776666", customer1Data.get(Data.CREDIT_CARD));
        assertEquals(true, outContent.toString().contains("[INFO]"));
        assertEquals(true, outContent.toString().contains("NAME=name-1"));
        
        // act
        HashMap<Data, String> anotherCustomerData = new HashMap<Data, String>();
        anotherCustomerData.put(Data.NAME, "name");
        anotherCustomerData.put(Data.SURNAME, "surname");
        anotherCustomerData.put(Data.EMAIL, "email");
        anotherCustomerData.put(Data.ADDRESS, "address");
        anotherCustomerData.put(Data.CREDIT_CARD, "xxx");
        customerManager.updateEntityData("random-id", anotherCustomerData);
        System.setOut(originalOut); 
        
        // assert
        assertEquals(true, outContent.toString().contains("[ERROR] - Customer not found, data not updated"));
    }
    
    @Test
    public void deleteEntityTest() {       
        // arrange
        CustomerManager customerManager = new CustomerManager(); 
        customerManager.addCustomer("name1", "surname1", "email1", "address1", "1234567887654321");
        customerManager.addCustomer("name2", "surname2", "email2", "address2", "0000111122223333");
        // act
        List<Entity> customersList = customerManager.getEntitiesList();
        String customer1Id = customersList.get(0).getId();
        String customer2Id = customersList.get(1).getId();
        assertEquals(2, customersList.size());
        customerManager.deleteEntity(customer1Id);
        // assert
        assertEquals(1, customersList.size());
        HashMap<Data, String> customer2Data = customerManager.getEntityData(customer2Id);
        assertEquals("name2", customer2Data.get(Data.NAME));
    }
    
    @Test
    public void getEntityByIdTest() {       
        // arrange
        CustomerManager customerManager = new CustomerManager(); 
        customerManager.addCustomer("name1", "surname1", "email1", "address1", "1234567887654321");
        customerManager.addCustomer("name2", "surname2", "email2", "address2", "0000111122223333");
        
        // act
        List<Entity> customersList = customerManager.getEntitiesList();
        String customer1Id = customersList.get(0).getId();
        String customer2Id = customersList.get(1).getId();
        
        // assert
        HashMap<Data, String> customer1Data = customerManager.getEntityData(customer1Id);
        assertEquals("name1", customer1Data.get(Data.NAME));
        assertEquals(null, customerManager.getEntityData("random-id"));
    }
}