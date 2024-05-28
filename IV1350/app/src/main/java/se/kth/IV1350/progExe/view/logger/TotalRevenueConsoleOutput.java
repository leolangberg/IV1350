package se.kth.IV1350.progExe.view.logger;

import se.kth.IV1350.progExe.view.TotalRevenueView;

/**
 * TotalRevenueConsoleOutput extends the abstract super-class TotalRevenueView.
 * This class therefore implements the inherited abstract method defined in the abstract super-class.
 */
public class TotalRevenueConsoleOutput extends TotalRevenueView {
    
    private ErrorMsgHandler errorMsgHandler;
    private StringHandler stringHandler;

    /**
     * Constructor for TotalRevenueConsoleOutput.
     */
    public TotalRevenueConsoleOutput() {
        this.errorMsgHandler = new ErrorMsgHandler();
        this.stringHandler = new StringHandler();
    }


    /**
     * Shows current Total Revenue.
     */
    protected void doShowTotalIncome() {
        stringHandler.log("Total Revenue: " + totalRevenue);
    }

    /**
     * Handles error in case Exception erupts.
     * @param e exception.
     */
    protected void handleErrors(Exception e) {
        errorMsgHandler.log(e);
    } 
}
