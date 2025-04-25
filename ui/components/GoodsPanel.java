package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import backend.GoodsList;
import backend.goods.*;

/**
 * GoodsPanel is the class that generates the Goods Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class GoodsPanel {
    // fields
    private GoodsList goods;
    private JPanel goodsPanel;
    private JLabel totalGoodsLabel;

    /**
     * Create the Warehouse Goods panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param goods The instance of the GoodsList class.
     */
    public GoodsPanel(JPanel mainUIContentPane, GoodsList goods) {        
        this.goods = goods;
        this.goodsPanel = new JPanel();
        this.goodsPanel.setBackground(Color.DARK_GRAY);       
        this.goodsPanel.setLayout(new BoxLayout(this.goodsPanel, BoxLayout.Y_AXIS));   
        this.goodsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.totalGoodsLabel = new JLabel(this.goods.getGoodsList().size() + " warehouse goods in catalog");
        this.totalGoodsLabel.setPreferredSize(new Dimension(160, 50));
        this.totalGoodsLabel.setForeground(Color.WHITE);
        this.goodsPanel.add(this.totalGoodsLabel);
        JButton add = new JButton("Add Warehouse Good");
        add.addActionListener(e -> createAddGoodFieldsFrame(Action.ADD, null, ""));
        this.goodsPanel.add(add);
        this.goodsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.goods.getGoodsList().size(); i++) {
            Good good = this.goods.getGoodsList().get(i);
            createGoodsPanel(good);
        }
        
        // set vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(this.goodsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        mainUIContentPane.add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Create a panel for an Good.
     *
     * @param good The Good object.
     */
    private void createGoodsPanel(Good good) {
        setGoodsPanelDetails(good);
        this.goodsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    /**
     * Set good details into the panel.
     * 
     * @param good The Good object.
     */
    private void setGoodsPanelDetails(Good good) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel(" Good: " + good.getName() + " ");
        panel.add(labelName);
        JLabel labelDescription = new JLabel(" Description: " + good.getDescription() + " ");
        panel.add(labelDescription);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        JButton delete = new JButton("Delete Good");
        delete.addActionListener(e -> {
            this.goods.deleteGood(good.getId());
            this.goodsPanel.remove(panel);
            this.goodsPanel.revalidate();
            this.goodsPanel.repaint();
            this.totalGoodsLabel.setText(this.goods.getGoodsList().size() + " warehouse goods in catalog");
        });
        panel.add(delete);
        
        this.goodsPanel.add(panel);
    }
    
    /**
     * Create a new frame panel to update good data.
     * 
     * @param action type: update or add.
     * @param label The Label of the Good panel to update the name showed.
     * @param id The Good id.
     */
    private void createAddGoodFieldsFrame(Action action, JLabel label, String id) {
        JFrame frame = new JFrame("Add Warehouse Good");
        frame.setSize(700, 200);

        // Create a panel and use GridLayout for label + field pairs
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 cols, spacing
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input components
        HashMap<Data, InputPair> goodFields = new HashMap<>();
        goodFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
        goodFields.put(Data.DESCRIPTION, new InputPair(new JLabel("Description:"), new JTextField("", 15)));
        goodFields.put(Data.QUANTITY, new InputPair(new JLabel("Quantity:"), new JTextField("", 15)));
        goodFields.put(Data.SUPPLIER, new InputPair(new JLabel("Supplier:"), new JTextField("", 15))); // TODO: add list of suppliers
        goodFields.put(Data.PRICE, new InputPair(new JLabel("Price:"), new JTextField("", 15)));

        JButton confirmBtn = new JButton("Add Good");
        confirmBtn.setForeground(new Color(0, 153, 0));
        confirmBtn.addActionListener(e -> {
            String name = goodFields.get(Data.NAME).getTextFieldString();
            String description = goodFields.get(Data.DESCRIPTION).getTextFieldString();
            int quantity = Integer.parseInt(goodFields.get(Data.QUANTITY).getTextFieldString());
            String supplier = goodFields.get(Data.SUPPLIER).getTextFieldString();
            float price = Float.parseFloat(goodFields.get(Data.PRICE).getTextFieldString());
            this.goods.addGood(name, description, quantity, supplier, price);
            this.totalGoodsLabel.setText(this.goods.getGoodsList().size() + " warehouse goods in catalog");
            Good good = this.goods.getGoodsList().getLast();
            createGoodsPanel(good);
            frame.dispose();
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setForeground(new Color(255, 153, 0));
        cancelBtn.addActionListener(e -> frame.dispose());

        // Add components to the updatePanel
        for (InputPair field : goodFields.values()) {
            updatePanel.add(field.getLabel()); // JLabel
            updatePanel.add(field.getTextField()); // JTextField
        }
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(confirmBtn);
        updatePanel.add(cancelBtn);

        // Add updatePanel to frame
        frame.add(updatePanel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}