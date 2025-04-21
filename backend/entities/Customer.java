package entities;

/**
 * A class to create Customer objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Customer extends Entity implements ICustomer {
    
    /**
     * Add a new entity Customer.
     * Info needed are: name, surname, email, address and credit card.
     * @param customer the CustomerData object with all the data
     */
    public Customer add(CustomerData customer) {
        entityData.put(Data.NAME, customer.getName());
        entityData.put(Data.SURNAME, customer.getSurname());
        entityData.put(Data.EMAIL, customer.getEmail());
        entityData.put(Data.ADDRESS, customer.getAddress());
        entityData.put(Data.CREDIT_CARD, customer.getCreditCard());
        return this;
    }
    
    /**
     * Add a new Customer order.
     */
    public void addNewOrder() {
        System.out.println("new order placed"); // TODO: remove log
    }
    
    /**
     * Getters
     */
    public String getSurname() {
        return entityData.get(Data.SURNAME);
    }
}
