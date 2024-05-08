package se.kth.IV1350.progExe.view.StringHandler;

import se.kth.IV1350.progExe.model.DTO.ItemDTO;
import se.kth.IV1350.progExe.model.DTO.ItemPackageDTO;
import se.kth.IV1350.progExe.model.DTO.PaymentDTO;
import se.kth.IV1350.progExe.model.DTO.SaleDTO;

/**
 * Specifies an object that can print to a log. This interface does not 
 * handle log locations, it is up to the implementing
 * class to decide where the log is.
 */
public interface logger {
    
    /**
     * The specified message is printed to the log.
     * 
     * @param message The message that will be logged.
     */
    void log(String message);

     /**
     * Translates ItemDTO, Quantity, runningTotalCost & runningTotalVAT into String information.
     * 
     * @param itemPackageDTO contains all relevant information to be read.
     */
    void itemPackageInfo(ItemPackageDTO itemPackageDTO);

     /**
     * Translates ItemDTO and Quantity into String information.
     * 
     * @param itemDTO Item to be read.
     * @param quantity Quantity to be read.
     */
   void itemInfo(ItemDTO itemDTO, int quantity);

    /**
     * Translates Sale into String information.
     * 
     * @param runningTotalCost ongoing Sale current cost.
     * @param runningTotalVAT ongoing Sale current VAT.
     */
    public void saleInfo(double runningTotalCost, double runningTotalVAT);

     /**
     * Translates data regarding the ending of the current Sale into String.
     * 
     * @param saleDTO Sale to be read.
     * @return String of all Sale information.
     */
    void EndSaleInfo(SaleDTO saleDTO);

     /**
     * Translates successful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    void paymentSuccess(PaymentDTO paymentDTO);


     /**
     * Translates unsuccessful payment into String information.
     * 
     * @param paymentDTO payment to be read.
     */
    void paymentFailure(PaymentDTO paymentDTO);


}
