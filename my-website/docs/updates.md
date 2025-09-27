# Project Documentation

## Overview

This project provides a backend system for managing customers, users, suppliers, and goods, including logging capabilities. The system is designed to handle data efficiently, offering functionalities for adding, updating, and deleting records. Additionally, a user interface is provided to interact with these functionalities.

## Logger

The `Logger` class provides static methods to log messages at different levels:

- **info(String message)**: Logs informational messages.
- **warn(String message)**: Logs warning messages.
- **error(String message)**: Logs error messages.
- **debug(String message)**: Logs debug messages.

### Usage Example

```java
Logger.info("This is an informational message.");
Logger.warn("This is a warning message.");
Logger.error("This is an error message.");
Logger.debug("This is a debug message.");
```

## Customers

The `CustomersList` class manages a list of customer entities.

### Public Methods

- **addCustomer(String name, String surname, String email, String address, String creditCard)**: Adds a new customer.
- **getEntityData(String id)**: Returns the data of a specified customer by ID.
- **updateEntityData(String id, Data key, String value)**: Updates the data of a specified customer by ID.
- **deleteEntity(String id)**: Deletes a specified customer by ID.

## Suppliers

The `SuppliersList` class manages a list of supplier entities.

### Public Methods

- **addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode)**: Adds a new supplier.
- **getEntityData(String id)**: Returns the data of a specified supplier by ID.
- **updateEntityData(String id, Data key, String value)**: Updates the data of a specified supplier by ID.
- **deleteEntity(String id)**: Deletes a specified supplier by ID.

## Goods

The `Goods` class manages a list of warehouse goods.

### Public Methods

- **addGood(String name, String description, int quantity, String supplier, int price)**: Adds a new good.
- **getGoodData(String id)**: Returns the data of a specified good by ID.
- **getGoodQuantity(String id)**: Returns the quantity of a specified good by ID.
- **updateGoodQuantity(String id, int quantity)**: Updates the quantity of a specified good by ID.

## Entities

### Data Enum

The `Data` enum defines the possible data fields for entities:

- ID
- NAME
- SURNAME
- ADDRESS
- EMAIL
- CREDIT_CARD
- BANK_ACCOUNT
- CRN
- SORT_CODE
- DESCRIPTION
- QUANTITY
- SUPPLIER
- PRICE

### Entity Class

The `Entity` class is an abstract class providing methods to handle common entity operations.

## Customer and Supplier Entities

### Customer Class

Implements the `ICustomer` interface and extends `Entity` to represent customer entities.

### Supplier Class

Implements the `ISupplier` interface and extends `Entity` to represent supplier entities.

## Interfaces

### ICustomer Interface

Defines the method to add a new customer entity.

### ISupplier Interface

Defines the method to add a new supplier entity.

## Main Application

### Main Class

The `Main` class is the entry point of the application, launching the user interface.

### MainUI Class

The `MainUI` class builds and displays the application GUI, initializing components for customers, suppliers, and goods.

## User Interface Components

### CustomersPanel

Manages the display and interaction with customer data within the UI.

### SuppliersPanel

Manages the display and interaction with supplier data within the UI.

### GoodsPanel

Manages the display and interaction with goods data within the UI.

### NavBar

Provides navigation and menu options within the application UI.

## Package Structure

- **backend**: Contains the main classes for managing customers, suppliers, and goods.
- **backend.entities**: Contains entity-related classes and interfaces.
- **backend.goods**: Contains classes related to goods management.
- **ui**: Contains classes for building the user interface.
- **ui.components**: Contains UI components for managing entities.

This documentation provides an overview of the public APIs and features available in the current codebase. For further details, refer to the source code and comments within each class.