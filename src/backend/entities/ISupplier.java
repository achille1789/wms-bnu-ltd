package src.backend.entities;

 /**
  * An interface to implement add() method for Supplier.
  *
  * @author Vanni Gallo
  * @version 1.0.0
  */
 interface ISupplier {
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
    public Supplier add(String name, String crn, String email, String address, String bankAccount, String sortCode);
 }
