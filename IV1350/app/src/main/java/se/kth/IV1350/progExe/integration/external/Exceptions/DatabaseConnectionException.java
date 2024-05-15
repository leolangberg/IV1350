package se.kth.IV1350.progExe.integration.external.Exceptions;

/**
 * Thrown when connection to database fails.
 */
public class DatabaseConnectionException extends DatabaseException {
    
    /**
     * Throws Excpetion.
     * 
     * @param reason why Exception was thrown.
     */
    public DatabaseConnectionException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public DatabaseConnectionException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
