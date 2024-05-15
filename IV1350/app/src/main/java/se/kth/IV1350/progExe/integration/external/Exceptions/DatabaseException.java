package se.kth.IV1350.progExe.integration.external.Exceptions;

/**
 * This a superclass responsible for encapsulating all Exceptions
 * related to operation calls to the database.
 * 
 * @DatabaseConnectionException regarding database connectivity.
 * @InvalidIdentifierException regarding use of invalid ID's.
 * @InvalidQuantityException regard use of invalid Quantity values.
 */
public class DatabaseException extends Exception {
    
    /**
     * Throws Exception.
     * 
     * @param reason why Exception was thrown.
     */
    public DatabaseException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public DatabaseException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}

