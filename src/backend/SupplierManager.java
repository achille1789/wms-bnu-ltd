package src.backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import src.backend.entities.Supplier;
import src.backend.entities.Data;
import src.backend.entities.Entity;
import src.utils.Logger;

/**
 * A class that handles the list of Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class SupplierManager extends EntityManager implements ISupplierManager {    
    /**
     * The constructor that will create a SupplierManager object that extends EntityManager and implements ISupplierManager.
     * This set the log entity type to Supplier.
     */
    public SupplierManager() {
        setLogEntityType("Supplier");
    }
    
    /**
     * Set all the supplier data to create a new supplier.
     * If data is invalid print an error message.
     * @param name the name of the supplier
     * @param crn the company registration number of the supplier
     * @param email the email of the supplier
     * @param address the address of the supplier
     * @param bankAccount the bank account of the supplier
     * @param sortCode the sort code of the supplier
     */
    public void addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode) {
        getEntitiesList().add(new Supplier().add(name, crn, email, address, bankAccount, sortCode));
        Logger.info("Supplier added, data: " + getEntitiesList().getLast().getAllData().toString());
    }
    
    /**
     * Get a list of all the suppliers.
     * @return an array of all the supplier names
     */
    public String[] getSuppliersName() {
        List<Entity> entitiesList = getEntitiesList();
        String[] names = new String[entitiesList.size()];
        for (int i = 0; i < entitiesList.size(); i++) {
            names[i] = entitiesList.get(i).getName();
        }
        return names;
    }
    
    /**
     * Get a list of all the suppliers.
     * @param name the name of the supplier
     * @return the id of the supplier
     */
    public String getSupplierIdByName(String name) {
        String id = null;
        List<Entity> entitiesList = getEntitiesList();
        for (int i = 0; i < entitiesList.size(); i++) {
            if (name.equals(entitiesList.get(i).getName())) {
                id = entitiesList.get(i).getId();
                break;
            }
        }
        if (id == null) {
            Logger.error("Supplier id not found");
        }
        return id;
    }
}
