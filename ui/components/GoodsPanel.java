package ui.components;

import java.awt.*;
import javax.swing.*;

/**
 * GoodsPanel is the class that generates the Goods Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class GoodsPanel {
    /**
     * Create the Warehouse Goods panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     */
    public GoodsPanel(JPanel mainUIContentPane) {        
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
//         panel.setSize(300,300);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        
        panel.setLayout(layout);   
        panel.add(new JButton("Good Panel"));
        
        mainUIContentPane.add(panel, BorderLayout.CENTER);
    }
}