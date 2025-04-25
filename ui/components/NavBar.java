package ui.components;

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
    // static fields
    public static final String VERSION = "Version 1.0";
    
    /**
     * Create the main frame's menu bar.
     * 
     * @param frame The frame that the menu bar should be added to.
     */
    public NavBar(JFrame frame) {
        final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu menu = new JMenu("File");
        menubar.add(menu);
        
        JMenuItem item = new JMenuItem("Quit");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
        item.addActionListener(e -> quit());
        menu.add(item);

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About Main UI...");
        item.addActionListener(e -> showAbout(frame));
        menu.add(item);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }

    // TODO: add description
    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "MainUI\n" + VERSION, "About Main UI", JOptionPane.INFORMATION_MESSAGE);
    }
}