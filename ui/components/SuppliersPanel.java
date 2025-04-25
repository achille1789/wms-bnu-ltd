package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import backend.SuppliersList;
import backend.entities.*;

/**
 * SuppliersPanel is the class that generates the Suppliers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class SuppliersPanel extends EntitiesPanel {

    /**
     * Constructor for the SuppliersPanel.
     */
     public SuppliersPanel(JPanel mainUIContentPane, SuppliersList suppliers, String region) {
         setInputsPadding(1);
         createEntitiesPanel(mainUIContentPane, suppliers, region);
     }
    
    /**
    * Get the Entity type to populate the labels.
    */
    @Override
    protected String getEntityType(boolean plural) {
        return plural ? "Suppliers" : "Supplier";
    }
    
    /**
     * Create a panel for an Supplier.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected HashMap<Data, InputPair> getEntityFields(String id) {
        HashMap<Data, InputPair> entityFields = new HashMap<>();
        if (id.equals("")) {
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField("", 15)));
            entityFields.put(Data.CRN, new InputPair(new JLabel("Company Reg. Num.:"), new JTextField("", 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField("", 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField("", 15)));
            entityFields.put(Data.BANK_ACCOUNT, new InputPair(new JLabel("Bank Account:"), new JTextField("", 15)));
            entityFields.put(Data.SORT_CODE, new InputPair(new JLabel("Sort Code:"), new JTextField("", 15)));
        } else {
            HashMap<Data, String> entitiesData = getEntities().getEntityData(id);
            entityFields.put(Data.NAME, new InputPair(new JLabel("Name:"), new JTextField(entitiesData.get(Data.NAME), 15)));
            entityFields.put(Data.CRN, new InputPair(new JLabel("Company Reg. Num.:"), new JTextField(entitiesData.get(Data.CRN), 15)));
            entityFields.put(Data.EMAIL, new InputPair(new JLabel("Email:"), new JTextField(entitiesData.get(Data.EMAIL), 15)));
            entityFields.put(Data.ADDRESS, new InputPair(new JLabel("Address:"), new JTextField(entitiesData.get(Data.ADDRESS), 15)));
            entityFields.put(Data.BANK_ACCOUNT, new InputPair(new JLabel("Bank Account:"), new JTextField(entitiesData.get(Data.BANK_ACCOUNT), 15)));
            entityFields.put(Data.SORT_CODE, new InputPair(new JLabel("Sort Code:"), new JTextField(entitiesData.get(Data.SORT_CODE), 15)));
        }
        return entityFields;
    }
    
    /**
     * Update the panel of the selected Entity.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected void updateEntityPanel(JFrame frame, JLabel label, String id, HashMap<Data, InputPair> entityFields) {
        SuppliersList suppliers = (SuppliersList)getEntities();
        suppliers.updateEntityData(id, Data.NAME, entityFields.get(Data.NAME).getTextFieldString());
        suppliers.updateEntityData(id, Data.CRN, entityFields.get(Data.CRN).getTextFieldString());
        suppliers.updateEntityData(id, Data.EMAIL, entityFields.get(Data.EMAIL).getTextFieldString());
        suppliers.updateEntityData(id, Data.ADDRESS, entityFields.get(Data.ADDRESS).getTextFieldString());
        suppliers.updateEntityData(id, Data.BANK_ACCOUNT, entityFields.get(Data.BANK_ACCOUNT).getTextFieldString());
        suppliers.updateEntityData(id, Data.SORT_CODE, entityFields.get(Data.SORT_CODE).getTextFieldString());
        HashMap<Data, String> updatedSupplierData = suppliers.getEntityData(id);
        label.setText(updatedSupplierData.get(Data.NAME));
        frame.dispose();
    }

    /**
     * Add the panel of the new Entity.
     * Override abstract method of EntitiesPanel.
     */
    @Override
    protected void addEntityPanel(JFrame frame, HashMap<Data, InputPair> entityFields) {
        SuppliersList suppliers = (SuppliersList)getEntities();
        String name = entityFields.get(Data.NAME).getTextFieldString();
        String crn = entityFields.get(Data.CRN).getTextFieldString();
        String email = entityFields.get(Data.EMAIL).getTextFieldString();
        String address = entityFields.get(Data.ADDRESS).getTextFieldString();
        String bankAccount = entityFields.get(Data.BANK_ACCOUNT).getTextFieldString();
        String sortCode = entityFields.get(Data.SORT_CODE).getTextFieldString();
        suppliers.addSupplier(name, crn, email, address, bankAccount, sortCode);
        getTotalEntitiesLabel().setText(getLabelsText(Labels.TOTAL_ENTITIES_LABEL) + suppliers.getEntitiesList().size());
        Supplier supplier = (Supplier)suppliers.getEntitiesList().getLast();
        setEntitiesPanelDetails(supplier.getId(), supplier.getName());
        getEntitiesPanel().add(Box.createRigidArea(new Dimension(0, 10)));
        frame.dispose();
    }
}