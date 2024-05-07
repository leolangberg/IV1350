package se.kth.IV1350.progExe.logger;

/**
 * Prints log messages to <code>System.out</code>.
 */
public class ConsoleLogger implements Logger {
    
    /**
     * Prints the specified string to <code>System.out</code>.
     * 
     * @param message The string that will be printed to <code>System.out</code>.
     */
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
