package se.kth.IV1350.progExe.view.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.ItemPackageDTO;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;

/**
 * Prints log messagse to a file. The log file will be in the 
 * current directory an will be called log.txt
 */
public class FileLogger implements Logger {
    
    private PrintWriter logStream;

    /**
     * Creats a new instance and also creates a new log file.
     * An existing log file will be deleted. 
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true));
        }
        catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file.
     * 
     * @param message The string that will be printed ot the log file.
     */
    @Override
    public void log(String message) {
        logStream.println(message);
    }


     /**
     * Translates ItemDTO, Quantity, runningTotalCost & runningTotalVAT into String information.
     * 
     * @param itemPackageDTO contains all relevant information to be read.
     */
    public void itemPackageInfo(ItemPackageDTO itemPackageDTO) {};

    /**
     * Translates ItemDTO and Quantity into String information.
     * 
     * @param itemDTO Item to be read.
     * @param quantity Quantity to be read.
     */
    public void itemInfo(ItemDTO itemDTO, int quantity) {};

     /**
     * Translates Sale into String information.
     * 
     * @param runningTotalCost ongoing Sale current cost.
     * @param runningTotalVAT ongoing Sale current VAT.
     */
    public void saleInfo(double runningTotalCost, double runningTotalVAT) {};

     /**
     * Translates data regarding the ending of the current Sale into String.
     * 
     * @param saleDTO Sale to be read.
     * @return String of all Sale information.
     */
    public void EndSaleInfo(SaleDTO saleDTO) {};

     /**
     * Translates successful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    public void paymentSuccess(PaymentDTO paymentDTO) {};


     /**
     * Translates unsuccessful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    public void paymentFailure(PaymentDTO paymentDTO) {};

    
}
