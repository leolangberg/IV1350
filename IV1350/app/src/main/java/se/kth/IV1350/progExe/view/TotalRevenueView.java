// src/main/java/se/kth/IV1350/progExe/view/TotalRevenueView.java
package se.kth.IV1350.progExe.view;

import se.kth.IV1350.progExe.model.RevenueObserver;

public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue;

    @Override
    public void newRevenue(double revenue) {
        totalRevenue += revenue;
        System.out.println("Total Revenue: " + totalRevenue);
    }
}
