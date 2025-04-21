package backend.entities;

/**
 * A class to create Supplier objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Supplier extends Entity implements ISupplier {
    
    /**
     * Add a new entity Supplier.
     * @param supplier the SupplierData object containing the supplier's data
     * @return the Supplier object
     */
    public Supplier add(SupplierData supplier) {
        entityData.put(Data.NAME, supplier.getName());
        entityData.put(Data.CRN, supplier.getCRN());
        entityData.put(Data.EMAIL, supplier.getEmail());
        entityData.put(Data.ADDRESS, supplier.getAddress());
        entityData.put(Data.BANK_ACCOUNT, supplier.getBankAccount());
        entityData.put(Data.SORT_CODE, supplier.getSortCode());
        return this;
    }
    
    /**
     * Add a new Supplier order.
     */
    public void addNewOrder() {
        System.out.println("new order placed"); // TODO: remove log
    }
}
