package backend;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import backend.finances.FinanceItem;
import backend.orders.*;

/**
 * A class to perform Financial calculations.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
 public class FinancialReporting {
    // The fields.
    private OrderManager ordersList;
    
    /**
     * Constructor
     * @param ordersList the list of orders
     */
    public FinancialReporting(OrderManager ordersList) {
        this.ordersList = ordersList;
    }
    
    /**
     * Get the list of sales or purchases.
     * @return the name of the item associated with the total units sold/purchased and the total cost
     */
    public HashMap<String, FinanceItem> getAllTransactions() {
        HashMap<String, FinanceItem> allTransaction = new HashMap<>();
        for (int i = 0; i < this.ordersList.getOrdersList().size(); i++) {
            Order order = this.ordersList.getOrdersList().get(i);
            for (OrderItem item : order.getOrderItems()) {
                if (!allTransaction.containsKey(item.getName())) {
                    allTransaction.put(item.getName(), new FinanceItem());
                }
                allTransaction.get(item.getName()).addQuantity(item.getQuantity());
                allTransaction.get(item.getName()).addCost(item.getCost());
            }
        }
        return allTransaction;
    }
    
    /**
     * Get total cost of all the sales or purchases.
     * @return the total cost
     */
    public float getAllTransactionsCost() {
        float totalCost = 0;
        for (int i = 0; i < this.ordersList.getOrdersList().size(); i++) {
            Order order = this.ordersList.getOrdersList().get(i);
            totalCost += order.getTotalCost();
        }
        return totalCost;
    }
}