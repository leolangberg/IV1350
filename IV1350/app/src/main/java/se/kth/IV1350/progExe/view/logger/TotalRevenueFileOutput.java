package se.kth.IV1350.progExe.view.logger;

import se.kth.IV1350.progExe.view.TotalRevenueView;
import java.io.FileWriter;


/**
 * TotalRevenueFileOutput extends the abstract super-class TotalRevenueView.
 * This class therefore implements the inherited abstract method defined in the abstract super-class.
 */
public class TotalRevenueFileOutput extends TotalRevenueView {
    
    private ErrorMsgHandler errorMsgHandler;

    /**
     * Constructor for TotalRevenueFileOutput.
     */
    public TotalRevenueFileOutput() {
        this.errorMsgHandler = new ErrorMsgHandler();
    }

    /**
     * Shows current Total Revenue via Filewrite in 'totalRevenue.txt'.
     * @throws Exception in case file write or operation fails.
     */
    protected void doShowTotalIncome() throws Exception {
        try(FileWriter fileWriter = new FileWriter("totalRevenue.txt", true)) {
            fileWriter.write("Total Revenue: " + totalRevenue + "\n");
        } 
    }

    /**
     * Handles error in case Exception erupts.
     * @param e exception.
     */
    protected void handleErrors(Exception e) {
        errorMsgHandler.log(e);
    }

}
