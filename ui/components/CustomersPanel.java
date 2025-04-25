package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import backend.CustomersList;
import backend.entities.*;

/**
 * CustomersPanel is the class that generates the Customers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomersPanel extends EntitiesPanel {

    /**
     * Constructor for the CustomersPanel.
     */
     public CustomersPanel(JPanel mainUIContentPane, CustomersList customers) {
         createEntitiesPanel(mainUIContentPane, customers);
     }
    
    /**
    * Get the Entity type to populate the labels.
    */
    @Override
    protected String getEntityType(boolean plural) {
        return plural ? "Customers" : "Customer";
    }
    
    /**
     * Create a panel for an Customer.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected HashMap<Data, InputPair> getEntityFields(String id) {
        HashMap<Data, InputPair> entityFields = new HashMap<>();
        if (id.equals("")) {
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
            entityFields.put(Data.SURNAME, new InputPair(new JLabel("Surname:"), new JTextField("", 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField("", 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField("", 15)));
            entityFields.put(Data.CREDIT_CARD, new InputPair(new JLabel("Credit Card:"), new JTextField("", 15)));
        } else {
            HashMap<Data, String> entitiesData = getEntities().getEntityData(id);
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField(entitiesData.get(Data.NAME), 15)));
            entityFields.put(Data.SURNAME, new InputPair(new JLabel("Surname:"), new JTextField(entitiesData.get(Data.SURNAME), 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField(entitiesData.get(Data.EMAIL), 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField(entitiesData.get(Data.ADDRESS), 15)));
            entityFields.put(Data.CREDIT_CARD, new InputPair(new JLabel("Credit Card:"), new JTextField(entitiesData.get(Data.CREDIT_CARD), 15)));
        }
        return entityFields;
    }
    
    /**
     * Update the panel of the selected Entity.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected void updateEntityPanel(JFrame frame, JLabel label, String id, HashMap<Data, InputPair> entityFields) {
        CustomersList customers = (CustomersList)getEntities();
        customers.updateEntityData(id, Data.NAME, entityFields.get(Data.NAME).getTextFieldString());
        customers.updateEntityData(id, Data.SURNAME, entityFields.get(Data.SURNAME).getTextFieldString());
        customers.updateEntityData(id, Data.EMAIL, entityFields.get(Data.EMAIL).getTextFieldString());
        customers.updateEntityData(id, Data.ADDRESS, entityFields.get(Data.ADDRESS).getTextFieldString());
        customers.updateEntityData(id, Data.CREDIT_CARD, entityFields.get(Data.CREDIT_CARD).getTextFieldString());
        HashMap<Data, String> updatedCustomerData = customers.getEntityData(id);
        label.setText(updatedCustomerData.get(Data.NAME) + " " + updatedCustomerData.get(Data.SURNAME));
        frame.dispose();
    }

    /**
     * Add the panel of the new Entity.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected void addEntityPanel(JFrame frame, HashMap<Data, InputPair> entityFields) {
        CustomersList customers = (CustomersList)getEntities();
        String name = entityFields.get(Data.NAME).getTextFieldString();
        String surname = entityFields.get(Data.SURNAME).getTextFieldString();
        String email = entityFields.get(Data.EMAIL).getTextFieldString();
        String address = entityFields.get(Data.ADDRESS).getTextFieldString();
        String creditCard = entityFields.get(Data.CREDIT_CARD).getTextFieldString();
        customers.addCustomer(name, surname, email, address, creditCard);
        getTotalEntitiesLabel().setText(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + customers.getEntitiesList().size());
        Customer customer = (Customer)customers.getEntitiesList().getLast();
        String id = customer.getId();
        String fullName = customer.getName() + " " + customer.getSurname();
        setEntitiesPanelDetails(id, fullName);
        getEntitiesPanel().add(Box.createRigidArea(new Dimension(0, 10)));
        frame.dispose();
    }
}