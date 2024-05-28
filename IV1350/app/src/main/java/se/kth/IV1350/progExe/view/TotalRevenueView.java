package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.model.RevenueObserver;

/**
 * Abstract class that uses the GoF 'Template' pattern.
 * 
 * This abstract class basically acts as a skeleton superclass for 'real' subclasses
 * that are concrete (not abstract).
 */
public abstract class TotalRevenueView implements RevenueObserver {

    /**
     * Variable totalRevenue is accessible for all subclasses extending this super-class
     * as it is declared as 'protected'.
     */
    protected double totalRevenue;

    /**
     * The method defined in the observer interface.
     * @param revenue price of finished Sale.
     */
    public void newRevenue(double revenue) {
        calculateTotalIncome(revenue);
        showTotalIncome();
    }

    /**
	 * Private method for how the TotalIncome is calculated.
	 * @param revenue income recieved from latest Sale.
	 */
    private void calculateTotalIncome(double revenue) {
        this.totalRevenue += revenue;
    }

	/**
	 * Method for displaying the Total Income or Revenue so far.
	 * In case an error occurs an exception will be catched and handled accordingly.
	 */
    private void showTotalIncome() {
        try {
            doShowTotalIncome();
        } catch(Exception e) {
            handleErrors(e);  
        }
    }

	/**
	 * Abstract method to be declared by subclasses that actually displays current total Revenue.
	 * @throws Exception in case operation fails.
	 */
    protected abstract void doShowTotalIncome() throws Exception;

	/**
	 * Abstract method to be declared by subclasses that handles potential Exceptions being thrown.
	 * @param e exception.
	 */
    protected abstract void handleErrors(Exception e);

}
