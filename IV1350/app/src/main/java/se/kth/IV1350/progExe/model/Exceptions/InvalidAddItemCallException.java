package se.kth.IV1350.progExe.model.Exceptions;

/**
 * Exception thrown when an Item is added to an already ended Sale.
 */
public class InvalidAddItemCallException extends SaleException {
    
      
    /**
     * Throws Exception.
     * 
     * @param reason why Exception was thrown.
     */
    public InvalidAddItemCallException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public InvalidAddItemCallException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
