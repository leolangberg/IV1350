package se.kth.IV1350.progExe.model.Exceptions;

/**
 * This is a superclass that encompasses and encapsulated all types of Excpetions thrown because of
 * Failed Operations regarding the ongoing Sale. 
 * 
 * @InvalidAddItemCallException in case one tries to add items to an already ended sale.
 * @TransactionFailedException for invalid purchases.
 */
public class SaleException extends Exception {
    
    /**
     * Throws Exception.
     * 
     * @param reason why Exception was thrown.
     */
    public SaleException(String reason) {
        super(reason);
    }

    /**
     * Throws Excpetion together with the root cause behind it.
     * 
     * @param reason Why exception was thrown.
     * @param rootCause The root Exception that caused this exception to thrown.
     */
    public SaleException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }
}
