package ui; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.components.*;
import backend.*;

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
    private CustomersList customers;
    private ItemsList items;
    private SuppliersList suppliers;
    private OrdersList suppliersOrders;
    private OrdersList customersOrders;
    private Finance financeSales;
    private Finance financePurchases;
    
    /**
     * Create an MainUI and display its GUI on screen.
     *
     * @param prepopulated If true, the UI will be prepopulated with some data.
     */
    public MainUI(boolean prepopulated) {
        this.customers = new CustomersList(prepopulated);
        this.items = new ItemsList(prepopulated);
        this.suppliers = new SuppliersList(prepopulated);
        this.suppliersOrders = new OrdersList();
        this.customersOrders = new OrdersList();
        this.financeSales = new Finance(this.customersOrders);
        this.financePurchases = new Finance(this.suppliersOrders);
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
