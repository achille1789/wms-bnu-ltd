import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import utils.IdGenerator;

/**
 * Test class for IdGenerator.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class IdGeneratorTest {
    @Test
    public void testGetId() {
        // act
        String id = IdGenerator.getId();
        // assert
        assertNotNull(id);
        assertFalse(id.isEmpty());
        assertEquals(14, id.length());
        assertTrue(id.matches("[a-zA-Z0-9]{10}-[a-zA-Z0-9]{3}"));
    }
}