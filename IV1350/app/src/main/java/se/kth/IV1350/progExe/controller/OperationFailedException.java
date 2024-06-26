package se.kth.IV1350.progExe.controller;

/**
 * Thrown when a system operation fails.
 */
public class OperationFailedException extends Exception {
    
    /**
     * Throws Exception.
     * 
     * @param reason why Exception was thrown.
     */
    public OperationFailedException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public OperationFailedException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
