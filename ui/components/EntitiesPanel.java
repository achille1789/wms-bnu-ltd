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
import backend.items.Item;
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
 * Enum to define some UI items.
 */
enum UIItems {
    PENDING_DELIVERY_PANEL,
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
    private HashMap<UIItems, InputPair> pendingDeliveryPanel = new HashMap<>();
    
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
        createSupplierExtraInfoPanel(this.entitiesPanel, entities);
        this.entitiesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.entities.getEntitiesList().size(); i++) {
            Entity entity = this.entities.getEntitiesList().get(i);
            createEntityPanel(entity);
        }
        
        JScrollPane scrollPane = FrameUtils.createVerticalScrollablePane(this.entitiesPanel);
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
        history.addActionListener(e -> createEntityOrdersHistoryFrame(id));
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
     * Create Pending Deliveries panel.
     *
     * @param panel the panel where to add the pending deliveries.
     * @param entities the list of Entities.
     */
    protected void createSupplierExtraInfoPanel(JPanel panel, EntitiesList entities) {
        if (entities instanceof SuppliersList) {
            JPanel linePanel = new JPanel();
            linePanel.setLayout(new BoxLayout(linePanel, BoxLayout.X_AXIS)); // left-aligned
            linePanel.setMaximumSize(new Dimension(300, 30)); // fix height of the line
            linePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            linePanel.setBackground(Color.DARK_GRAY);
            
            JButton stockLevelBtn = new JButton("Items Stock Level");
            stockLevelBtn.addActionListener(e -> {
                String stockMsg;
                if (this.items.getItemsList().size() == 0) {
                    stockMsg = "Currently there are no items in stock";
                } else {
                    stockMsg = "Items Stock:\n";
                    for (int i = 0; i < this.items.getItemsList().size(); i++) {
                        stockMsg += "- " + this.items.getItemsList().get(i).getName() + " has " + this.items.getItemsList().get(i).getQuantity() + " items left\n";
                    }
                }
                JOptionPane.showMessageDialog(null, stockMsg, "Items Stock Level", JOptionPane.INFORMATION_MESSAGE);
            });
            panel.add(stockLevelBtn);
            
            int pendingDeliveries = this.orders.getPendingOrders().size();
            JLabel label = new JLabel(pendingDeliveries + " pending deliveries");
            label.setForeground(Color.WHITE);
            JButton deliveryBtn = new JButton("Receive Deliveries");
            deliveryBtn.setForeground(Color.BLUE);
            if (pendingDeliveries == 0) {
                deliveryBtn.setEnabled(false);
            }
            deliveryBtn.addActionListener(e -> {
                updateStockLevelAfterDelivery();
                this.orders.setNewOrderStatus(OrderStatus.DELIVERED);
                this.pendingDeliveryPanel.get(UIItems.PENDING_DELIVERY_PANEL).getButton().setEnabled(false);
                this.pendingDeliveryPanel.get(UIItems.PENDING_DELIVERY_PANEL).getLabel().setText(this.orders.getPendingOrders().size() + " pending deliveries");
            });
            linePanel.add(label);
            linePanel.add(deliveryBtn);
            this.pendingDeliveryPanel.put(UIItems.PENDING_DELIVERY_PANEL, new InputPair(label, deliveryBtn));
            panel.add(linePanel);
        }
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

        FrameUtils.centerFrame(frame);
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
        
        FrameUtils.centerFrame(frame);
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

        Entity entity = this.entities.getEntityById(entityId);
        List <Item> itemsList = entity instanceof Customer ? this.items.getItemsList() : this.items.getSupplierItems(entityId);
        for (int i = 0; i < itemsList.size(); i++) {
            HashMap<ItemData, String> itemData = itemsList.get(i).getAllData();
            setOrderItemsPanelDetails(orderPanel, itemData, entityId);
        }
        
        JButton closeBtn = new JButton("Close");
        closeBtn.setForeground(new Color(255, 153, 0));
        closeBtn.addActionListener(e -> frame.dispose());
        orderPanel.add(closeBtn);
        
        JScrollPane scrollPane = FrameUtils.createVerticalScrollablePane(orderPanel);
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
            if (entity instanceof Customer) {
                this.orders.addOrder(entityId, getTotalPurchaseCost(), new LinkedList<>(this.basketItems), OrderStatus.SHIPPED);
                this.updateStockLevelAfterPurchase();
            } else {
                this.orders.addOrder(entityId, getTotalPurchaseCost(), new LinkedList<>(this.basketItems), OrderStatus.PENDING);
                this.pendingDeliveryPanel.get(UIItems.PENDING_DELIVERY_PANEL).getButton().setEnabled(true);
                this.pendingDeliveryPanel.get(UIItems.PENDING_DELIVERY_PANEL).getLabel().setText(this.orders.getPendingOrders().size() + " pending deliveries");
            }
            frame.dispose();
            this.basketItems.clear();
        });
        this.basketPanel.add(purchaseBtn);
        
        JScrollPane scrollPane = FrameUtils.createVerticalScrollablePane(this.basketPanel);
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
     * Create Entity orders history frame.
     *
     * @param entityId the id of the Entity that created the order.
     */
     private void createEntityOrdersHistoryFrame(String entityId) {
        JFrame frame = new JFrame("Orders History");
        frame.setSize(600, 500);
        JPanel orderHistoryPanel = new JPanel();
        orderHistoryPanel.setBackground(Color.DARK_GRAY);       
        orderHistoryPanel.setLayout(new BoxLayout(orderHistoryPanel, BoxLayout.Y_AXIS));   
        orderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel noOrdersLabel = new JLabel("No orders found for this " + getEntityType(false));
        noOrdersLabel.setForeground(Color.WHITE);
        orderHistoryPanel.add(noOrdersLabel);
        orderHistoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> frame.dispose());
        orderHistoryPanel.add(closeBtn);
        
        List <Order> ordersList = this.orders.getEntityOrders(entityId);
        if (ordersList.size() > 0) {
            noOrdersLabel.setText(getEntityType(false) + " has " + ordersList.size() + " orders");
            for (int i = 0; i < ordersList.size(); i++) {
                JPanel itemPanel = new JPanel();
                itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS)); // left-aligned
                itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                itemPanel.setBackground(Color.LIGHT_GRAY); 
                Order order = ordersList.get(i);
                HashMap<OrderData, String> orderData = order.getAllData();
                String orderInfo = "<html>Date: " + orderData.get(OrderData.DATE) + "<br>Id: " + orderData.get(OrderData.ORDER_ID) + "<br>Status: " + orderData.get(OrderData.STATUS);
                orderInfo += "<br>Items:<br>" + orderData.get(OrderData.ORDER_ITEMS).replace("}{", "<br>- ").replace("{", "- ").replace("}", "") + "<br>Order Cost: £" + orderData.get(OrderData.TOTAL_COST) + "</html>";
                JLabel labelInfo = new JLabel(orderInfo);
                itemPanel.add(labelInfo);
                orderHistoryPanel.add(itemPanel);
                orderHistoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        
        JScrollPane scrollPane = FrameUtils.createVerticalScrollablePane(orderHistoryPanel);        
        frame.add(scrollPane, BorderLayout.CENTER);
        FrameUtils.centerFrame(frame);
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
        List<Item> itemsLowStock = this.items.getItemsLowStock();
        if (itemsLowStock.size() > 0) {
            String alertMessage = "Low Stock Items:\n";
            for (int i = 0; i < itemsLowStock.size(); i++) {
                alertMessage += "- " + itemsLowStock.get(i).getName() + " has " + itemsLowStock.get(i).getQuantity() + " items left\n";
            }
            JOptionPane.showMessageDialog(null, alertMessage, "Stock Alert", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update stock level after delivery.
     */
    private void updateStockLevelAfterDelivery() {
        List <Order> pendingOrders = this.orders.getPendingOrders();
        for (int i = 0; i < pendingOrders.size(); i++) {
            List<OrderItem> orderItems = pendingOrders.get(i).getOrderItems();
            for (int j = 0; j < orderItems.size(); j++) {
                String basketItemId = orderItems.get(j).getItemId();
                int newQuantity = this.items.getItemQuantity(basketItemId) + orderItems.get(j).getQuantity();
                this.items.updateItemQuantity(basketItemId, newQuantity);
            }
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