package src.ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.awt.event.*;

import src.backend.*;
import src.backend.entities.*;
import src.backend.warehouseitems.ItemData;
import src.backend.warehouseitems.Item;
import src.backend.orders.*;

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
    private EntityManager entities;
    private JPanel entitiesPanel;
    private JLabel totalEntitiesLabel;
    private int padding = 0;
    private OrderManager orders;
    private InventoryManager items;
    private List<OrderItem> basketItems = new LinkedList<>();
    private JLabel basketLabel;
    private JPanel basketPanel;
    private HashMap<UIItems, InputPair> pendingDeliveryItems = new HashMap<>();
    
    /**
     * Create the Entities panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param entities The instance of the EntityManager class.
     * @param region of the UI where display the panel.
     */
    protected void createEntitiesPanel(JPanel mainUIContentPane, EntityManager entities, OrderManager orders, InventoryManager items, String region) {
        this.entities = entities;
        this.orders = orders;
        this.items = items;
        this.entitiesPanel = new JPanel();
        this.totalEntitiesLabel = new JLabel(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + this.entities.getEntitiesList().size());
        this.totalEntitiesLabel.setPreferredSize(new Dimension(160, 50));
        FrameUtils.createHighContrastPanel(this.entitiesPanel, this.totalEntitiesLabel);
        JButton add = new JButton(getLabelsText(Labels.ADD_ENTITY_BTN));
        add.addActionListener(e -> createEntityFieldsFrame(Action.ADD, null, ""));
        this.entitiesPanel.add(add);
        createSupplierExtraInfoPanel(this.entitiesPanel, entities, this.items, this.orders, this.pendingDeliveryItems);
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
     protected EntityManager getEntities() {
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
        FrameUtils.createReverseHighContrastPanel(panel, true);
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
     * Create a new frame panel to update entity data.
     * 
     * @param action type: update or add.
     * @param label The Label of the Entity panel to update the name showed.
     * @param id The Entity id.
     */
    private void createEntityFieldsFrame(Action action, JLabel label, String id) {
        JFrame frame = new JFrame(getLabelsText(action == Action.UPDATE ? Labels.UPDATE_ENTITY_FRAME_LABEL : Labels.ADD_ENTITY_FRAME_LABEL));
        frame.setSize(700, 200);

        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
     * @param entityId the id of the Entity that created the order.
     */
    private void createOrderItemsSidePanel(JFrame frame, String entityId) {
        JPanel orderPanel = new JPanel();
        JLabel totalItemsLabel = new JLabel("Catalogue has " + this.items.getItemsList().size() + " items");
        FrameUtils.createHighContrastPanel(orderPanel, totalItemsLabel);
        orderPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        Entity entity = this.entities.getEntityById(entityId);
        List<Item> itemsList = getSidePanelOrderItems(entityId, this.items);
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
        FrameUtils.createReverseHighContrastPanel(panel, true);
        
        Entity entity = this.entities.getEntityById(entityId);        
        float price = getPrice(itemDetails);
        String itemInfo = "<html>Name: " + itemDetails.get(ItemData.NAME) + "<br>Description: " + itemDetails.get(ItemData.DESCRIPTION);              
        itemInfo += "<br>Price: £" + price + "<br>Items available: " + itemDetails.get(ItemData.QUANTITY) + "</html>";
        JLabel labelInfo = new JLabel(itemInfo);
        panel.add(labelInfo);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        getOrderQuantityLinePanel(panel, itemDetails, price);
        
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
        this.basketLabel = new JLabel("Items in basket: " + this.basketItems.size() + " - Total Cost: £" + getTotalPurchaseCost());
        FrameUtils.createHighContrastPanel(this.basketPanel, this.basketLabel);
        this.basketPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        Entity entity = this.entities.getEntityById(entityId);
        
        JButton purchaseBtn = new JButton("Purchase");
        purchaseBtn.setForeground(new Color(255, 153, 0));
        purchaseBtn.addActionListener(e -> {
            OrderStatus status = getOrderStatus();
            if (this.basketItems.size() == 0) return;
            this.orders.addOrder(entityId, getTotalPurchaseCost(), new LinkedList<>(this.basketItems), status);
            if (status == OrderStatus.SHIPPED) {
                this.updateStockLevelAfterPurchase();
            } else {
                this.pendingDeliveryItems.get(UIItems.PENDING_DELIVERY_PANEL).getButton().setEnabled(true);
                this.pendingDeliveryItems.get(UIItems.PENDING_DELIVERY_PANEL).getLabel().setText(this.orders.getPendingOrders().size() + " pending deliveries");
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
    protected void setBasketItem(OrderItem orderItem, JTextField quantityField, JButton orderButton) {
        this.basketItems.add(orderItem);
        HashMap<OrderItemData, String> orderItemData = orderItem.getAllData();
        
        JPanel panel = new JPanel();
        FrameUtils.createReverseHighContrastPanel(panel, true);
        
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
        JLabel noOrdersLabel = new JLabel("No orders found for this " + getEntityType(false));
        FrameUtils.createHighContrastPanel(orderHistoryPanel, noOrdersLabel);
        orderHistoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> frame.dispose());
        orderHistoryPanel.add(closeBtn);
        
        List <Order> ordersList = this.orders.getEntityOrders(entityId);
        if (ordersList.size() > 0) {
            noOrdersLabel.setText(getEntityType(false) + " has " + ordersList.size() + " orders");
            for (int i = 0; i < ordersList.size(); i++) {
                JPanel itemPanel = new JPanel();
                itemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                FrameUtils.createReverseHighContrastPanel(itemPanel, false);
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
    protected void updateStockLevelAfterDelivery() {
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
    protected abstract String getEntityName(Entity entity);
    
    /**
     * Create Entity extra info panel.
     *
     * @param panel the panel where to add the pending deliveries.
     * @param entities the list of Entities.
     * @param items the list of Items.
     * @param orders the list of Orders.
     * @param pendingDeliveryItems the list of pending deliveries.
     */
    protected abstract void createSupplierExtraInfoPanel(JPanel panel, EntityManager entities, InventoryManager items, OrderManager orders, HashMap<UIItems, InputPair> pendingDeliveryItems);
    
    /**
     * Get the Entity fields.
     * @param id of the Entity.
     * @return the fields of the Entity.
     */
    protected abstract HashMap<Data, InputPair> getEntityFields(String id);
    
    /**
     * Get the item price.
     * @param itemDetails an HashMap containing the item price.
     * @return the item price as float.
     */
    protected abstract float getPrice(HashMap<ItemData, String> itemDetails);
    
    /**
     * Get the side panel for Order Items.
     * @param id of the Entity.
     * @param items the list of Items.
     * @return the list of items that can be ordered.
     */
    protected abstract List<Item> getSidePanelOrderItems(String id, InventoryManager items);
    
    /**
     * Get the Order status.
     * @return the Order status.
     */
    protected abstract OrderStatus getOrderStatus();
    
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
    
    /**
     * Add the line panel of the item quantity.
     * @param panel where to attach order item line panel.
     * @param itemDetails an HashMap containing the item quantity.
     * @param price the price of the item.
     */
    protected abstract void getOrderQuantityLinePanel(JPanel panel, HashMap<ItemData, String> itemDetails, float price);
}