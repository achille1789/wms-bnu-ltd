package backend.entities;

 /**
  * An interface to implement add() method for Customer.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
 interface ISupplier {
    /**
     * Add a new entity Supplier.
     * Info needed are: name, crn, email, address, bank account and sort code.
     * @param supplier the SupplierData object with all the data
     */
    public Supplier add(SupplierData supplier);
 }
