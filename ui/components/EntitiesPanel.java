package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.*;

import backend.*;
import backend.entities.*;
import backend.items.ItemData;
import backend.orders.*;

/**
 * Enum to define the labels used in the EntitiesPanel.
 */
enum Labels {
    TOTAL_ENTITIES_LABEL,
    UPDATE_ENTITY_FRAME_LABEL,
    ADD_ENTITY_FRAME_LABEL,
    ADD_ENTITY_BTN,
    UPDATE_ENTITY_BTN,
    DELETE_ENTITY_BTN,
    UPDATE_ENTITY_FRAME_BTN,
    ADD_ENTITY_FRAME_BTN,
}

/**
 * Enum to define the actions of the entity panel.
 */
enum Action {
    ADD,
    UPDATE,
}

/**
 * EntitiesPanel is the class that generates the Entities Panel.
 * It is an abstract class that is used to create the Customers and Suppliers panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
abstract class EntitiesPanel {
    // fields
    private EntitiesList entities;
    private JPanel entitiesPanel;
    private JLabel totalEntitiesLabel;
    private int padding = 0;
    private OrdersList orders;
    private ItemsList items;
    private List<OrderItem> basketItems = new LinkedList<>();
    private JLabel basketLabel;
    private JPanel basketPanel;
    
    /**
     * Create the Entities panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param entities The instance of the EntitiesList class.
     * @param region of the UI where display the panel.
     */
    protected void createEntitiesPanel(JPanel mainUIContentPane, EntitiesList entities, OrdersList orders, ItemsList items, String region) {
        this.entities = entities;
        this.orders = orders;
        this.items = items;
        this.entitiesPanel = new JPanel();
        this.entitiesPanel.setBackground(Color.DARK_GRAY);       
        this.entitiesPanel.setLayout(new BoxLayout(this.entitiesPanel, BoxLayout.Y_AXIS));   
        this.entitiesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.totalEntitiesLabel = new JLabel(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + this.entities.getEntitiesList().size());
        this.totalEntitiesLabel.setPreferredSize(new Dimension(160, 50));
        this.totalEntitiesLabel.setForeground(Color.WHITE);
        this.entitiesPanel.add(this.totalEntitiesLabel);
        JButton add = new JButton(getLabelsText(Labels.ADD_ENTITY_BTN));
        add.addActionListener(e -> createEntityFieldsFrame(Action.ADD, null, ""));
        this.entitiesPanel.add(add);
        this.entitiesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.entities.getEntitiesList().size(); i++) {
            Entity entity = this.entities.getEntitiesList().get(i);
            createEntityPanel(entity);
        }
        
        // set vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(this.entitiesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        mainUIContentPane.add(scrollPane, region);
    }
    
    /**
     * Get expected text for each label.
     * 
     * @param label the label name.
     * @return the label text.
     */
    protected String getLabelsText(Labels label) {
        switch (label) {
        case Labels.TOTAL_ENTITIES_LABEL:
            return "Total " + getEntityType(true) + ": ";
        case Labels.UPDATE_ENTITY_FRAME_LABEL:
            return "Update " + getEntityType(false);
        case Labels.ADD_ENTITY_FRAME_LABEL:
            return "Add " + getEntityType(false);
        case Labels.ADD_ENTITY_BTN:
            return "Add " + getEntityType(false);
        case Labels.UPDATE_ENTITY_BTN:
            return "Update " + getEntityType(false);
        case Labels.DELETE_ENTITY_BTN:
            return "Delete " + getEntityType(false);
        case Labels.UPDATE_ENTITY_FRAME_BTN:
            return "Update " + getEntityType(false);
        case Labels.ADD_ENTITY_FRAME_BTN:
            return "Add " + getEntityType(false);
        default:
            return "";
        }
    }
    
    /**
     * Getters.
     */
     protected EntitiesList getEntities() {
        return this.entities;
     }     
     protected JLabel getTotalEntitiesLabel() {
        return this.totalEntitiesLabel;
     }     
     protected JPanel getEntitiesPanel() {
        return this.entitiesPanel;
     }
     
     /**
     * Setters.
     */
     protected void setInputsPadding(int padding) {
        this.padding = padding;
     }
     
     /**
      * Get the Entity type to populate the labels.
      * @param plural true if the label is plural, false otherwise.
      * @return the entity type as string.
      */
     protected String getEntityType(boolean plural) {
         return plural ? "Entities" : "Entity";
     }
    
    /**
     * Set entity details into the panel.
     * 
     * @param id The Entity id.
     * @param name The Entity name and surname chained in one string.
     */
    protected void setEntitiesPanelDetails(String id, String name) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel(name);
        panel.add(labelName);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        JButton order = new JButton("Order");
        order.addActionListener(e -> createOrderFrame(id));
        panel.add(order);
        JButton history = new JButton("Order History");
        history.addActionListener(e -> System.out.println("Button clicked!" + id));
        panel.add(history);
        JButton update = new JButton(getLabelsText(Labels.UPDATE_ENTITY_BTN));
        update.addActionListener(e -> createEntityFieldsFrame(Action.UPDATE,labelName, id));
        panel.add(update);
        JButton delete = new JButton(getLabelsText(Labels.DELETE_ENTITY_BTN));
        delete.addActionListener(e -> deleteEntityPanel(panel, id));
        panel.add(delete);
        
        this.entitiesPanel.add(panel);
    }
    
    /**
     * Create a panel for an Entity.
     */
    private void createEntityPanel(Entity entity) {
        String id = entity.getId();
        String name = getEntityName(entity);
        setEntitiesPanelDetails(id, name);
        this.entitiesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
        
    /**
     * Delete the panel of the selected Entity.
     */
    private void deleteEntityPanel(JPanel panel, String id) {
        this.entities.deleteEntity(id);
        this.entitiesPanel.remove(panel);
        this.entitiesPanel.revalidate();
        this.entitiesPanel.repaint();
        this.totalEntitiesLabel.setText(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + this.entities.getEntitiesList().size());
    }
    
    /**
     * Create a new frame panel to update entity data.
     * 
     * @param action type: update or add.
     * @param label The Label of the Entity panel to update the name showed.
     * @param id The Entity id.
     */
    private void createEntityFieldsFrame(Action action, JLabel label, String id) {
        JFrame frame = new JFrame(getLabelsText(action == Action.UPDATE ? Labels.UPDATE_ENTITY_FRAME_LABEL : Labels.ADD_ENTITY_FRAME_LABEL));
        frame.setSize(700, 200);

        // Create a panel and use GridLayout for label + field pairs
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 cols, spacing
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input components
        HashMap<Data, InputPair> entityFields = getEntityFields(id);

        JButton confirmBtn = new JButton(getLabelsText(action == Action.UPDATE ? Labels.UPDATE_ENTITY_FRAME_BTN : Labels.ADD_ENTITY_FRAME_BTN));
        confirmBtn.setForeground(new Color(0, 153, 0));
        confirmBtn.addActionListener(e -> {
            if (label == null) {
                addEntityPanel(frame, entityFields);
            } else {
                updateEntityPanel(frame, label, id, entityFields);
            }
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setForeground(new Color(255, 153, 0));
        cancelBtn.addActionListener(e -> frame.dispose());

        // Add components to the updatePanel
        for (InputPair field : entityFields.values()) {
            updatePanel.add(field.getLabel()); // JLabel
            updatePanel.add(field.getTextField()); // JTextField
        }
        for (int i = 0; i < this.padding; i++) {
            updatePanel.add(new JLabel("")); // Empty labels for padding
        }
        updatePanel.add(confirmBtn);
        updatePanel.add(cancelBtn);

        // Add updatePanel to frame
        frame.add(updatePanel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create a new frame panel to submit an order.
     * 
     * @param action type: update or add.
     * @param label The Label of the Entity panel to update the name showed.
     * @param id The Entity id.
     */
    private void createOrderFrame(String entityId) {
        JFrame frame = new JFrame("Create Order");
        frame.setSize(600, 500);
        this.basketItems.clear();

        createOrderItemsSidePanel(frame, entityId);
        createBasketSidePanel(frame, entityId);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create Order Items left panel.
     * 
     * @param frame the order frame.
     */
    private void createOrderItemsSidePanel(JFrame frame, String entityId) {
        JPanel orderPanel = new JPanel();
        orderPanel.setBackground(Color.DARK_GRAY);       
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));   
        orderPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel totalItemsLabel = new JLabel("Catalogue has " + this.items.getItemsList().size() + " items");
        totalItemsLabel.setForeground(Color.WHITE);
        orderPanel.add(totalItemsLabel);
        orderPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (int i = 0; i < this.items.getItemsList().size(); i++) {
            HashMap<ItemData, String> itemData = this.items.getItemsList().get(i).getAllData();
            setOrderItemsPanelDetails(orderPanel, itemData, entityId);
        }
        
        JButton closeBtn = new JButton("Close");
        closeBtn.setForeground(new Color(255, 153, 0));
        closeBtn.addActionListener(e -> frame.dispose());
        orderPanel.add(closeBtn);
        
        // set vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(orderPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add orderPanel to frame
        frame.add(scrollPane, BorderLayout.WEST);
    }
    
    /**
     * Set items details into the panel.
     * 
     * @param itemPanel the scrollable panel.
     * @param itemDetails the details of the item.
     */
    private void setOrderItemsPanelDetails(JPanel itemPanel, HashMap<ItemData, String> itemDetails, String entityId) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        Entity entity = this.entities.getEntityById(entityId);
        String price = entity instanceof Customer ? itemDetails.get(ItemData.CUSTOMER_PRICE) : itemDetails.get(ItemData.SUPPLIER_PRICE);
        
        String itemInfo = "<html>Name: " + itemDetails.get(ItemData.NAME) + "<br>Description: " + itemDetails.get(ItemData.DESCRIPTION);              
        itemInfo += "<br>Price: £" + price + "<br>Items available: " + itemDetails.get(ItemData.QUANTITY) + "</html>";
        JLabel labelInfo = new JLabel(itemInfo);
        panel.add(labelInfo);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        if (Integer.parseInt(itemDetails.get(ItemData.QUANTITY)) > 0 || entity instanceof Supplier) {
            // Create a sub-panel for label + input field
            JPanel linePanel = new JPanel();
            linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.X_AXIS)); // left-aligned
            linePanel.setMaximumSize(new Dimension(200, 30)); // fix height of the line
            linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            linePanel.setBackground(Color.LIGHT_GRAY); 
            JLabel label = new JLabel("Quantity to order:");
            JTextField quantityField = new JTextField(10); // 10 columns width
            linePanel.add(label);
            linePanel.add(quantityField);
            panel.add(linePanel);
            
            JButton orderButton = new JButton("Order");        
            orderButton.addActionListener(e -> {
                int quantityAvailable = Integer.parseInt(itemDetails.get(ItemData.QUANTITY));
                int quantity = Integer.parseInt(quantityField.getText()) > quantityAvailable && entity instanceof Customer ? quantityAvailable : Integer.parseInt(quantityField.getText());
                OrderItem orderItem = new OrderItem(itemDetails.get(ItemData.NAME), quantity, itemDetails.get(ItemData.ID), quantity * Float.parseFloat(price));
                quantityField.setEnabled(false);
                orderButton.setEnabled(false);
                setBasketItem(orderItem, quantityField, orderButton);
            });
            panel.add(orderButton);
        } else {           
            JLabel label = new JLabel("Item not available");
            label.setForeground(Color.RED);
            panel.add(label);
        }
        
        itemPanel.add(panel);
        itemPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    /**
     * Create Order Items left panel.
     * 
     * @param frame the order frame.
     * @param entityId the id of the Entity that created the order.
     */
    private void createBasketSidePanel(JFrame frame, String entityId) {
        this.basketPanel = new JPanel();
        this.basketPanel.setBackground(Color.DARK_GRAY);       
        this.basketPanel.setLayout(new BoxLayout(this.basketPanel, BoxLayout.Y_AXIS));   
        this.basketPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.basketLabel = new JLabel("Items in basket: " + this.basketItems.size() + " - Total Cost: £" + getTotalPurchaseCost());
        this.basketLabel.setForeground(Color.WHITE);
        this.basketPanel.add(this.basketLabel);
        this.basketPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        Entity entity = this.entities.getEntityById(entityId);
        
        JButton purchaseBtn = new JButton("Purchase");
        purchaseBtn.setForeground(new Color(255, 153, 0));
        purchaseBtn.addActionListener(e -> {
            this.orders.addOrder(entityId, getTotalPurchaseCost(), this.basketItems, entity instanceof Customer ? OrderStatus.SHIPPED : OrderStatus.ORDERED);
            if (entity instanceof Customer) {
                this.updateStockLevelAfterPurchase();
            }
            frame.dispose();
            this.basketItems.clear();
        });
        this.basketPanel.add(purchaseBtn);
        
        // set vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(this.basketPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add basketPanel to frame
        frame.add(scrollPane, BorderLayout.EAST);
    }
    
    /**
     * Set basket item into the panel.
     * 
     * @param itemPanel the scrollable panel.
     * @param itemDetails the details of the item.
     */
    private void setBasketItem(OrderItem orderItem, JTextField quantityField, JButton orderButton) {
        this.basketItems.add(orderItem);
        HashMap<OrderItemData, String> orderItemData = orderItem.getAllData();
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        this.basketLabel.setText("Items in basket: " + this.basketItems.size() + " - Total Cost: £" + getTotalPurchaseCost());
        
        String itemInfo = "<html>Name: " + orderItemData.get(OrderItemData.NAME) + "<br>Quantity: " + orderItemData.get(OrderItemData.QUANTITY);              
        itemInfo += "<br>Total price: £" + orderItemData.get(OrderItemData.COST) + "</html>";
        JLabel labelInfo = new JLabel(itemInfo);
        panel.add(labelInfo);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            quantityField.setEnabled(true);
            orderButton.setEnabled(true);
            deleteBasketItem(orderItemData.get(OrderItemData.NAME));
            this.basketPanel.remove(panel);
            this.basketPanel.revalidate();
            this.basketPanel.repaint();
            this.basketLabel.setText("Items in basket: " + this.basketItems.size() + " - Total Cost: £" + getTotalPurchaseCost());
        });
        panel.add(removeButton);
        
        this.basketPanel.add(panel);
        this.basketPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    /**
     * Delete item from basket.
     * @param itemId find and delete the item with the passed it.
     */
    private void deleteBasketItem(String itemName) {
        for (int i = 0; i < this.basketItems.size(); i++) {
            if (this.basketItems.get(i).getName().equals(itemName)) {
                this.basketItems.remove(i);
                break;
            }
        }
    }
    
    /**
     * Calculate total purchase cost.
     * @return the total cost of the purchase.
     */
    private float getTotalPurchaseCost() {
        float totalCost = 0;
        for (int i = 0; i < this.basketItems.size(); i++) {
            totalCost += this.basketItems.get(i).getCost();
        }
        return totalCost;
    }
    
    /**
     * Update stock level after purchase.
     */
    private void updateStockLevelAfterPurchase() {
        for (int i = 0; i < this.basketItems.size(); i++) {
            String basketItemId = this.basketItems.get(i).getItemId();
            int newQuantity = this.items.getItemQuantity(basketItemId) - this.basketItems.get(i).getQuantity();
            this.items.updateItemQuantity(basketItemId, newQuantity);
        }
    }
    
    /**
     * Get the Entity name.
     * @param entity The Entity to get the name from.
     * @return the name of the Entity.
     */
    private String getEntityName(Entity entity) {
        if (entity instanceof Customer) {
            Customer customer = (Customer) entity;
            return customer.getName() + " " + customer.getSurname();
        }
        return entity.getName();
    }
    
    /**
     * Get the Entity fields.
     * @param id of the Entity.
     * @return the fields of the Entity.
     */
    protected abstract HashMap<Data, InputPair> getEntityFields(String id);
    
    /**
     * Update the panel of the selected Entity.
     * @param frame where to display the update entity panel.
     * @param label to give to the update panel.
     * @param id of the Entity.
     * @param entityFields the data of the entity.
     */
    protected abstract void updateEntityPanel(JFrame frame, JLabel label, String id, HashMap<Data, InputPair> entityFields);

    /**
     * Add the panel of the new Entity.
     * @param frame where to display the add entity panel.
     * @param entityFields the data needed by the entity.
     */
    protected abstract void addEntityPanel(JFrame frame, HashMap<Data, InputPair> entityFields);
}