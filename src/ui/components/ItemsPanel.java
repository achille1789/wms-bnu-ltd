package src.ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import src.backend.InventoryManager;
import src.backend.SupplierManager;
import src.backend.warehouseitems.*;
import src.utils.*;

/**
 * ItemsPanel is the class that generates the Items Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class ItemsPanel {
    // fields
    private InventoryManager items;
    private SupplierManager suppliers;
    private JPanel itemsPanel;
    private JLabel totalItemsLabel;

    /**
     * Create the Warehouse Items panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param items The instance of the ItemsList class.
     */
    public ItemsPanel(JPanel mainUIContentPane, InventoryManager items, SupplierManager suppliers) {
        this.items = items;
        this.suppliers = suppliers;
        this.itemsPanel = new JPanel();
        this.totalItemsLabel = new JLabel(this.items.getItemsList().size() + " warehouse items in catalog");
        this.totalItemsLabel.setPreferredSize(new Dimension(160, 50));
        FrameUtils.createHighContrastPanel(this.itemsPanel, this.totalItemsLabel);
        JButton add = new JButton("Add Warehouse Item");
        add.addActionListener(e -> createAddItemFieldsFrame(Action.ADD, null, ""));
        this.itemsPanel.add(add);
        this.itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.items.getItemsList().size(); i++) {
            Item item = this.items.getItemsList().get(i);
            createItemsPanel(item);
        }
        
        JScrollPane scrollPane = FrameUtils.createVerticalScrollablePane(this.itemsPanel);        
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
        FrameUtils.createReverseHighContrastPanel(panel, true);
        JLabel labelName = new JLabel(" Item: " + item.getName() + " ");
        panel.add(labelName);
        JLabel labelDescription = new JLabel(" Description: " + item.getDescription() + " ");
        panel.add(labelDescription);
        JLabel labelPrice = new JLabel("Supplier Price: £" + item.getSupplierPrice() + " ");
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

        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        if (this.suppliers.getEntitiesList().size() > 0) {
            HashMap<ItemData, InputPair> itemFields = new HashMap<>();
            itemFields.put(ItemData.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
            itemFields.put(ItemData.DESCRIPTION, new InputPair(new JLabel("Description:"), new JTextField("", 15)));
            itemFields.put(ItemData.QUANTITY, new InputPair(new JLabel("Initial Quantity:"), new JTextField("", 15)));
            JComboBox<String> comboBox = new JComboBox<>(this.suppliers.getSuppliersName());
            itemFields.put(ItemData.SUPPLIER, new InputPair(new JLabel("Supplier:"), comboBox));
            itemFields.put(ItemData.SUPPLIER_PRICE, new InputPair(new JLabel("Supplier Price:"), new JTextField("", 15)));
    
            JButton confirmBtn = new JButton("Add Item");
            confirmBtn.setForeground(new Color(0, 153, 0));
            confirmBtn.addActionListener(e -> {
                try {
                    String name = itemFields.get(ItemData.NAME).getTextFieldString();
                    String description = itemFields.get(ItemData.DESCRIPTION).getTextFieldString();
                    String quantity = itemFields.get(ItemData.QUANTITY).getTextFieldString();
                    String supplier = itemFields.get(ItemData.SUPPLIER).getDropListSelected();
                    String supplierId = this.suppliers.getSupplierIdByName(supplier);
                    String price = itemFields.get(ItemData.SUPPLIER_PRICE).getTextFieldString();
                    HashMap<InputType, String> validatedInputs = InputValidator.getValidateInputs(name, description, quantity, price);
                    int intQuantity = Integer.parseInt(validatedInputs.get(InputType.QUANTITY));
                    this.items.addItem(
                        validatedInputs.get(InputType.NAME),
                        validatedInputs.get(InputType.DESCRIPTION),
                        Integer.parseInt(validatedInputs.get(InputType.QUANTITY)),
                        supplier, supplierId,
                        Float.parseFloat(validatedInputs.get(InputType.PRICE))
                    );
                    this.totalItemsLabel.setText(this.items.getItemsList().size() + " warehouse items in catalog");
                    Item item = this.items.getItemsList().getLast();
                    createItemsPanel(item);
                    frame.dispose();
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            JButton cancelBtn = new JButton("Cancel");
            cancelBtn.setForeground(new Color(255, 153, 0));
            cancelBtn.addActionListener(e -> frame.dispose());
    
            // Add components to the Add Item Panel
            for (InputPair field : itemFields.values()) {
                updatePanel.add(field.getLabel());
                if (field.getLabelString().toUpperCase().equals(ItemData.SUPPLIER.name() + ":")) {
                    updatePanel.add(field.getDropList());
                } else {
                    updatePanel.add(field.getTextField());
                }
            }
            updatePanel.add(new JLabel(""));
            updatePanel.add(new JLabel(""));
            updatePanel.add(new JLabel(""));
            updatePanel.add(confirmBtn);
            updatePanel.add(cancelBtn);      
        } else {
        JLabel noSuppliersLabel = new JLabel("No suppliers available. Please add a supplier first.");
            noSuppliersLabel.setForeground(Color.RED);
            updatePanel.add(noSuppliersLabel);
            JButton cancelBtn = new JButton("Cancel");
            cancelBtn.setForeground(new Color(255, 153, 0));
            cancelBtn.addActionListener(e -> frame.dispose());
            updatePanel.add(cancelBtn);
        }

        frame.add(updatePanel);
        FrameUtils.centerFrame(frame);
    }
}