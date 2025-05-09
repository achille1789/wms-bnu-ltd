import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import src.utils.Logger;
import src.utils.LogType;

/**
 * Test class for Logger.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class LoggerTest {

    @Test
    void infoTest() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
                
        // Act
        Logger.info("info message");
        System.setOut(originalOut);

        // Assert
        assertEquals("\u001B[32m[INFO] - info message\u001B[0m" + System.lineSeparator(), outContent.toString());
    }
    
    @Test
    void warnTest() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Act
        Logger.warn("warning message");
        System.setOut(originalOut);

        // Assert
        assertEquals("\u001B[33m[WARN] - warning message\u001B[0m" + System.lineSeparator(), outContent.toString());
    }
    
    @Test
    void errorTest() {
        // Arrange
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        // Act
        Logger.error("error message");
        System.setOut(originalOut);

        // Assert
        assertEquals("\u001B[31m[ERROR] - error message\u001B[0m" + System.lineSeparator(), outContent.toString());
    }
}