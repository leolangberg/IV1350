package se.kth.IV1350.progExe.model;

public class TransactionFailedException extends Exception {
    
    /**
     * 
     * @param reason why Exception was thrown.
     */
    public TransactionFailedException(String reason) {
        super(reason);
    }

    /**
     * 
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public TransactionFailedException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
    
}
