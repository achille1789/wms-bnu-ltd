package src.ui.components;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * NavBar is the class that generates the Menu Nav Bar.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class NavBar {
    // fields
    private static final String VERSION = "Version 1.0\n";
    
    /**
     * Create the main frame's menu bar.
     * 
     * @param frame The frame that the menu bar should be added to.
     */
    public NavBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu = new JMenu("Menu");
        menubar.add(menu);
        JMenuItem item = new JMenuItem("Quit");
        item.addActionListener(e -> quit());
        menu.add(item);
        
        menu = new JMenu("About");
        menubar.add(menu);
        item = new JMenuItem("About this app");
        item.addActionListener(e -> showAbout(frame));
        menu.add(item);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }

    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Running " + VERSION + "Please refer to the README file for any info.", "About BNU Industry Solutions Ltd.", JOptionPane.INFORMATION_MESSAGE);
    }
}
