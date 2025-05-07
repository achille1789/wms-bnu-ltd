import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import backend.finances.FinanceItem;

/**
 * Test class for FinanceItem.
 * 
 * @author Vanni Gallo
 * @version 1.0
 */
public class FinanceItemTest {
    @Test
    public void FinanceItemTest() {
        // act
        FinanceItem financeItem = new FinanceItem();       
        // assert
        assertNotNull(financeItem);
        assertEquals(0, financeItem.getQuantity());
        assertEquals(0, financeItem.getCost());
        
        // act
        financeItem.addQuantity(10);
        financeItem.addCost(10);
        // assert
        assertEquals(10, financeItem.getQuantity());
        assertEquals(10, financeItem.getCost());
        
        // act
        financeItem.addQuantity(30);
        financeItem.addCost(30);
        // assert
        assertEquals(40, financeItem.getQuantity());
        assertEquals(40, financeItem.getCost());
    }
}