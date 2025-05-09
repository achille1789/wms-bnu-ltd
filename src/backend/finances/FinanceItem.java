package src.backend.finances;

import java.util.HashMap;

/**
 * A class that defines a FinanceItem to store total items purchased/sold and their cost.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class FinanceItem {
    // The fields.
    private int quantity;
    private float cost;
    
    /**
     * Add a new finance item.
     * Info needed are: quantity and cost.
     */
    public FinanceItem() {
        this.quantity = 0;
        this.cost = 0;
    }
    
    /**
     * Add items quantity to the previous value 
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    /**
     * Add items cost/revenue to the previous value 
     */
    public void addCost(float cost) {
        this.cost += cost;
    }
    
    /**
     * Getters
     */
    public int getQuantity() {
        return this.quantity;
    }
    public float getCost() {
        return this.cost;
    }
}