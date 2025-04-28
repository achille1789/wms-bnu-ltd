package backend.entities;

import java.time.Instant;
import java.util.HashMap;

/**
 * A class to create Customer objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Customer extends Entity implements ICustomer {
    // The fields.
    HashMap<Data, String> entityData;
    
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
        String rand = String.valueOf((int)(Math.random() * 1000));
        String id = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
        this.entityData = getEntityData();
        this.entityData.put(Data.ID, id);
        this.entityData.put(Data.NAME, name);
        this.entityData.put(Data.SURNAME, surname);
        this.entityData.put(Data.EMAIL, email);
        this.entityData.put(Data.ADDRESS, address);
        this.entityData.put(Data.CREDIT_CARD, creditCard);
        return this;
    }
    
    /**
     * Add a new Customer order.
     */
    @Override
    public void addNewOrder() {
        System.out.println("new order placed"); // TODO: remove log
    }
    
    /**
     * Getters
     */
    public String getSurname() {
        return this.entityData.get(Data.SURNAME);
    }
}
