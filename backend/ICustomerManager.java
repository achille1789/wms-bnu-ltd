package backend;

import backend.entities.Customer;

 /**
  * An interface to implement addCustomer() method for CustomersManager.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
public interface ICustomerManager {
    /**
     * Add a new Customer on the list.
     * Info needed are: name, surname, email, address and credit card.
     * @param name of the customer
     * @param surname of the customer
     * @param email of the customer
     * @param address of the customer
     * @param creditCard of the customer
     */
    public void addCustomer(String name, String surname, String email, String address, String creditCard);
}
 