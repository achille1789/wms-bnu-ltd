package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import backend.*;
import backend.entities.*;
import backend.warehouseitems.Item;
import backend.warehouseitems.ItemData;
import backend.orders.*;
import utils.*;

/**
 * SuppliersPanel is the class that generates the Suppliers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class SuppliersPanel extends EntitiesPanel {

    /**
     * Constructor for the SuppliersPanel.
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param customers The instance of the SupplierManager class.
     * @param region of the UI where display the panel.
     */
     public SuppliersPanel(JPanel mainUIContentPane, SupplierManager suppliers, OrderManager suppliersOrders, InventoryManager items, String region) {
         setInputsPadding(1);
         createEntitiesPanel(mainUIContentPane, suppliers, suppliersOrders, items, region);
     }
    
    /**
    * Get the Entity type to populate the labels.
    * Override abstract method getEntityType.
    *
    * @param plural true if the label is plural, false otherwise.
    * @return the entity type as string.
    */
    @Override
    protected String getEntityType(boolean plural) {
        return plural ? "Suppliers" : "Supplier";
    }
    
    /**
     * Get the side panel for Order Items.
     * Override abstract method getSidePanelOrderItems.
     *
     * @param id of the Entity.
     * @param items the list of Items.
     * @return the list of items that can be ordered.
     */
    @Override
    protected List<Item> getSidePanelOrderItems(String id, InventoryManager items) {
        return items.getSupplierItems(id);
    }
    
    /**
     * Get the item price.
     * Override abstract method getPrice.
     *
     * @param itemDetails an HashMap containing the item price.
     * @return the item price as float.
     */
    @Override
    protected float getPrice(HashMap<ItemData, String> itemDetails) {
        return Float.parseFloat(itemDetails.get(ItemData.SUPPLIER_PRICE));
    }
    
    /**
     * Get the Order status.
     * Override abstract method getOrderStatus.
     *
     * @return the Order status.
     */
    @Override
    protected OrderStatus getOrderStatus() {
        return OrderStatus.PENDING;
    }
    
    /**
     * Get the Entity name.
     * Override abstract method getEntityName.
     *
     * @param entity The Entity to get the name from.
     * @return the name of the Entity.
     */
    @Override
    protected String getEntityName(Entity entity) {
        return entity.getName();
    }
    
    /**
     * Create a panel for an Supplier.
     * Override abstract method EntitiesPanel.
     *
     * @param id of the supplier.
     * @return a HashMap of Data and InputPair.
     */
    @Override
    protected HashMap<Data, InputPair> getEntityFields(String id) {
        HashMap<Data, InputPair> entityFields = new HashMap<>();
        if (id.equals("")) {
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
            entityFields.put(Data.CRN, new InputPair(new JLabel("Company Reg. Num. (8 digits):"), new JTextField("", 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField("", 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField("", 15)));
            entityFields.put(Data.BANK_ACCOUNT, new InputPair(new JLabel("Bank Account (8 digits):"), new JTextField("", 15)));
            entityFields.put(Data.SORT_CODE, new InputPair(new JLabel("Sort Code:"), new JTextField("", 15)));
        } else {
            HashMap<Data, String> entitiesData = getEntities().getEntityData(id);
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField(entitiesData.get(Data.NAME), 15)));
            entityFields.put(Data.CRN, new InputPair(new JLabel("Company Reg. Num. (8 digits):"), new JTextField(entitiesData.get(Data.CRN), 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField(entitiesData.get(Data.EMAIL), 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField(entitiesData.get(Data.ADDRESS), 15)));
            entityFields.put(Data.BANK_ACCOUNT, new InputPair(new JLabel("Bank Account (8 digits):"), new JTextField(entitiesData.get(Data.BANK_ACCOUNT), 15)));
            entityFields.put(Data.SORT_CODE, new InputPair(new JLabel("Sort Code:"), new JTextField(entitiesData.get(Data.SORT_CODE), 15)));
        }
        return entityFields;
    }
    
    /**
     * Update the panel of the selected Entity.
     * Override abstract method EntitiesPanel.
     *
     * @param frame the JFrame of the panel.
     * @param label the JLabel of the panel.
     * @param id the id of the entity.
     * @param entityFields the fields of the entity.
     */
    @Override
    protected void updateEntityPanel(JFrame frame, JLabel label, String id, HashMap<Data, InputPair> entityFields) {
        try {
            HashMap<InputType, String> validatedInputs = InputValidator.getValidateInputs(
                entityFields.get(Data.NAME).getTextFieldString(),
                entityFields.get(Data.CRN).getTextFieldString(),
                entityFields.get(Data.EMAIL).getTextFieldString(),
                entityFields.get(Data.ADDRESS).getTextFieldString(),
                entityFields.get(Data.BANK_ACCOUNT).getTextFieldString(),
                entityFields.get(Data.SORT_CODE).getTextFieldString()
            );
            HashMap<Data, String> newEntityData = new HashMap<>();
            SupplierManager suppliers = (SupplierManager)getEntities();
            newEntityData.put(Data.NAME, validatedInputs.get(InputType.NAME));
            newEntityData.put(Data.CRN, validatedInputs.get(InputType.CRN));
            newEntityData.put(Data.EMAIL, validatedInputs.get(InputType.EMAIL));
            newEntityData.put(Data.ADDRESS, validatedInputs.get(InputType.ADDRESS));
            newEntityData.put(Data.BANK_ACCOUNT, validatedInputs.get(InputType.BANK_ACCOUNT));
            newEntityData.put(Data.SORT_CODE, validatedInputs.get(InputType.SORT_CODE));
            suppliers.updateEntityData(id, newEntityData);
            HashMap<Data, String> updatedSupplierData = suppliers.getEntityData(id);
            label.setText(updatedSupplierData.get(Data.NAME));
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Add the panel of the new Entity.
     * Override abstract method addEntityPanel.
     *
     * @param frame where to display the add entity panel.
     * @param entityFields the data needed by the entity.
     */
    @Override
    protected void addEntityPanel(JFrame frame, HashMap<Data, InputPair> entityFields) {
        try {
            SupplierManager suppliers = (SupplierManager)getEntities();
            String name = entityFields.get(Data.NAME).getTextFieldString();
            String crn = entityFields.get(Data.CRN).getTextFieldString();
            String email = entityFields.get(Data.EMAIL).getTextFieldString();
            String address = entityFields.get(Data.ADDRESS).getTextFieldString();
            String bankAccount = entityFields.get(Data.BANK_ACCOUNT).getTextFieldString();
            String sortCode = entityFields.get(Data.SORT_CODE).getTextFieldString();
            HashMap<InputType, String> validatedInputs = InputValidator.getValidateInputs(name, crn, email, address, bankAccount, sortCode);
            suppliers.addSupplier(
                validatedInputs.get(InputType.NAME),
                validatedInputs.get(InputType.CRN),
                validatedInputs.get(InputType.EMAIL),
                validatedInputs.get(InputType.ADDRESS),
                validatedInputs.get(InputType.BANK_ACCOUNT),
                validatedInputs.get(InputType.SORT_CODE)
            );
            getTotalEntitiesLabel().setText(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + suppliers.getEntitiesList().size());
            Supplier supplier = (Supplier)suppliers.getEntitiesList().getLast();
            setEntitiesPanelDetails(supplier.getId(), supplier.getName());
            getEntitiesPanel().add(Box.createRigidArea(new Dimension(0, 10)));
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Create Entity extra info panel.
     * Override abstract method createSupplierExtraInfoPanel.
     *
     * @param panel the panel where to add the pending deliveries.
     * @param entities the list of Entities.
     * @param items the list of Items.
     * @param orders the list of Orders.
     * @param pendingDeliveryItems the list of pending deliveries.
     */
    @Override
    protected void createSupplierExtraInfoPanel(JPanel panel, EntityManager entities, InventoryManager items, OrderManager orders, HashMap<UIItems, InputPair> pendingDeliveryItems) {
        JPanel linePanel = new JPanel();
        linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.X_AXIS));
        linePanel.setMaximumSize(new Dimension(300, 30));
        linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linePanel.setBackground(Color.DARK_GRAY);
        
        JButton stockLevelBtn = new JButton("Items Stock Level");
        stockLevelBtn.addActionListener(e -> {
            String stockMsg;
            if (items.getItemsList().size() == 0) {
                stockMsg = "Currently there are no items in stock";
            } else {
                stockMsg = "Items Stock:\n";
                for (int i = 0; i < items.getItemsList().size(); i++) {
                    stockMsg += "- " + items.getItemsList().get(i).getName() + " has " + items.getItemsList().get(i).getQuantity() + " items left\n";
                }
            }
            JOptionPane.showMessageDialog(null, stockMsg, "Items Stock Level", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(stockLevelBtn);
        
        int pendingDeliveries = orders.getPendingOrders().size();
        JLabel label = new JLabel(pendingDeliveries + " pending deliveries");
        label.setForeground(Color.WHITE);
        JButton deliveryBtn = new JButton("Receive Deliveries");
        deliveryBtn.setForeground(Color.BLUE);
        if (pendingDeliveries == 0) {
            deliveryBtn.setEnabled(false);
        }
        deliveryBtn.addActionListener(e -> {
            updateStockLevelAfterDelivery();
            orders.setNewOrderStatus(OrderStatus.DELIVERED);
            pendingDeliveryItems.get(UIItems.PENDING_DELIVERY_PANEL).getButton().setEnabled(false);
            pendingDeliveryItems.get(UIItems.PENDING_DELIVERY_PANEL).getLabel().setText(orders.getPendingOrders().size() + " pending deliveries");
        });
        linePanel.add(label);
        linePanel.add(deliveryBtn);
        pendingDeliveryItems.put(UIItems.PENDING_DELIVERY_PANEL, new InputPair(label, deliveryBtn));
        panel.add(linePanel);
    }
    
    /**
     * Add the line panel of the item quantity.
     * Override abstract method getOrderQuantityLinePanel.
     *
     * @param panel where to attach order item line panel.
     * @param itemDetails an HashMap containing the item quantity.
     */
    @Override
    protected void getOrderQuantityLinePanel(JPanel panel, HashMap<ItemData, String> itemDetails, float price) {
        // Create a sub-panel for label + input field
        JPanel linePanel = new JPanel();
        linePanel.setMaximumSize(new Dimension(200, 30));
        linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        FrameUtils.createReverseHighContrastPanel(linePanel, false);
        JLabel label = new JLabel("Quantity to order:");
        JTextField quantityField = new JTextField(10);
        linePanel.add(label);
        linePanel.add(quantityField);
        panel.add(linePanel);
        
        JButton orderButton = new JButton("Order");        
        orderButton.addActionListener(e -> {
            try {
                int quantityAvailable = Integer.parseInt(itemDetails.get(ItemData.QUANTITY));
                HashMap<InputType, String> validatedInputs = InputValidator.getValidateInputs(quantityField.getText());
                int quantity = Integer.parseInt(validatedInputs.get(InputType.QUANTITY));
                OrderItem orderItem = new OrderItem(itemDetails.get(ItemData.NAME), quantity, itemDetails.get(ItemData.ID), quantity * price);
                quantityField.setEnabled(false);
                orderButton.setEnabled(false);
                setBasketItem(orderItem, quantityField, orderButton);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(orderButton);
    }
}