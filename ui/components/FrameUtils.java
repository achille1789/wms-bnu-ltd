package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

/**
 * FrameUtils is a class that provides utility static methods to configure frames and panels.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class FrameUtils {
    /**
     * Set the default close operation for a JFrame.
     * 
     * @param frame The JFrame to set the default close operation for.
     */
    public static void centerFrame(JFrame frame) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create a vertical scrollable pane
     *
     * @param panel The JPanel to be added to the JScrollPane
     * @return A JScrollPane containing the specified JPanel
     */
    public static JScrollPane createVerticalScrollablePane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }
    
    /**
     * Create high contrast panel
     *
     * @param panel The JPanel to make DARK_GRAY
     * @param label The JLabel to make WHITE
     */
    public static void createHighContrastPanel(JPanel panel, JLabel label) {
        panel.setBackground(Color.DARK_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));   
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);
    }
    
    /**
     * Create high contrast panel
     *
     * @param panel The JPanel to make DARK_GRAY
     */
    public static void createHighContrastPanel(JPanel panel) {
        panel.setBackground(Color.DARK_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));   
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    
    /**
     * Create high contrast panel
     *
     * @param panel The JPanel to make DARK_GRAY
     */
    public static void createReverseHighContrastPanel(JPanel panel, boolean vertical) {
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, vertical ? BoxLayout.Y_AXIS : BoxLayout.X_AXIS));
    }
}