// package ui.components;
// 
// import java.awt.*;
// import javax.swing.*;
// 
// /**
//  * SuppliersPanel is the class that generates the Suppliers Panel.
//  * 
//  * @author Vanni Gallo
//  * @version 1.0
//  */
// public class SuppliersPanel {
//     /**
//      * Create the Suppliers panel.
//      * 
//      * @param mainUIContentPane The contentPane that is created in MainUI.
//      */
//     public SuppliersPanel(JPanel mainUIContentPane) {        
//         JPanel panel = new JPanel();
//         panel.setBackground(Color.darkGray);
//         panel.setPreferredSize(new Dimension(250, 100));
// //         panel.setSize(300,300);
//         FlowLayout layout = new FlowLayout();
//         layout.setHgap(10);
//         layout.setVgap(10);
//         
//         panel.setLayout(layout);   
//         panel.add(new JButton("Supplier Panel"));
//         
//         mainUIContentPane.add(panel, BorderLayout.EAST);
//     }
// }