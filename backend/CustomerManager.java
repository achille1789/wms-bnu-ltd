package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Customer;
import backend.entities.Data;
import utils.Logger;

/**
 * A class that handles the list of Customers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class CustomerManager extends EntityManager implements ICustomerManager {
    /**
     * @param prepopulated to instantiate the class with 2 customers for debugging
     */
    public CustomerManager(boolean prepopulated) {
        setLogEntityType("Customer");
        if (prepopulated) {
            getEntitiesList().add(new Customer().add("Vanni", "Gallo", "vanni@gmail.com", "12 First Road, London", "1234-5678-9012-3456"));
            getEntitiesList().add(new Customer().add("Mark", "Luton", "mark@gmail.com", "2 Second Road, Glasgow", "9876-5432-1098-7654"));
            Logger.info("Added 2 customers for debugging");
        }
    }
    
    /**
     * Set all the customer data to create a new customer.
     * If data is invalid print an error message.
     * @param name the name of the customer
     * @param surname the surname of the customer
     * @param email the email of the customer
     * @param address the address of the customer
     * @param creditCard the credit card of the customer
     */
    public void addCustomer(String name, String surname, String email, String address, String creditCard) {
        getEntitiesList().add(new Customer().add(name, surname, email, address, creditCard));
        Logger.info("Customer added, data: " + getEntitiesList().getLast().getAllData().toString());
    }
}
