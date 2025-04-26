package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import backend.ItemsList;
import backend.SuppliersList;
import backend.items.*;

/**
 * ItemsPanel is the class that generates the Items Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class ItemsPanel {
    // fields
    private ItemsList items;
    private SuppliersList suppliers;
    private JPanel itemsPanel;
    private JLabel totalItemsLabel;

    /**
     * Create the Warehouse Items panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param items The instance of the ItemsList class.
     */
    public ItemsPanel(JPanel mainUIContentPane, ItemsList items, SuppliersList suppliers) {
        this.items = items;
        this.suppliers = suppliers;
        this.itemsPanel = new JPanel();
        this.itemsPanel.setBackground(Color.DARK_GRAY);       
        this.itemsPanel.setLayout(new BoxLayout(this.itemsPanel, BoxLayout.Y_AXIS));   
        this.itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.totalItemsLabel = new JLabel(this.items.getItemsList().size() + " warehouse items in catalog");
        this.totalItemsLabel.setPreferredSize(new Dimension(160, 50));
        this.totalItemsLabel.setForeground(Color.WHITE);
        this.itemsPanel.add(this.totalItemsLabel);
        JButton add = new JButton("Add Warehouse Item");
        add.addActionListener(e -> createAddItemFieldsFrame(Action.ADD, null, ""));
        this.itemsPanel.add(add);
        this.itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.items.getItemsList().size(); i++) {
            Item item = this.items.getItemsList().get(i);
            createItemsPanel(item);
        }
        
        // set vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(this.itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        mainUIContentPane.add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Create a panel for an Item.
     *
     * @param item The Item object.
     */
    private void createItemsPanel(Item item) {
        setItemsPanelDetails(item);
        this.itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    /**
     * Set item details into the panel.
     * 
     * @param item The Item object.
     */
    private void setItemsPanelDetails(Item item) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel(" Item: " + item.getName() + " ");
        panel.add(labelName);
        JLabel labelDescription = new JLabel(" Description: " + item.getDescription() + " ");
        panel.add(labelDescription);
        JLabel labelPrice = new JLabel(" Price: £" + item.getPrice() + " ");
        panel.add(labelPrice);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        JButton delete = new JButton("Delete Item");
        delete.addActionListener(e -> {
            this.items.deleteItem(item.getId());
            this.itemsPanel.remove(panel);
            this.itemsPanel.revalidate();
            this.itemsPanel.repaint();
            this.totalItemsLabel.setText(this.items.getItemsList().size() + " warehouse items in catalog");
        });
        panel.add(delete);
        
        this.itemsPanel.add(panel);
    }
    
    /**
     * Create a new frame panel to update item data.
     * 
     * @param action type: update or add.
     * @param label The Label of the Item panel to update the name showed.
     * @param id The Item id.
     */
    private void createAddItemFieldsFrame(Action action, JLabel label, String id) {
        JFrame frame = new JFrame("Add Warehouse Item");
        frame.setSize(700, 200);

        // Create a panel and use GridLayout for label + field pairs
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 cols, spacing
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input components
        HashMap<Data, InputPair> itemFields = new HashMap<>();
        itemFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
        itemFields.put(Data.DESCRIPTION, new InputPair(new JLabel("Description:"), new JTextField("", 15)));
        itemFields.put(Data.QUANTITY, new InputPair(new JLabel("Quantity:"), new JTextField("", 15)));
        JComboBox<String> comboBox = new JComboBox<>(this.suppliers.getSuppliersName());
        itemFields.put(Data.SUPPLIER, new InputPair(new JLabel("Supplier:"), comboBox)); // TODO: add list of suppliers
        itemFields.put(Data.PRICE, new InputPair(new JLabel("Price:"), new JTextField("", 15)));

        JButton confirmBtn = new JButton("Add Item");
        confirmBtn.setForeground(new Color(0, 153, 0));
        confirmBtn.addActionListener(e -> {
            String name = itemFields.get(Data.NAME).getTextFieldString();
            String description = itemFields.get(Data.DESCRIPTION).getTextFieldString();
            int quantity = Integer.parseInt(itemFields.get(Data.QUANTITY).getTextFieldString());
            String supplier = itemFields.get(Data.SUPPLIER).getDropListSelected();
            float price = Float.parseFloat(itemFields.get(Data.PRICE).getTextFieldString());
            String supplierId = this.suppliers.getSupplierIdByName(supplier);
            this.items.addItem(name, description, quantity, supplier, supplierId, price);
            this.totalItemsLabel.setText(this.items.getItemsList().size() + " warehouse items in catalog");
            Item item = this.items.getItemsList().getLast();
            createItemsPanel(item);
            frame.dispose();
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setForeground(new Color(255, 153, 0));
        cancelBtn.addActionListener(e -> frame.dispose());

        // Add components to the Add Item Panel
        for (InputPair field : itemFields.values()) {
            updatePanel.add(field.getLabel()); // JLabel
            if (field.getLabelString().toUpperCase().equals(Data.SUPPLIER.name() + ":")) {
                updatePanel.add(field.getDropList()); // JComboBox<String>
            } else {
                updatePanel.add(field.getTextField()); // JTextField
            }
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