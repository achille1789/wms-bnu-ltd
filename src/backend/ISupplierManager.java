package src.backend;

import src.backend.entities.Supplier;

 /**
  * An interface to implement addSupplier() method for SupplierManager.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
public interface ISupplierManager {
    /**
     * Add a new entity Supplier.
     * Info needed are: name, crn, email, address, bank account and sort code.
     * @param name of the supplier
     * @param crn of the supplier
     * @param email of the supplier
     * @param address of the supplier
     * @param bankAccount of the supplier
     * @param sortCode of the supplier
     */
    public void addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode);
    
    /**
     * Get a list of all the suppliers.
     * @return an array of all the suppliers
     */
    public String[] getSuppliersName();
    
    /**
     * Get a list of all the suppliers.
     * @param name the name of the supplier
     * @return the id of the supplier
     */
    public String getSupplierIdByName(String name);
}
 