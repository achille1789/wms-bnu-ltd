package backend.items;

import java.util.HashMap;
import java.time.Instant;

/**
 * A class that defines a warehouse Item.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Item {
    // The fields.
    public String name;
    public String description;
    public int quantity;
    public String supplier;
    public String supplierId;
    public float supplierPrice;
    public float customerPrice;
    public String id;
    
    /**
     * Add a new warehouse item.
     * Info needed are: name, description, available quantity, supplier name and supplierPrice.
     *
     * @param name the name of the item
     * @param description the description of the item
     * @param quantity the available quantity of the item
     * @param supplier the supplier of the item
     * @param supplierPrice the supplierPrice of the item
     */
    public Item(String name, String description, int quantity, String supplier, String supplierId, float supplierPrice) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.supplier = supplier;
        this.supplierId = supplierId;
        this.supplierPrice = supplierPrice;
        this.customerPrice = getCustomerPrice(supplierPrice);
        String rand = String.valueOf((int)(Math.random() * 1000));
        this.id = String.valueOf(Instant.now().toEpochMilli()) + "-" + rand;
    }
    
    /**
     * Get a HashMap with all the item data.
     * The keys are the Data enum values.
     * The values are the item data.
     * @return a HashMap with all the Item data.
     */
    public HashMap<ItemData, String> getAllData() {
        HashMap<ItemData, String> itemData = new HashMap<>();
        itemData.put(ItemData.ID, this.id);
        itemData.put(ItemData.NAME, this.name);
        itemData.put(ItemData.DESCRIPTION, this.description);
        itemData.put(ItemData.QUANTITY, String.valueOf(this.quantity));
        itemData.put(ItemData.SUPPLIER, this.supplier);
        itemData.put(ItemData.SUPPLIER_ID, this.supplierId);
        itemData.put(ItemData.SUPPLIER_PRICE, String.valueOf(this.supplierPrice));
        itemData.put(ItemData.CUSTOMER_PRICE, String.valueOf(this.customerPrice));
        return itemData;
    }
    
    /**
     * Setters
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    public float getSupplierPrice() {
        return this.supplierPrice;
    }
    
    public float getCustomerPrice() {
        return this.customerPrice;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getSupplierId() {
        return this.supplierId;
    }
    
    /**
     * Set the customer price.
     * The customer price is 40% more than the supplier price.
     * @param supplierPrice the supplier price
     * @return the customer price
     */
    private float getCustomerPrice(float supplierPrice) {
        return supplierPrice * 1.4f;
    }
}
