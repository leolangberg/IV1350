package se.kth.IV1350.progExe.model;

public class InvalidAddItemCallException extends Exception {
    
      
    /**
     * 
     * @param reason why Exception was thrown.
     */
    public InvalidAddItemCallException(String reason) {
        super(reason);
    }

    /**
     * 
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public InvalidAddItemCallException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
