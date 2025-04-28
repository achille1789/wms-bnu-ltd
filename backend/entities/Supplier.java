package backend.entities;

import java.time.Instant;
import java.util.HashMap;

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
        String rand = String.valueOf((int)(Math.random() * 1000));
        String id = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
        HashMap<Data, String> entityData = getEntityData();
        entityData.put(Data.ID, id);
        entityData.put(Data.NAME, name);
        entityData.put(Data.CRN, crn);
        entityData.put(Data.EMAIL, email);
        entityData.put(Data.ADDRESS, address);
        entityData.put(Data.BANK_ACCOUNT, bankAccount);
        entityData.put(Data.SORT_CODE, sortCode);
        return this;
    }
    
    /**
     * Add a new Supplier order.
     */
    @Override
    public void addNewOrder() {
        System.out.println("new order placed"); // TODO: remove log
    }
}
