# Warehouse Management System for BNU Industry Solutions Ltd

This application is a Warehouse Management System (WMS) developed for BNU Industry Solutions Ltd.
It is designed to streamline the management of warehouse inventory and operations, offering features such as:
- Stock level tracking
- Supplier and customer management
- Order processing
- Warehouse process optimization
- Report generation
The system includes both a backend and a graphical user interface (GUI).

## Technical aspects
The whole system has been developed using Java on a Macbook Pro with M1 chip.
These are the versions used:
```text
openjdk version "23.0.2" 2025-01-21
OpenJDK Runtime Environment Homebrew (build 23.0.2)
OpenJDK 64-Bit Server VM Homebrew (build 23.0.2, mixed mode, sharing)
```

## How to run the application
The application can be run from CLI or from BlueJ app.
### From CLI
1. Open a terminal window.
2. Navigate to the directory where the project is located.
3. Compile the Java files (Windows OS may require a different command):
```bash
javac -d . $(find . -type f -name "*.java" ! -path "*/unit/*" ! -path "*/jacoco/*")
```
4. Run the main class:
```bash
java Main
```
### From BlueJ
1. Open BlueJ.
2. Open the project folder.
3. Compile the project.
4. Right-click on the Main class and select "void main()".<br>
<img src="/readme-images/bluej-main.png" width="400" />

## How to use the application
### Add Suppliers, Warehouse Items and Customers
1. Launch the application.<br>
<img src="/readme-images/main.png" width="400" />
2. The user interface is divided into five main sections:
   - Nav Bar: Positioned at the top of the window, it includes the "Menu" and "About" tabs.
   - Customers panel: Located on the left side, it displays a list of all existing customers.
   - Warehouse items panel: Centred in the window, it shows all available items for purchase.
   - Suppliers panel: Found on the right side, it lists all current suppliers.
   - Financial report panel: Situated at the bottom, it features a button to generate a financial report.
3. Add one or more suppliers:
   - Click on the "Add Supplier" button.
   - Enter the required information, then click "Add Supplier" again to confirm.
4. Add one or more warehouse items to the catalog:
   - Click on the "Add Warehouse Item" button.
   - Enter required information:
        - Supplier Price: The cost at which the supplier sells the item. The customer price will be 40% higher.
        - Initial Quantity: The starting stock level. A non-zero value is considered leftover inventory from the previous year and will be excluded from the financial report.
        - Supplier: Select the appropriate supplier from the dropdown list.
   - Click "Add Item" to finalise.
5. Add one or more customers:
   - Click on the "Add Customer" button.
   - Enter the necessary information, then click "Add Customer" to save.

<img src="/readme-images/main-populated.png" width="400" />

### Purchase Items
Items can be purchased from both suppliers and customers, following a similar process with a few key differences:
- Customer orders allow selection from any available items, regardless of supplier. In contrast, supplier orders are limited to items provided by the selected supplier.
- The purchase price for customers is 40% higher than the supplier price.
- When items are purchased from a customer, the selected quantities are immediately deducted from stock. When items are ordered from a supplier, the quantities are marked as "pending" and must be manually received by clicking the "Receive Deliveries" button before they are added to stock.
To place an order:<br>
1. Select a customer or supplier from the appropriate panel.
2. Click the "Order" button.
3. Choose one or more items and specify the desired quantity.
4. Click "Purchase" to confirm the order.

### Customer/Supplier Order History
1. Click on the "Order History" button to view all past orders.

### Customer/Supplier Update details
1. Click on the "Update Customer" or "Update Supplier" button and update the required information.
2. Confirm to save the changes.

### Delete Customer/Supplier
1. Click on the "Delete Customer" or "Delete Supplier" button.

### Generate Financial Report
1. Click on the "Generate Financial Report" button.
2. A new window will open, showing a detailed financial report that includes:
   - All customer purchases made during the current year
   - All supplier orders placed to replenish inventory
   - The total cost and profit will be automatically calculated and displayed.

<img src="/readme-images/report.png" width="400" />

## Unit Tests
The project includes unit tests to ensure the functionality of the application.<br>
The tests are written using JUnit and can be run from the command line.
The tests are located in the `unit` folder and can be run using the following command (Windows OS may require a different command):
```bash
javac -d bin -cp .:junit-platform-console-standalone-1.11.1.jar $(find src -name "*.java") $(find unit -name "*.java")
java -jar junit-platform-console-standalone-1.11.1.jar --class-path bin --scan-class-path
```