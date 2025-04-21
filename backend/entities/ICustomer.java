package backend.entities;

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
     * @param customer the CustomerData object with all the data
     */
    public Customer add(CustomerData customer);
    
    /**
     * Customer's getters.
     */
     public String getSurname();
 }
