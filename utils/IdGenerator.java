package utils;

import java.time.Instant;

 /**
 * A class that provide a static method to generate IDs.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class IdGenerator {
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

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
        String rand = String.valueOf((int)(Math.random() * 1000));
        return String.valueOf(Instant.now().getEpochSecond()) + "-" + rand;
    }
}
