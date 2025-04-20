package entities;

 /**
  * An interface to implement add() method for Customer.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
 interface ICustomer {
    /**
     * Add a new entity Customer.
     */
    public Customer add(CustomerData customer);
    
    /**
     * Customer's getters.
     */
     public String getSurname();
 }
