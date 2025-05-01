package utils;

/**
 * Enam with all the type of log
 */
enum LogType {
    INFO,
    WARN,
    ERROR,
    DEBUG,
}

 /**
 * A class that handles all the logs.
 *
 * @author Vanni Gallo
 * @version 1.0.0
 */
public class Logger {
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";

    /**
     * Using a private constructor to prevent instantiation of this class.
     */
    private Logger() {}

    /**
     * Printing "info" messages.
     * @param message the message to print
     */
    public static void info(String message) {
        System.out.println(GREEN + "[" + LogType.INFO + "] - "  + message + RESET);
    }

    /**
     * Printing "warning" messages.
     * @param message the message to print
     */
    public static void warn(String message) {
        System.out.println(YELLOW + "[" + LogType.WARN + "] - "  + message + RESET);
    }

    /**
     * Printing "error" messages.
     * @param message the message to print
     */
    public static void error(String message) {
        System.out.println(RED + "[" + LogType.ERROR + "] - " + message + RESET);
    }

    /**
     * Printing "debug" messages.
     * @param message the message to print
     */
    public static void debug(String message) {
        System.out.println(BLUE + "[" + LogType.DEBUG + "] - " + message + RESET);
    }
}
