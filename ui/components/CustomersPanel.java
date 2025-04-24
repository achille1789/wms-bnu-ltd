package ui.components;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;

import backend.*;
import backend.entities.Customer;
import backend.entities.Data;

/**
 * CustomersPanel is the class that generates the Customers Panel.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class CustomersPanel {
    // fields
    private CustomersList customers;
    private JPanel customersPanel;
    private JLabel totalCustomersLabel;
    
    /**
     * Create the Customers panel.
     * 
     * @param mainUIContentPane The contentPane that is created in MainUI.
     * @param customers The instance of the CustomersList class.
     */
    public CustomersPanel(JPanel mainUIContentPane, CustomersList customers) {
        this.customers = customers;
        this.customersPanel = new JPanel();
        this.customersPanel.setBackground(Color.DARK_GRAY);       
        this.customersPanel.setLayout(new BoxLayout(this.customersPanel, BoxLayout.Y_AXIS));   
        this.customersPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.totalCustomersLabel = new JLabel("Total Customers: " + this.customers.getEntitiesList().size());
        this.totalCustomersLabel.setForeground(Color.WHITE);
        this.customersPanel.add(this.totalCustomersLabel);
        JButton add = new JButton("Add Customer");
        add.addActionListener(e -> createAddFrame());
        this.customersPanel.add(add);
        this.customersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        for (int i = 0; i < this.customers.getEntitiesList().size(); i++) {
            Customer customer = (Customer)this.customers.getEntitiesList().get(i);
            String id = customer.getId();
            String name = customer.getName() + " " + customer.getSurname();
            setCustomersPanelDetails(id, name);
            this.customersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        JScrollPane scrollPane = new JScrollPane(this.customersPanel);
        
        // Optional: control when scrollbars appear
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        mainUIContentPane.add(scrollPane, BorderLayout.WEST);
    }
    
    /**
     * Create a panel for each customer.
     * 
     * @param id The Customer id.
     * @param name The Customer name and surname chained in one string.
     */
    private void setCustomersPanelDetails(String id, String name) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel labelName = new JLabel(name);
        panel.add(labelName);
        panel.add(Box.createRigidArea(new Dimension(5, 10)));
        
        JButton order = new JButton("Order");
        order.addActionListener(e -> System.out.println("Button clicked!" + id));
        panel.add(order);
        JButton history = new JButton("Order History");
        history.addActionListener(e -> System.out.println("Button clicked!" + id));
        panel.add(history);
        JButton update = new JButton("Update Customer");
        update.addActionListener(e -> createUpdateFrame(labelName, id));
        panel.add(update);
        JButton delete = new JButton("Delete Customer");
        delete.addActionListener(e -> {
            customers.deleteEntity(id);
            this.customersPanel.remove(panel);
            refreshCustomersPanel(this.customersPanel);
            this.totalCustomersLabel.setText("Total Customers: " + this.customers.getEntitiesList().size());
        });
        panel.add(delete);
        
        this.customersPanel.add(panel);
    }
    
    /**
     * Refresh the Customer panel UI.
     */
     private void refreshCustomersPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
    
    /**
     * Create a new frame panel to update customer data.
     * 
     * @param label The Label of the Customer panel to update the name showed.
     * @param id The Customer id.
     */
    private void createUpdateFrame(JLabel label, String id) {
        HashMap<Data, String> customerData = this.customers.getEntityData(id);
        JFrame frame = new JFrame("Update Customer");
        frame.setSize(700, 200);
    
        // Create a panel and use GridLayout for label + field pairs
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 cols, spacing
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Input components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(customerData.get(Data.NAME), 15);
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField(customerData.get(Data.SURNAME), 15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(customerData.get(Data.EMAIL), 15);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(customerData.get(Data.ADDRESS), 15);
        JLabel creditCardLabel = new JLabel("Credit Card:");
        JTextField creditCardField = new JTextField(customerData.get(Data.CREDIT_CARD), 15);
    
        JButton updateBtn = new JButton("Update");
        updateBtn.setForeground(new Color(0, 153, 0));
        updateBtn.addActionListener(e -> {
            this.customers.updateEntityData(id, Data.NAME, nameField.getText());
            this.customers.updateEntityData(id, Data.SURNAME, surnameField.getText());
            this.customers.updateEntityData(id, Data.EMAIL, emailField.getText());
            this.customers.updateEntityData(id, Data.ADDRESS, addressField.getText());
            this.customers.updateEntityData(id, Data.CREDIT_CARD, creditCardField.getText());
            HashMap<Data, String> updatedCustomerData = this.customers.getEntityData(id);
            label.setText(updatedCustomerData.get(Data.NAME) + " " + updatedCustomerData.get(Data.SURNAME));
            frame.dispose();
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setForeground(new Color(255, 153, 0));
        cancelBtn.addActionListener(e -> frame.dispose());
    
        // Add components to the updatePanel
        updatePanel.add(nameLabel);
        updatePanel.add(nameField);
        updatePanel.add(surnameLabel);
        updatePanel.add(surnameField);
        updatePanel.add(emailLabel);
        updatePanel.add(emailField);
        updatePanel.add(addressLabel);
        updatePanel.add(addressField);
        updatePanel.add(creditCardLabel);
        updatePanel.add(creditCardField);
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(updateBtn);
        updatePanel.add(cancelBtn);
    
        // Add updatePanel to frame
        frame.add(updatePanel);
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create a new frame panel to update customer data.
     * 
     * @param label The Label of the Customer panel to update the name showed.
     * @param id The Customer id.
     */
    private void createAddFrame() {
        JFrame frame = new JFrame("Add Customer");
        frame.setSize(700, 200);

        // Create a panel and use GridLayout for label + field pairs
        JPanel updatePanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 cols, spacing
        updatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField("", 15);
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField("", 15);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField("", 15);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField("", 15);
        JLabel creditCardLabel = new JLabel("Credit Card:");
        JTextField creditCardField = new JTextField("", 15);

        JButton addBtn = new JButton("Add");
        addBtn.setForeground(new Color(0, 153, 0));
        addBtn.addActionListener(e -> {
            this.customers.addCustomer(nameField.getText(), surnameField.getText(), emailField.getText(), addressField.getText(), creditCardField.getText());
            this.totalCustomersLabel.setText("Total Customers: " + this.customers.getEntitiesList().size());
            Customer customer = (Customer)this.customers.getEntitiesList().getLast();
            String id = customer.getId();
            String name = customer.getName() + " " + customer.getSurname();
            setCustomersPanelDetails(id, name);
            this.customersPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            frame.dispose();
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setForeground(new Color(255, 153, 0));
        cancelBtn.addActionListener(e -> frame.dispose());

        // Add components to the updatePanel
        updatePanel.add(nameLabel);
        updatePanel.add(nameField);
        updatePanel.add(surnameLabel);
        updatePanel.add(surnameField);
        updatePanel.add(emailLabel);
        updatePanel.add(emailField);
        updatePanel.add(addressLabel);
        updatePanel.add(addressField);
        updatePanel.add(creditCardLabel);
        updatePanel.add(creditCardField);
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(new JLabel(""));
        updatePanel.add(addBtn);
        updatePanel.add(cancelBtn);

        // Add updatePanel to frame
        frame.add(updatePanel);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
}