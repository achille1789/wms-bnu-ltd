package backend.entities;

 /**
  * A class to specify needed data for a Customer.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
public class CustomerData extends EntityData {
    // The fields.
    private String surname;
    private String creditCard;
    
    // TODO: implement input validity checks
    /**
     * Add a new entity Customer data.
     * @param name the name of the customer
     * @param surname the surname of the customer
     * @param email the email of the customer
     * @param address the address of the customer
     * @param creditCard the credit card of the customer
     */
    public CustomerData(String name, String surname, String email, String address, String creditCard) {
        super(name, email, address);
        this.surname = surname;
        this.creditCard = creditCard;
    }
    
    /**
     * Getters
     */
    public String getSurname() {
        return this.surname;
    }
    
    public String getCreditCard() {
        return this.creditCard;
    }
}
