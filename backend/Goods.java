import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import goods.Good;
import goods.Data;

/**
 * A class that handles the list of warehouse Goods.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 class Goods {
    // The fields.
    private List<Good> goodsList = new LinkedList<>();
    
    // Constructor should be uncommented only for debugging
//     public Goods() {
//         Good good1 = new Good("Brick", "Construction bricks", 100, "Vanni ltd");
//         Good good2 = new Good("Cement", "Construction cement", 50, "Mark ltd");
//         goodsList.add(good1);
//         goodsList.add(good2);
//         Logger.info("Added 2 warehouse goods for debugging");
//     }
    
    /**
     * Establish name matching.
     * @param name the name to match
     * @param itemIndex the index of the good in the list
     * @return true if the name matches the good data
     */
    private boolean isNameMatching(String name, int itemIndex) {
        return name.toLowerCase().equals(goodsList.get(itemIndex).getName().toLowerCase());
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
    public void addGood(String name, String description, int quantity, String supplier) {
        Good good = new Good(name, description, quantity, supplier);
        boolean exists = false;
        for (int i = 0; i < goodsList.size(); i++) {
            if (isNameMatching(name, i)) {
                Logger.error("Good already exists, aborted");
                exists = true;
                break;
            }
        }
        if (!exists) {
            goodsList.add(good);
            Logger.info("Good added in warehouse, data: " + goodsList.getLast().getAllData().toString());
        }
    }
    
    /**
     * Get the number of goods in the list.
     * @return the number of goods in the list
     */
    public int getTotalGoods() {
        return goodsList.size();
    }
    
    /**
     * Get the name of all goods in the list.
     * @return an array of good names
     */
    public String[] getGoodNames() {
        String[] names = new String[goodsList.size()];
        for (int i = 0; i < goodsList.size(); i++) {
            names[i] = goodsList.get(i).getName();
        }
        return names;
    }
    
    /**
     * Get data of the passed Good.
     * @param name the name of the good
     * @return a HashMap with all the Good data
     */
    public HashMap<Data, String> getGoodData(String name) {
        HashMap<Data, String> goodData = null;
        for (int i = 0; i < goodsList.size(); i++) {
            if (isNameMatching(name, i)) {
                goodData = goodsList.get(i).getAllData();
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
    public int getGoodQuantity(String name) {
        int quantity = -1;
        for (int i = 0; i < goodsList.size(); i++) {
            if (isNameMatching(name, i)) {
                quantity = goodsList.get(i).getQuantity();
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
    public void updateGoodQuantity(String name, int quantity) {
        boolean found = false;
        for (int i = 0; i < goodsList.size(); i++) {
            if (isNameMatching(name, i)) {
                goodsList.get(i).setQuantity(quantity);
                Logger.info("Good quantity updated: " + goodsList.get(i).getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            Logger.error("Good not found, quantity not updated");
        }
    }
 }
