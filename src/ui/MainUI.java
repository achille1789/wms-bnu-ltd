package src.ui; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import src.ui.components.*;
import src.backend.*;

/**
 * MainUI is the main class used to build the User Interface. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class MainUI {
    // fields
    private JFrame frame;
    private CustomerManager customers;
    private InventoryManager items;
    private SupplierManager suppliers;
    private OrderManager suppliersOrders;
    private OrderManager customersOrders;
    private FinancialReporting financeSales;
    private FinancialReporting financePurchases;
    
    /**
     * Create an MainUI and display its GUI on screen.
     */
    public MainUI() {
        this.customers = new CustomerManager();
        this.items = new InventoryManager();
        this.suppliers = new SupplierManager();
        this.suppliersOrders = new OrderManager();
        this.customersOrders = new OrderManager();
        this.financeSales = new FinancialReporting(this.customersOrders);
        this.financePurchases = new FinancialReporting(this.suppliersOrders);
        makeFrame();
    }
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {
        frame = new JFrame("BNU Industry Solutions Ltd.");
        frame.setSize(800, 700);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        new NavBar(frame);
        contentPane.setLayout(new BorderLayout(6, 6));        
        
        new CustomersPanel(contentPane, this.customers, this.customersOrders, this.items, BorderLayout.WEST);
        new ItemsPanel(contentPane, this.items, this.suppliers);
        new SuppliersPanel(contentPane, this.suppliers, this.suppliersOrders, this.items, BorderLayout.EAST);
        new FinancesPanel(contentPane, this.financeSales, this.financePurchases);
        
        FrameUtils.centerFrame(frame);
    }
}
