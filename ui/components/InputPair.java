package ui.components;

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
    * getters
    */    
    public JLabel getLabel() {
        return label;
    }    
    public JTextField getTextField() {
        return textField;
    }    
    public String getLabelString() {
        return label.getText();
    }        
    public String getTextFieldString() {
        return textField.getText();
    }
}
