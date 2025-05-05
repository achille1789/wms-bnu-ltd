import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import utils.InputValidator;
import utils.InputType;

/**
 * Test class for InputValidator.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class InputValidatorTest {

    // getValidateInputs with 1 param
    @Test
    void testGetValidateInputsOneParamNoException() throws Exception {
        // act
        HashMap<InputType, String> result = InputValidator.getValidateInputs("45");
        // assert
        assertEquals(1, result.size());
        assertEquals("45", result.get(InputType.QUANTITY));
    }
    @Test
    void testGetValidateInputsOneParamWithBlankSpacesNoException() throws Exception {
        // act
        HashMap<InputType, String> result = InputValidator.getValidateInputs("  45   ");
        // assert
        assertEquals(1, result.size());
        assertEquals("45", result.get(InputType.QUANTITY));
    }
    @Test
    void testGetValidateInputsOneParamWithExceptionInt() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("fourtyfive"));
        // assert
        assertEquals("fourtyfive is not a valid positive integer", ex.getMessage());
    }    
    @Test
    void testGetValidateInputsOneParamWithExceptionEmpty() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(""));
        // assert
        assertEquals("Input text is empty", ex.getMessage());
    }
    
    // getValidateInputs with 4 params
    @Test
    void testGetValidateInputsFourParamsNoException() throws Exception {
        // act
        HashMap<InputType, String> result = InputValidator.getValidateInputs(" name ", "   description", "45", "34.6789");
        // assert
        assertEquals(4, result.size());
        assertEquals("name", result.get(InputType.NAME));
        assertEquals("description", result.get(InputType.DESCRIPTION));
        assertEquals("45", result.get(InputType.QUANTITY));
        assertEquals("34.68", result.get(InputType.PRICE));
    }
    @Test
    void testGetValidateInputsFourParamsWithExceptionEmpty() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("", "   description", "45", "34.6789"));
        // assert
        assertEquals("Input text is empty", ex.getMessage());
    }    
    @Test
    void testGetValidateInputsFourParamsWithExceptionFloat() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "description", "45", "-34.6789"));
        // assert
        assertEquals("-34.6789 is not a valid positive float", ex.getMessage());
    } 
    
    // getValidateInputs with 5 params
    @Test
    void testGetValidateInputsFiveParamsNoException() throws Exception {
        // act
        HashMap<InputType, String> result = InputValidator.getValidateInputs(" name ", "   surname", "email@test.com", "1 address London", "1234567887654321");
        // assert
        assertEquals(5, result.size());
        assertEquals("name", result.get(InputType.NAME));
        assertEquals("surname", result.get(InputType.SURNAME));
        assertEquals("email@test.com", result.get(InputType.EMAIL));
        assertEquals("1 address London", result.get(InputType.ADDRESS));
        assertEquals("1234567887654321", result.get(InputType.CREDIT_CARD));
    }
    @Test
    void testGetValidateInputsFiveParamsWithExceptionEmpty() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "", "1 address London", "1234567887654321"));
        // assert
        assertEquals("Input text is empty", ex.getMessage());
    }    
    @Test
    void testGetValidateInputsFiveParamsWithExceptionEmail() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "email@test", "1 address London", "1234567887654321"));
        // assert
        assertEquals("email@test is not a valid email address", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsFiveParamsWithExceptionEmail2() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "email@test.c", "1 address London", "1234567887654321"));
        // assert
        assertEquals("email@test.c is not a valid email address", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsFiveParamsWithExceptionEmail3() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "emailtest.com", "1 address London", "1234567887654321"));
        // assert
        assertEquals("emailtest.com is not a valid email address", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsFiveParamsWithExceptionCreditCard() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "email@test.com", "1 address London", "123"));
        // assert
        assertEquals("123 is not 16 digits long", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsFiveParamsWithExceptionCreditCard2() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "email@test.com", "1 address London", "123456788765432112345678"));
        // assert
        assertEquals("123456788765432112345678 is not 16 digits long", ex.getMessage());
    }
    @Test
    void testGetValidateInputsFiveParamsWithExceptionCreditCard3() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs(" name ", "   surname", "email@test.com", "1 address London", "1234-5678-8765-4321"));
        // assert
        assertEquals("1234-5678-8765-4321 is not a valid positive integer", ex.getMessage());
    }  
    
    // getValidateInputs with 6 params
    @Test
    void testGetValidateInputsSixParamsNoException() throws Exception {
        // act
        HashMap<InputType, String> result = InputValidator.getValidateInputs(" name ", "12345678", "email@test.com", "1 address London", "87654321", "12-34-56");
        // assert
        assertEquals(6, result.size());
        assertEquals("name", result.get(InputType.NAME));
        assertEquals("12345678", result.get(InputType.CRN));
        assertEquals("email@test.com", result.get(InputType.EMAIL));
        assertEquals("87654321", result.get(InputType.BANK_ACCOUNT));
        assertEquals("12-34-56", result.get(InputType.SORT_CODE));
    }
    @Test
    void testGetValidateInputsSixParamsWithExceptionEmpty() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "12345678", "email@test.com", "1 address London", "", "12-34-56"));
        // assert
        assertEquals("Input text is empty", ex.getMessage());
    }    
    @Test
    void testGetValidateInputsSixParamsWithExceptionCRN() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "xxx", "email@test.com", "1 address London", "87654321", "12-34-56"));
        // assert
        assertEquals("xxx is not a valid positive integer", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsSixParamsWithExceptionCRN2() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "12345678123", "email@test.com", "1 address London", "87654321", "12-34-56"));
        // assert
        assertEquals("12345678123 is not 8 digits long", ex.getMessage());
    } 
    @Test
    void testGetValidateInputsSixParamsWithExceptionBankAccount() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "12345678", "email@test.com", "1 address London", "87654", "12-34-56"));
        // assert
        assertEquals("87654 is not 8 digits long", ex.getMessage());
    }
    @Test
    void testGetValidateInputsSixParamsWithExceptionSortCode() {
        // act
        Exception ex = assertThrows(Exception.class, () -> InputValidator.getValidateInputs("name", "12345678", "email@test.com", "1 address London", "87654321", "12-3456"));
        // assert
        assertEquals("12-3456 is not a valid sort code", ex.getMessage());
    } 
}