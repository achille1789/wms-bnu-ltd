# Project Documentation

## Overview

This project provides a backend system for managing users and suppliers, including logging capabilities. The system is designed to handle user and supplier data efficiently, offering functionalities for adding, updating, and deleting records.

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

## Suppliers

The `Suppliers` class manages a list of supplier entities.

### Public Methods

- **addSupplier(String name, String crn, String email, String address, String bankAccount, String sortCode)**: Adds a new supplier if it doesn't already exist.
- **getTotalSuppliers()**: Returns the total number of suppliers.
- **getSupplierNames()**: Returns an array of supplier names.
- **getSupplierData(String name)**: Returns the data of a specified supplier.
- **updateSupplierData(String name, Data key, String value)**: Updates the data of a specified supplier.
- **deleteSupplier(String name)**: Deletes a specified supplier.

## Users

The `Users` class manages a list of user entities.

### Public Methods

- **addUser(String name, String surname, String email, String address, String creditCard)**: Adds a new user if they don't already exist.
- **getTotalUsers()**: Returns the total number of users.
- **getUserNames()**: Returns an array of user names.
- **getUserData(String name)**: Returns the data of a specified user.
- **updateUserData(String name, Data key, String value)**: Updates the data of a specified user.
- **deleteUser(String name)**: Deletes a specified user.

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

## Supplier and User Entities

### Supplier Class

Implements the `ISupplier` interface and extends `Entity` to represent supplier entities.

### SupplierData Class

Extends `EntityData` to specify the necessary data for a supplier.

### User Class

Implements the `IUser` interface and extends `Entity` to represent user entities.

### UserData Class

Extends `EntityData` to specify the necessary data for a user.

## Interfaces

### ISupplier Interface

Defines the method to add a new supplier entity.

### IUser Interface

Defines methods to add a new user entity and retrieve user-specific data.

## Package Structure

- **backend**: Contains the main classes for managing users and suppliers.
- **backend.entities**: Contains entity-related classes and interfaces.

This documentation provides an overview of the public APIs and features available in the current codebase. For further details, refer to the source code and comments within each class.