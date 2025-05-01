package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.*;

import backend.*;
import backend.finances.FinanceItem;

/**
 * FinancesPanel is the class that generates the Bottom Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class FinancesPanel {
    // fields
    private Finance financeSales;
    private Finance financePurchases;
    
    /**
     * Create a FinancesPanel instance.
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param financeSales The instance of the Finance class for sales.
     * @param financePurchases The instance of the Finance class for purchases.
     */
    public FinancesPanel(JPanel mainUIContentPane, Finance financeSales, Finance financePurchases) {
        this.financeSales = financeSales;
        this.financePurchases = financePurchases;
        
        JPanel panel = new JPanel();
        FrameUtils.createHighContrastPanel(panel);
        JButton reportBtn = new JButton("Generate Financial Report");
        reportBtn.addActionListener(e -> createFinancialReportFrame());
        panel.add(reportBtn);
        
        mainUIContentPane.add(panel, BorderLayout.SOUTH);
    }
    
    /**
     * Create the financial report frame.
     */
    private void createFinancialReportFrame() {
        JFrame frame = new JFrame("Financial Report");
        frame.setSize(700, 500);
        
        JPanel panel = new JPanel();
        FrameUtils.createHighContrastPanel(panel);
        
        String[] salesColumns = {"Item Name", "Sold Units", "Total Revenue"};    
        buildTable(panel, "Sales", this.financeSales, salesColumns);
        String[] purchasesColumns = {"Item Name", "Purchased Units", "Total Cost"};
        buildTable(panel, "Purchases", this.financePurchases, purchasesColumns);
        
        JLabel totalSalesLabel = new JLabel("Total Sales: £" + this.financeSales.getAllTransactionsCost());
        totalSalesLabel.setForeground(Color.WHITE);
        JLabel totalPurchasesLabel = new JLabel("Total Purchases: £" + this.financePurchases.getAllTransactionsCost());
        totalPurchasesLabel.setForeground(Color.WHITE);
        float totalRevenues = this.financeSales.getAllTransactionsCost() - this.financePurchases.getAllTransactionsCost();
        JLabel totalRevenuesLabel = new JLabel("Net Income: £" + totalRevenues);
        totalRevenuesLabel.setForeground(totalRevenues >= 0 ? Color.GREEN : Color.RED);
        panel.add(totalSalesLabel);
        panel.add(totalPurchasesLabel);
        panel.add(totalRevenuesLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> frame.dispose());
        panel.add(closeBtn);
        
        frame.add(panel);
        FrameUtils.centerFrame(frame);
    }
    
    /**
     * Get the labels text.
     * @param panel the panel where to add the table
     * @param labelText the text of the label
     * @param finances the finances object
     * @param columns the columns of the table
     */
     private void buildTable(JPanel panel, String labelText, Finance finances, String[] columns) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        HashMap<String, FinanceItem> transactionsMap = finances.getAllTransactions();
        if (transactionsMap.isEmpty()) {
            label.setText(labelText + " - No transactions found");
            panel.add(label);
            panel.add(Box.createRigidArea(new Dimension(0, 30)));
            return;
        }
        Object[][] transactions = new Object[transactionsMap.size()][3]; 
        int i = 0;
        for (Map.Entry<String, FinanceItem> entry : transactionsMap.entrySet()) {
            transactions[i][0] = entry.getKey();
            FinanceItem item = entry.getValue();
            transactions[i][1] = item.getQuantity();
            transactions[i][2] = item.getCost();
            i++;
        }
        JScrollPane scrollPanel = new JScrollPane(new JTable(transactions, columns));
        panel.add(label);
        panel.add(scrollPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
     }
}