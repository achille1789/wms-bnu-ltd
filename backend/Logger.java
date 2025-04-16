 /**
 * A class that handles all the logs.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
class Logger {
    // ANSI color codes
//     public static final String RESET = "\u001B[0m";
//     public static final String RED = "\u001B[31m";
//     public static final String GREEN = "\u001B[32m";
//     public static final String YELLOW = "\u001B[33m";
//     public static final String BLUE = "\u001B[34m";

    /**
     * Using a private constructor to prevent instantiation of this class.
     */
    private Logger() {}

    /**
     * Printing "info" messages.
     */
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    /**
     * Printing "warning" messages.
     */
    public static void warn(String message) {
//         System.out.println(YELLOW + "[WARN] " + message + RESET);
        System.out.println("[WARN] " + message);
    }

    /**
     * Printing "error" messages.
     */
    public static void error(String message) {
//         System.out.println(RED + "[ERROR] " + message + RESET);
        System.out.println("[ERROR] " + message);
    }

    /**
     * Printing "debug" messages.
     */
    public static void debug(String message) {
//         System.out.println(BLUE + "[DEBUG] " + message + RESET);
        System.out.println("[DEBUG] " + message);
    }
}
