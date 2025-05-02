package backend.entities;

import java.util.HashMap;

import utils.IdGenerator;

/**
 * A class to create Supplier objects.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Supplier extends Entity implements ISupplier {
    
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
    public Supplier add(String name, String crn, String email, String address, String bankAccount, String sortCode) {
        String id = IdGenerator.getId();
        HashMap<Data, String> supplierData = getEntityData();
        supplierData.put(Data.ID, id);
        supplierData.put(Data.NAME, name);
        supplierData.put(Data.CRN, crn);
        supplierData.put(Data.EMAIL, email);
        supplierData.put(Data.ADDRESS, address);
        supplierData.put(Data.BANK_ACCOUNT, bankAccount);
        supplierData.put(Data.SORT_CODE, sortCode);
        return this;
    }
}
