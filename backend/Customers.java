package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Customer;
import backend.entities.CustomerData;
import backend.entities.Data;

/**
 * A class that handles the list of Customers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class Customers {
    // The fields.
    private List<Customer> customersList = new LinkedList<>();
    
    /**
     * @param prepopulated to instantiate the class with 2 customers for debugging
     */
    public Customers(boolean prepopulated) {
        if (prepopulated) {
            CustomerData data1 = new CustomerData("Vanni", "Gallo", "vanni@gmail.com", "12 First Road, London", "1234-5678-9012-3456");
            CustomerData data2 = new CustomerData("Mark", "Luton", "mark@gmail.com", "2 Second Road, Glasgow", "9876-5432-1098-7654");
            customersList.add(new Customer().add(data1));
            customersList.add(new Customer().add(data2));
            Logger.info("Added 2 customers for debugging");
        }
    }
    
    /**
     * Establish name matching.
     * @param name the name to match
     * @param surname the surname to match
     * @param itemIndex the index of the customer in the list
     * @return true if the name and surname match the customer data
     */
    private boolean areDetailsMatching(String name, String surname, int itemIndex) {
        return name.toLowerCase().equals(customersList.get(itemIndex).getName().toLowerCase()) && 
            surname.toLowerCase().equals(customersList.get(itemIndex).getSurname().toLowerCase());
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
        CustomerData data = new CustomerData(name, surname, email, address, creditCard);
        boolean exists = false;
        for (int i = 0; i < customersList.size(); i++) {
            if (areDetailsMatching(name, surname, i)) {
                Logger.error("Customer already exists, aborted");
                exists = true;
                break;
            }
        }
        if (!exists) {
            customersList.add(new Customer().add(data));
            Logger.info("Customer added, data: " + customersList.getLast().getAllData().toString());
        }
    }
    
    /**
     * Get the number of customers in the list.
     * @return the number of customers in the list
     */
    public int getTotalCustomers() {
        return customersList.size();
    }
    
    /**
     * Get the name of all customers in the list.
     * @return an array of customer names
     */
    public String[] getCustomerNames() {
        String[] names = new String[customersList.size()];
        for (int i = 0; i < customersList.size(); i++) {
            names[i] = customersList.get(i).getName();
        }
        return names;
    }
    
    /**
     * Get data of the passed Customer.
     * @param name the name of the customer
     * @param surname the surname of the customer
     * @return a HashMap with all the customer data
     */
    public HashMap<Data, String> getCustomerData(String name, String surname) {
        HashMap<Data, String> customerData = null;
        for (int i = 0; i < customersList.size(); i++) {
            if (areDetailsMatching(name, surname, i)) {
                customerData = customersList.get(i).getAllData();
                break;
            }
        }
        if (customerData == null) {
            Logger.error("Customer not found");
        }
        return customerData;
    }
    
    /**
     * Update data of the passed Customer.
     * @param name the name of the customer
     * @param surname the surname of the customer
     * @param key the key of the data to update
     * @param value the new value of the data
     */
    public void updateCustomerData(String name, String surname, Data key, String value) {
        boolean found = false;
        for (int i = 0; i < customersList.size(); i++) {
            if (areDetailsMatching(name, surname, i)) {
                customersList.get(i).update(key, value);
                Logger.info("Customer data updated: " + customersList.get(i).getAllData().toString());
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Customer not found, data not updated");
        }
    }
    
    /**
     * Delete passed Customer.
     * @param name the name of the customer
     * @param surname the surname of the customer
     */
    public void deleteCustomer(String name, String surname) {
        boolean found = false;
        for (int i = 0; i < customersList.size(); i++) {
            if (areDetailsMatching(name, surname, i)) {
                customersList.remove(i);
                Logger.info("Customer deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Customer not found, data not updated");
        }
    }
 }
