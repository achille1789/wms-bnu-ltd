package entities;

/**
 * A class to create Supplier objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
class Supplier extends Entity implements ISupplier {
    
    public void add(SupplierData supplier) {
        entityData.put(Data.NAME, supplier.getName());
        entityData.put(Data.CRN, supplier.getCRN());
        entityData.put(Data.EMAIL, supplier.getEmail());
        entityData.put(Data.ADDRESS, supplier.getAddress());
        entityData.put(Data.BANK_ACCOUNT, supplier.getBankAccount());
        System.out.println("Supplier data: " + entityData);
    }
    
    public void addNewOrder() {
        System.out.println("new order placed");
    }
}
