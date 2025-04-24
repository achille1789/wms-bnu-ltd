package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Customer;
import backend.entities.Data;

/**
 * A class that handles the list of Customers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class CustomersList extends EntitiesList implements ICustomersList {
    /**
     * @param prepopulated to instantiate the class with 2 customers for debugging
     */
    public CustomersList(boolean prepopulated) {
        if (prepopulated) {
            entitiesList.add(new Customer().add("Vanni", "Gallo", "vanni@gmail.com", "12 First Road, London", "1234-5678-9012-3456"));
            entitiesList.add(new Customer().add("Mark", "Luton", "mark@gmail.com", "2 Second Road, Glasgow", "9876-5432-1098-7654"));
            Logger.info("Added 2 customers for debugging");
        }
    }
    
    // TODO: handle wrong input data
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
        entitiesList.add(new Customer().add(name, surname, email, address, creditCard));
        Logger.info("Customer added, data: " + entitiesList.getLast().getAllData().toString());
    }
    
    /**
     * Get the list of entities.
     * @param type of log.
     * @param message to log.
     */
    @Override
    protected void printlog(LogType type, String message) {
        message = message.replace("Entity", "Customer");
        switch (type) {
            case LogType.ERROR:
                Logger.error(message);
                break;
            case LogType.WARN:
                Logger.warn(message);
                break;
            case LogType.INFO:
            default:
                Logger.info(message);
        }
    }
}
