package se.kth.IV1350.progExe.integration.external;

/**
 * Thrown when database is presented with invalid Quantity amount.
 */
public class InvalidQuantityException extends Exception {
    
    /**
     * 
     * @param reason why Exception was thrown.
     */
    public InvalidQuantityException(String reason) {
        super(reason);
    }

    /**
     * 
     * 
     * @param reason why Exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public InvalidQuantityException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
