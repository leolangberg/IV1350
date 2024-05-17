
package se.kth.IV1350.progExe.model;

/**
 * Interface for classes that wants to observe the revenue.
 */
public interface RevenueObserver {
    void newRevenue(double revenue);
}
