package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Supplier;
import backend.entities.Data;

/**
 * A class that handles the list of Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class SuppliersList extends EntitiesList implements ISuppliersList {    
    /**
     * @param prepopulated to instantiate the class with 2 suppliers for debugging
     */
    public SuppliersList(boolean prepopulated) {
        if (prepopulated) {
            entitiesList.add(new Supplier().add("Simon&Sons ltd", "12345678", "simon-ltd@gmail.com", "11 Third Road, London", "87654321", "10-20-30"));
            entitiesList.add(new Supplier().add("Alex&Friends ltd", "19283746", "alex-ltd@gmail.com", "1 Fourth Road, Glasgow", "98765432", "40-50-60"));
            Logger.info("Added 2 suppliers for debugging");
        }
    }
    
    // TODO: handle wrong input data
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
        entitiesList.add(new Supplier().add(name, crn, email, address, bankAccount, sortCode));
        Logger.info("Supplier added, data: " + entitiesList.getLast().getAllData().toString());
    }
    
    /**
     * Get the list of entities.
     * @param type of log.
     * @param message to log.
     */
    @Override
    protected void printlog(LogType type, String message) {
        message = message.replace("Entity", "Supplier");
        switch (type) {
            case LogType.ERROR:
                Logger.error(message);
                break;
            case LogType.WARN:
                Logger.warn(message);
                break;
            case LogType.INFO:
            default:
                Logger.info(message);
        }
    }
}
