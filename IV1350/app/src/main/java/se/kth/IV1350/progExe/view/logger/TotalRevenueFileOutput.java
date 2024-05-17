// src/main/java/se/kth/IV1350/progExe/view/logger/TotalRevenueFileOutput.java
package se.kth.IV1350.progExe.view.logger;

import se.kth.IV1350.progExe.model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;

public class TotalRevenueFileOutput implements RevenueObserver {
    private double totalRevenue;

    @Override
    public void newRevenue(double revenue) {
        totalRevenue += revenue;
        try (FileWriter fileWriter = new FileWriter("totalRevenue.txt", true)) {
            fileWriter.write("Total Revenue: " + totalRevenue + "\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
