package src.backend.entities;

 /**
  * An interface to implement add() method for Customer.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
 interface ICustomer {
    /**
     * Add a new entity Customer.
     * Info needed are: name, surname, email, address and credit card.
     * @param name of the customer
     * @param surname of the customer
     * @param email of the customer
     * @param address of the customer
     * @param creditCard of the customer
     */
    public Customer add(String name, String surname, String email, String address, String creditCard);
    
    /**
     * Customer's getters.
     * @return the surname of the customer
     */
     public String getSurname();
 }
