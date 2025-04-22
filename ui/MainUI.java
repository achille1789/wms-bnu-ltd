package ui; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.components.*;

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
    
    /**
     * Create an MainUI and display its GUI on screen.
     */
    public MainUI() {
        makeFrame();
    }
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame() {
        frame = new JFrame("MainUI");
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));

        new NavBar(frame);
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));
        
        new CustomersPanel(contentPane);
        new GoodsPanel(contentPane);
        new SuppliersPanel(contentPane);
        
        // Create a label at the bottom for status message
        String versionText = NavBar.VERSION;
        contentPane.add(new JLabel(versionText), BorderLayout.SOUTH);
        
//         // Create the toolbar with the buttons
//         JPanel toolbar = new JPanel();
//         toolbar.setLayout(new GridLayout(0, 1));
// 
//         // Add toolbar into panel with flow layout for spacing
//         JPanel flow = new JPanel();
//         flow.add(toolbar);
//         
//         contentPane.add(flow, BorderLayout.WEST);
        
        // building is done - arrange the components     
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}
