import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import entities.Supplier;
import entities.SupplierData;
import entities.Data;

/**
 * A class that handles the list of Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 class Suppliers {
    // The fields.
    private List<Supplier> suppliersList = new LinkedList<>();
    
    // Constructor should be uncommented only for debugging
//     public Suppliers() {
//         SupplierData data1 = new SupplierData("Vanni ltd", "12345678", "vanni-ltd@gmail.com", "11 Third Road, London", "87654321", "10-20-30");
//         SupplierData data2 = new SupplierData("Mark ltd", "19283746", "mark-ltd@gmail.com", "1 Fourth Road, Glasgow", "98765432", "40-50-60");
//         suppliersList.add(new Supplier().add(data1));
//         suppliersList.add(new Supplier().add(data2));
//         Logger.info("Added 2 suppliers for debugging");
//     }
    
    /**
     * Establish name matching.
     */
    private boolean isNameMatching(String name, int itemIndex) {
        return name.toLowerCase().equals(suppliersList.get(itemIndex).getName().toLowerCase());
    }
    
    // TODO: handle wrong input data
    /**
     * Set all the supplier data to create a new supplier.
     * If data is invalid print an error message.
     */
    public void addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode) {
        SupplierData data = new SupplierData(name, crn, email, address, bankAccount, sortCode);
        boolean exists = false;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (isNameMatching(name, i)) {
                Logger.error("Supplier already exists, aborted");
                exists = true;
                break;
            }
        }
        if (!exists) {
            suppliersList.add(new Supplier().add(data));
            Logger.info("Supplier added, data: " + suppliersList.getLast().getAllData().toString());
        }
    }
    
    /**
     * Get the number of suppliers in the list.
     */
    public int getTotalSuppliers() {
        return suppliersList.size();
    }
    
    /**
     * Get the name of all suppliers in the list.
     */
    public String[] getSupplierNames() {
        String[] names = new String[suppliersList.size()];
        for (int i = 0; i < suppliersList.size(); i++) {
            names[i] = suppliersList.get(i).getName();
        }
        return names;
    }
    
    /**
     * Get data of the passed Supplier.
     */
    public HashMap<Data, String> getSupplierData(String name) {
        HashMap<Data, String> supplierData = null;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (isNameMatching(name, i)) {
                supplierData = suppliersList.get(i).getAllData();
                break;
            }
        }
        if (supplierData == null) {
            Logger.error("Supplier not found");
        }
        return supplierData;
    }
    
    /**
     * Update data of the passed Supplier.
     */
    public void updateSupplierData(String name, Data key, String value) {
        boolean found = false;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (isNameMatching(name, i)) {
                suppliersList.get(i).update(key, value);
                Logger.info("Supplier data updated: " + suppliersList.get(i).getAllData().toString());
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Supplier not found, data not updated");
        }
    }
    
    /**
     * Delete passed Supplier.
     */
    public void deleteSupplier(String name) {
        boolean found = false;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (isNameMatching(name, i)) {
                suppliersList.remove(i);
                Logger.info("Supplier deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Supplier not found, data not updated");
        }
    }
 }
