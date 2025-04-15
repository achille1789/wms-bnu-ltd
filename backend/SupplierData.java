 /**
  * A class to specify needed data for a Supplier.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
class SupplierData extends EntityData {
    // The fields.
    private String crn;
    private String bankAccount;
    
    // TODO: implement input validity checks
    public SupplierData(String name, String crn, String email, String address, String bankAccount) {
        super(name, email, address);
        this.crn = crn;
        this.bankAccount = bankAccount;
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
}