package backend;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import backend.goods.*;
import backend.Logger;

/**
 * A class that handles the list of warehouse Goods.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class GoodsList {
    // The fields.
    private List<Good> goodsList = new LinkedList<>();
    
    /**
     * @param prepopulated to instantiate the class with 2 warehouse goods for debugging
     */
    public GoodsList(boolean prepopulated) {
        if (prepopulated) {
            Good good1 = new Good("Brick", "Construction bricks", 100, "Vanni&Sons ltd", 5);
            Good good2 = new Good("Cement", "Construction cement", 50, "Mark&Friends ltd", 15);
            this.goodsList.add(good1);
            this.goodsList.add(good2);
            Logger.info("Added 2 warehouse goods for debugging");
        }
    }
    
    // TODO: handle wrong input data
    /**
     * Set all the good details to create a new Good.
     * If data is invalid print an error message.
     * @param name the name of the good
     * @param description the description of the good
     * @param quantity the available quantity of the good
     * @param supplier the name of the supplier
     */
    public void addGood(String name, String description, int quantity, String supplier, float price) {
        Good good = new Good(name, description, quantity, supplier, price);
        this.goodsList.add(good);
        Logger.info("Good added in warehouse, data: " + this.goodsList.getLast().getAllData().toString());
    }
    
    /**
     * Get the number of goods in the list.
     * @return the list of goods
     */
    public List<Good> getGoodsList() {
        return this.goodsList;
    }
    
    /**
     * Get data of the passed Good.
     * @param name the name of the good
     * @return a HashMap with all the Good data
     */
    public HashMap<Data, String> getGoodData(String id) {
        HashMap<Data, String> goodData = null;
        for (int i = 0; i < this.goodsList.size(); i++) {
            if (id.equals(this.goodsList.get(i).getId())) {
                goodData = this.goodsList.get(i).getAllData();
                break;
            }
        }
        if (goodData == null) {
            Logger.error("Good not found");
        }
        return goodData;
    }
    
    /**
     * Get Good quantity
     * @param name the name of the good
     * @return the quantity of the good
     */
    public int getGoodQuantity(String id) {
        int quantity = -1;
        for (int i = 0; i < this.goodsList.size(); i++) {
            if (id.equals(this.goodsList.get(i).getId())) {
                quantity = this.goodsList.get(i).getQuantity();
                break;
            }
        }
        if (quantity == -1) {
            Logger.error("Good not found, quantity not updated");
        }
        Logger.info("Good quantity: " + quantity);
        return quantity;
    }
    
    /**
     * Update available quantity of the passed Good.
     * @param name the name of the good
     * @param quantity the new quantity of the good
     */
    public void updateGoodQuantity(String id, int quantity) {
        boolean found = false;
        for (int i = 0; i < this.goodsList.size(); i++) {
            if (id.equals(this.goodsList.get(i).getId())) {
                this.goodsList.get(i).setQuantity(quantity);
                Logger.info("Good quantity updated: " + this.goodsList.get(i).getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Good not found, quantity not updated");
        }
    }
    
    /**
     * Delete passed Goods.
     * @param id the id of the good
     */
    public void deleteGood(String id) {
        boolean found = false;
        for (int i = 0; i < this.goodsList.size(); i++) {
            if (id.equals(this.goodsList.get(i).getId())) {
                this.goodsList.remove(i);
                Logger.info("Good deleted");
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Good not found, data not updated");
        }
    }
 }
