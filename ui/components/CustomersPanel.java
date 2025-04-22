package ui.components;

import java.awt.*;
import javax.swing.*;

/**
 * CustomersPanel is the class that generates the Customers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomersPanel {
    /**
     * Create the Customer panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     */
    public CustomersPanel(JPanel mainUIContentPane) {        
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300,300);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        
        panel.setLayout(layout);   
        panel.add(new JButton("Customer Panel"));
        
        mainUIContentPane.add(panel, BorderLayout.WEST);
    }
}