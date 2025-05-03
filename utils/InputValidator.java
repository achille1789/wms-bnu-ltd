package utils;

import java.util.HashMap;

import utils.InputType;

/**
 * A class that provide static methods to perform input validation and sanitation.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class InputValidator {
    /**
     * Using a private constructor to prevent instantiation of this class.
     */
    private InputValidator() {}

    /**
     * Sanitise text field.
     *
     * @param text the text to sanitise
     * @return a String with the text sanitised
     */
    private static String sanitiseText(String text) throws Exception {
        if (text == null || text.isEmpty()) throw new Exception("Input text is empty");
        return text.trim();
    }
    
    /**
     * Validate positive integer.
     *
     * @param text the text to validate
     * @return a String with the integer validated
     */
    private static String validateInt(String text) throws Exception {
        String sanitisedText = sanitiseText(text);
        if (sanitisedText.matches("^\\d+$")) return sanitisedText;
        throw new Exception(text + " is not a valid positive integer");
    }
    
    /**
     * Validate positive float.
     *
     * @param text the text to validate
     * @return a String with the float validated
     */
    private static String validateFloat(String text) throws Exception {
        String sanitisedText = sanitiseText(text);
        if (sanitisedText.matches("^\\d+(\\.\\d+)?$")) return String.format("%.2f", Float.parseFloat(sanitisedText));
        throw new Exception(text + " is not a valid positive float");
    }
    
    /**
     * Validate positive integer and number of digits.
     *
     * @param text the text to validate
     * @param digits the number of digits to validate
     * @return a String with the integer validated
     */
    private static String validateIntDigits(String text, int digits) throws Exception {
        String sanitisedText = sanitiseText(text);
        if (!validateInt(sanitisedText).equals("") && sanitisedText.length() == digits) return sanitisedText;
        throw new Exception(text + " is not " + digits + " digits long");
    }
    
    /**
     * Validate sort code.
     *
     * @param text the text to validate
     * @return a String with the sort code validated
     */
    private static String validateSortCode(String text) throws Exception {
        String sanitisedText = sanitiseText(text);
        if (sanitisedText.matches("^\\d{2}-\\d{2}-\\d{2}$")) return sanitisedText;
        throw new Exception(text + " is not a valid sort code");
    }
    
    /**
     * Validate email address.
     *
     * @param text the text to validate
     * @return a String with the email address validated
     */
    private static String validateEmail(String text) throws Exception {
        String sanitisedText = sanitiseText(text);
        if (sanitisedText.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) return sanitisedText;
        throw new Exception(text + " is not a valid email address");
    }
    
    /**
     * Validate inputs.
     *
     * @param quantity the int quantity to validate
     * @return a HashMap with the validated inputs
     */
    public static HashMap<InputType, String> getValidateInputs(String quantity) throws Exception {
        HashMap<InputType, String> validatedInputs = new HashMap<>();
        validatedInputs.put(InputType.QUANTITY, validateInt(quantity));
        return validatedInputs;
    }
    
    /**
     * Validate inputs for Warehouse Item data.
     *
     * @param name the name to validate
     * @param description the description to validate
     * @param quantity the int quantity to validate
     * @param price the float price to validate
     * @return a HashMap with the validated inputs
     */
    public static HashMap<InputType, String> getValidateInputs(String name, String description, String quantity, String price) throws Exception {
        HashMap<InputType, String> validatedInputs = new HashMap<>();
        validatedInputs.put(InputType.NAME, sanitiseText(name));
        validatedInputs.put(InputType.DESCRIPTION, sanitiseText(description));
        validatedInputs.put(InputType.QUANTITY, validateInt(quantity));
        validatedInputs.put(InputType.PRICE, validateFloat(price));
        return validatedInputs;
    }
        
    /**
     * Validate inputs Customer data.
     *
     * @param name the name to validate
     * @param surname the surname to validate
     * @param email the email to validate
     * @param address the address to validate
     * @param creditCard the credit card to validate
     * @return a HashMap with the validated inputs
     */
    public static HashMap<InputType, String> getValidateInputs(String name, String surname, String email, String address, String creditCard) throws Exception {
        HashMap<InputType, String> validatedInputs = new HashMap<>();
        validatedInputs.put(InputType.NAME, sanitiseText(name));
        validatedInputs.put(InputType.SURNAME, sanitiseText(surname));
        validatedInputs.put(InputType.EMAIL, validateEmail(email));
        validatedInputs.put(InputType.ADDRESS, sanitiseText(address));
        validatedInputs.put(InputType.CREDIT_CARD, validateIntDigits(creditCard, 16));
        return validatedInputs;
    }
    
    /**
     * Validate inputs for Supplier data.
     *
     * @param name the name to validate
     * @param crn the company registration number to validate
     * @param email the email to validate
     * @param address the address to validate
     * @param bankAccount the bank account to validate
     * @param sortCode the sort code to validate
     * @return a HashMap with the validated inputs
     */
    public static HashMap<InputType, String> getValidateInputs(String name, String crn, String email, String address, String bankAccount, String sortCode) throws Exception {
        HashMap<InputType, String> validatedInputs = new HashMap<>();
        validatedInputs.put(InputType.NAME, sanitiseText(name));
        validatedInputs.put(InputType.CRN, validateIntDigits(crn, 8));
        validatedInputs.put(InputType.EMAIL, validateEmail(email));
        validatedInputs.put(InputType.ADDRESS, sanitiseText(address));
        validatedInputs.put(InputType.BANK_ACCOUNT, validateIntDigits(bankAccount, 8));
        validatedInputs.put(InputType.SORT_CODE, validateSortCode(sortCode));
        return validatedInputs;
    }
}