package src.utils;

import java.time.Instant;

 /**
 * A class that provide a static method to generate IDs.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class IdGenerator {
    /**
     * Using a private constructor to prevent instantiation of this class.
     */
    private IdGenerator() {}

    /**
     * Printing "info" messages.
     *
     * @return a String with the new ID
     */
    public static String getId() {
        String rand = String.format("%03d", (int)(Math.random() * 1000));
        return String.valueOf(Instant.now().getEpochSecond()) + "-" + rand;
    }
}
