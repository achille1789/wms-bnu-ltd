package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.entities.Supplier;
import backend.entities.SupplierData;
import backend.entities.Data;

/**
 * A class that handles the list of Suppliers.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class Suppliers {
    // The fields.
    private List<Supplier> suppliersList = new LinkedList<>();
    
    /**
     * @param prepopulated to instantiate the class with 2 suppliers for debugging
     */
    public Suppliers(boolean prepopulated) {
        if (prepopulated) {
            SupplierData data1 = new SupplierData("Simon&Sons ltd", "12345678", "simon-ltd@gmail.com", "11 Third Road, London", "87654321", "10-20-30");
            SupplierData data2 = new SupplierData("Alex&Friends ltd", "19283746", "alex-ltd@gmail.com", "1 Fourth Road, Glasgow", "98765432", "40-50-60");
            suppliersList.add(new Supplier().add(data1));
            suppliersList.add(new Supplier().add(data2));
            Logger.info("Added 2 suppliers for debugging" + suppliersList.get(0).getAllData().toString());
            Logger.info("Added 2 suppliers for debugging" + suppliersList.get(1).getAllData().toString());
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
        SupplierData data = new SupplierData(name, crn, email, address, bankAccount, sortCode);
        suppliersList.add(new Supplier().add(data));
        Logger.info("Supplier added, data: " + suppliersList.getLast().getAllData().toString());
    }
    
    /**
     * Get the number of suppliers in the list.
     * @return the number of suppliers in the list
     */
    public int getTotalSuppliers() {
        return suppliersList.size();
    }
    
    /**
     * Get the name of all suppliers in the list.
     * @return the name of all suppliers in the list
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
     * @param name the name of the supplier
     * @return a HashMap with all the Supplier data
     */
    public HashMap<Data, String> getSupplierData(String id) {
        HashMap<Data, String> supplierData = null;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (id.equals(suppliersList.get(i).getId())) {
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
     * @param name the name of the supplier
     * @param key the key of the data to update
     * @param value the new value of the data
     */
    public void updateSupplierData(String id, Data key, String value) {
        boolean found = false;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (id.equals(suppliersList.get(i).getId())) {
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
     * @param name the name of the supplier
     */
    public void deleteSupplier(String id) {
        boolean found = false;
        for (int i = 0; i < suppliersList.size(); i++) {
            if (id.equals(suppliersList.get(i).getId())) {
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
