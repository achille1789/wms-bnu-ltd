package src.ui.components;

import javax.swing.*;

/**
 * InputPair is a class to store the pair label and text.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class InputPair {
    private JLabel label;
    private JTextField textField;
    private JComboBox<String> dropList;
    private JButton button;

    /**
     * Constructor to create a new InputPair.
     * 
     * @param label The label of the input.
     * @param textField The text field of the input.
     */
    public InputPair(JLabel label, JTextField textField) {
        this.label = label;
        this.textField = textField;
    }
    
    /**
     * Constructor to create a new InputPair.
     * 
     * @param label The label of the input.
     * @param dropList The droplist of string.
     */
    public InputPair(JLabel label, JComboBox<String> dropList) {
        this.label = label;
        this.dropList = dropList;
    }
    
    /**
     * Constructor to create a new InputPair.
     * 
     * @param label The label of the input.
     * @param button The button to trigger an action.
     */
    public InputPair(JLabel label, JButton button) {
        this.label = label;
        this.button = button;
    }
    
    /**
    * getters
    */    
    public JLabel getLabel() {
        return this.label;
    }    
    public JTextField getTextField() {
        return this.textField;
    }   
    public JComboBox<String> getDropList() {
        return this.dropList;
    }   
    public JButton getButton() {
        return this.button;
    }  
    public String getLabelString() {
        return this.label.getText();
    }        
    public String getTextFieldString() {
        return this.textField.getText();
    }
    public String getDropListSelected() {
        return (String) this.dropList.getSelectedItem();
    }
}
