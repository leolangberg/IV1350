package se.kth.IV1350.progExe.integration.external.Exceptions;

/**
 * Thrown when database is presented with Invalid Id.
 */
public class InvalidIdentifierException extends DatabaseException {

    /**
     * Throws Exception.
     * 
     * @param reason why Exception was thrown.
     */
    public InvalidIdentifierException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public InvalidIdentifierException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
    
}
