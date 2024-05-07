package se.kth.IV1350.progExe.integration.external;

/**
 * Thrown when connection to database fails.
 */
public class DatabaseConnectionException extends Exception {
    
    /**
     * 
     * 
     * @param reason why Exception was thrown.
     */
    public DatabaseConnectionException(String reason) {
        super(reason);
    }

    /**
     * 
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public DatabaseConnectionException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
