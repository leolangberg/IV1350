package se.kth.IV1350.progExe.integration.external;

/**
 * Thrown when database is presented with Invalid Id.
 */
public class InvalidIdentifierException extends Exception {

    /**
     * 
     * @param reason why Exception was thrown.
     */
    public InvalidIdentifierException(String reason) {
        super(reason);
    }

    /**
     * 
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public InvalidIdentifierException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
    
}
