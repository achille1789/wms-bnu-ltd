package entities;

 /**
  * A class to specify needed data for a Supplier.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
public class SupplierData extends EntityData {
    // The fields.
    private String crn;
    private String bankAccount;
    private String sortCode;
    
    // TODO: implement input validity checks
    /**
     * Add a new entity Supplier data.
     */
    public SupplierData(String name, String crn, String email, String address, String bankAccount, String sortCode) {
        super(name, email, address);
        this.crn = crn;
        this.bankAccount = bankAccount;
        this.sortCode = sortCode;
    }
    
    /**
     * Getters
     */
    public String getCRN() {
        return this.crn;
    }
    
    public String getBankAccount() {
        return this.bankAccount;
    }
    
    public String getSortCode() {
        return this.sortCode;
    }
}
