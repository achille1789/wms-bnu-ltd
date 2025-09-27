# Project Documentation

## Overview

This project provides a backend system for managing customers, users, suppliers, and goods, including logging capabilities. The system is designed to handle data efficiently, offering functionalities for adding, updating, and deleting records.

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

The `Customers` class manages a list of customer entities.

### Public Methods

- **addCustomer(String name, String surname, String email, String address, String creditCard)**: Adds a new customer if they don't already exist.
- **getTotalCustomers()**: Returns the total number of customers.
- **getCustomerNames()**: Returns an array of customer names.
- **getCustomerData(String name, String surname)**: Returns the data of a specified customer.
- **updateCustomerData(String name, String surname, Data key, String value)**: Updates the data of a specified customer.
- **deleteCustomer(String name, String surname)**: Deletes a specified customer.

## Suppliers

The `Suppliers` class manages a list of supplier entities.

### Public Methods

- **addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode)**: Adds a new supplier if it doesn't already exist.
- **getTotalSuppliers()**: Returns the total number of suppliers.
- **getSupplierNames()**: Returns an array of supplier names.
- **getSupplierData(String name)**: Returns the data of a specified supplier.
- **updateSupplierData(String name, Data key, String value)**: Updates the data of a specified supplier.
- **deleteSupplier(String name)**: Deletes a specified supplier.

## Goods

The `Goods` class manages a list of warehouse goods.

### Public Methods

- **addGood(String name, String description, int quantity, String supplier)**: Adds a new good if it doesn't already exist.
- **getTotalGoods()**: Returns the total number of goods.
- **getGoodNames()**: Returns an array of good names.
- **getGoodData(String name)**: Returns the data of a specified good.
- **getGoodQuantity(String name)**: Returns the quantity of a specified good.
- **updateGoodQuantity(String name, int quantity)**: Updates the quantity of a specified good.

## Entities

### Data Enum

The `Data` enum defines the possible data fields for entities:

- NAME
- SURNAME
- ADDRESS
- EMAIL
- CREDIT_CARD
- BANK_ACCOUNT
- CRN
- SORT_CODE

### Entity Class

The `Entity` class is an abstract class providing methods to handle common entity operations.

### EntityData Class

The `EntityData` class is an abstract class that specifies the necessary data for a generic entity.

## Customer and Supplier Entities

### Customer Class

Implements the `ICustomer` interface and extends `Entity` to represent customer entities.

### CustomerData Class

Extends `EntityData` to specify the necessary data for a customer.

### Supplier Class

Implements the `ISupplier` interface and extends `Entity` to represent supplier entities.

### SupplierData Class

Extends `EntityData` to specify the necessary data for a supplier.

## Interfaces

### ICustomer Interface

Defines the method to add a new customer entity.

### ISupplier Interface

Defines the method to add a new supplier entity.

## Package Structure

- **backend**: Contains the main classes for managing customers, suppliers, and goods.
- **backend.entities**: Contains entity-related classes and interfaces.
- **backend.goods**: Contains classes related to goods management.

This documentation provides an overview of the public APIs and features available in the current codebase. For further details, refer to the source code and comments within each class.