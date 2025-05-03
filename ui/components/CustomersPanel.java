package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;

import backend.*;
import backend.warehouseitems.Item;
import backend.warehouseitems.ItemData;
import backend.entities.*;
import backend.orders.*;
import utils.*;

/**
 * CustomersPanel is the class that generates the Customers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomersPanel extends EntitiesPanel {

    /**
     * Constructor for the CustomersPanel.
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param customers The instance of the CustomerManager class.
     * @param region of the UI where display the panel.
     */
     public CustomersPanel(JPanel mainUIContentPane, CustomerManager customers, OrderManager customersOrders, InventoryManager items, String region) {
         setInputsPadding(3);
         createEntitiesPanel(mainUIContentPane, customers, customersOrders, items, region);
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
        return plural ? "Customers" : "Customer";
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
        return items.getItemsList();
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
        return Float.parseFloat(itemDetails.get(ItemData.CUSTOMER_PRICE));
    }
    
    /**
     * Get the Order status.
     * Override abstract method getOrderStatus.
     *
     * @return the Order status.
     */
    protected OrderStatus getOrderStatus() {
        return OrderStatus.SHIPPED;
    }
    
    /**
     * Get the Entity name.
     * Override abstract method getEntityName.
     *
     * @param entity The Entity to get the name from.
     * @return the name of the Entity.
     */
    protected String getEntityName(Entity entity) {
        Customer customer = (Customer) entity;
        return customer.getName() + " " + customer.getSurname();
    }
    
    /**
     * Create a panel for an Customer.
     * Override abstract method EntitiesPanel.
     *
     * @param id of the customer.
     * @return a HashMap of Data and InputPair.
     */
    @Override
    protected HashMap<Data, InputPair> getEntityFields(String id) {
        HashMap<Data, InputPair> entityFields = new HashMap<>();
        if (id.equals("")) {
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
            entityFields.put(Data.SURNAME, new InputPair(new JLabel("Surname:"), new JTextField("", 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField("", 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField("", 15)));
            entityFields.put(Data.CREDIT_CARD, new InputPair(new JLabel("Credit Card (16 digits):"), new JTextField("", 15)));
        } else {
            HashMap<Data, String> entitiesData = getEntities().getEntityData(id);
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField(entitiesData.get(Data.NAME), 15)));
            entityFields.put(Data.SURNAME, new InputPair(new JLabel("Surname:"), new JTextField(entitiesData.get(Data.SURNAME), 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField(entitiesData.get(Data.EMAIL), 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField(entitiesData.get(Data.ADDRESS), 15)));
            entityFields.put(Data.CREDIT_CARD, new InputPair(new JLabel("Credit Card (16 digits):"), new JTextField(entitiesData.get(Data.CREDIT_CARD), 15)));
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
                entityFields.get(Data.SURNAME).getTextFieldString(),
                entityFields.get(Data.EMAIL).getTextFieldString(),
                entityFields.get(Data.ADDRESS).getTextFieldString(),
                entityFields.get(Data.CREDIT_CARD).getTextFieldString()
            );
            HashMap<Data, String> newEntityData = new HashMap<>();
            CustomerManager customers = (CustomerManager)getEntities();
            newEntityData.put(Data.NAME, validatedInputs.get(InputType.NAME));
            newEntityData.put(Data.SURNAME, validatedInputs.get(InputType.SURNAME));
            newEntityData.put(Data.EMAIL, validatedInputs.get(InputType.EMAIL));
            newEntityData.put(Data.ADDRESS, validatedInputs.get(InputType.ADDRESS));
            newEntityData.put(Data.CREDIT_CARD, validatedInputs.get(InputType.CREDIT_CARD));
            customers.updateEntityData(id, newEntityData);
            HashMap<Data, String> updatedCustomerData = customers.getEntityData(id);
            label.setText(updatedCustomerData.get(Data.NAME) + " " + updatedCustomerData.get(Data.SURNAME));
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            CustomerManager customers = (CustomerManager)getEntities();
            String name = entityFields.get(Data.NAME).getTextFieldString();
            String surname = entityFields.get(Data.SURNAME).getTextFieldString();
            String email = entityFields.get(Data.EMAIL).getTextFieldString();
            String address = entityFields.get(Data.ADDRESS).getTextFieldString();
            String creditCard = entityFields.get(Data.CREDIT_CARD).getTextFieldString();
            HashMap<InputType, String> validatedInputs = InputValidator.getValidateInputs(name, surname, email, address, creditCard);
            customers.addCustomer(validatedInputs.get(InputType.NAME), validatedInputs.get(InputType.SURNAME), validatedInputs.get(InputType.EMAIL), validatedInputs.get(InputType.ADDRESS), validatedInputs.get(InputType.CREDIT_CARD));
            getTotalEntitiesLabel().setText(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + customers.getEntitiesList().size());
            Customer customer = (Customer)customers.getEntitiesList().getLast();
            String id = customer.getId();
            String fullName = customer.getName() + " " + customer.getSurname();
            setEntitiesPanelDetails(id, fullName);
            getEntitiesPanel().add(Box.createRigidArea(new Dimension(0, 10)));
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        // Customer Panel does not have extra info panel
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
        if (Integer.parseInt(itemDetails.get(ItemData.QUANTITY)) > 0) {
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
                    int quantityOrdered = Integer.parseInt(validatedInputs.get(InputType.QUANTITY));
                    int quantity = quantityOrdered > quantityAvailable ? quantityAvailable : quantityOrdered;
                    OrderItem orderItem = new OrderItem(itemDetails.get(ItemData.NAME), quantity, itemDetails.get(ItemData.ID), quantity * price);
                    quantityField.setEnabled(false);
                    orderButton.setEnabled(false);
                    setBasketItem(orderItem, quantityField, orderButton);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            panel.add(orderButton);
        } else {           
            JLabel label = new JLabel("Item not available");
            label.setForeground(Color.RED);
            panel.add(label);
        }
    }
}