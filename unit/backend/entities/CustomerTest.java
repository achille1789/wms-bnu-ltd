import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import backend.entities.Customer;
import backend.entities.Data;

/**
 * Test class for Customer.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomerTest {
    @Test
    public void testAdd() {
        // act
        Customer customer = new Customer().add("name", "surname", "email@test.com", "1 address London", "1234567887654321");
        
        // assert
        assertNotNull(customer);
        assertEquals("name", customer.getName());
        assertEquals("surname", customer.getSurname());
        assertEquals(14, customer.getId().length());       
        HashMap<Data, String> allData = customer.getAllData();
        assertEquals("name", allData.get(Data.NAME));
        assertEquals("surname", allData.get(Data.SURNAME));
        assertEquals("email@test.com", allData.get(Data.EMAIL));
        assertEquals("1 address London", allData.get(Data.ADDRESS));
        assertEquals("1234567887654321", allData.get(Data.CREDIT_CARD));
    }
    
    @Test
    public void testUpdate() {
        // act
        Customer customer = new Customer().add("name", "surname", "email@test.com", "1 address London", "1234567887654321");
        
        // assert
        HashMap<Data, String> allData = customer.getAllData();
        assertEquals("name", allData.get(Data.NAME));
        assertEquals("surname", allData.get(Data.SURNAME));
        assertEquals("email@test.com", allData.get(Data.EMAIL));
        assertEquals("1 address London", allData.get(Data.ADDRESS));
        assertEquals("1234567887654321", allData.get(Data.CREDIT_CARD));
        assertEquals(14, allData.get(Data.ID).length());
        
        // act
        customer.update(Data.NAME, "name2");
        customer.update(Data.SURNAME, "surname2");
        customer.update(Data.EMAIL, "email2@test.com");
        customer.update(Data.ADDRESS, "2 address Dublin");
        customer.update(Data.CREDIT_CARD, "1111222233334444");
        // assert
        HashMap<Data, String> allNewData = customer.getAllData();
        assertEquals("name2", allNewData.get(Data.NAME));
        assertEquals("surname2", allNewData.get(Data.SURNAME));
        assertEquals("email2@test.com", allNewData.get(Data.EMAIL));
        assertEquals("2 address Dublin", allNewData.get(Data.ADDRESS));
        assertEquals("1111222233334444", allNewData.get(Data.CREDIT_CARD));
    }
}