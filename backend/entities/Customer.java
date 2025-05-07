package backend.entities;

import java.util.HashMap;

import utils.IdGenerator;

/**
 * A class to create Customer objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Customer extends Entity implements ICustomer {
    // The fields.
    HashMap<Data, String> customerData;
    
    /**
     * Add a new entity Customer.
     * Info needed are: name, surname, email, address and credit card.
     * @param name of the customer
     * @param surname of the customer
     * @param email of the customer
     * @param address of the customer
     * @param creditCard of the customer
     */
    public Customer add(String name, String surname, String email, String address, String creditCard) {
        String id = IdGenerator.getId();
        this.customerData = getAllData();
        this.customerData.put(Data.ID, id);
        this.customerData.put(Data.NAME, name);
        this.customerData.put(Data.SURNAME, surname);
        this.customerData.put(Data.EMAIL, email);
        this.customerData.put(Data.ADDRESS, address);
        this.customerData.put(Data.CREDIT_CARD, creditCard);
        return this;
    }
    
    /**
     * Getters
     */
    public String getSurname() {
        return this.customerData.get(Data.SURNAME);
    }
}
