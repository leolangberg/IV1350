package se.kth.IV1350.progExe.model.DTO;

import se.kth.IV1350.progExe.model.Sale;
import se.kth.IV1350.progExe.model.Payment;
import se.kth.IV1350.progExe.model.Receipt;

/**
 * ReceiptDTO class consist of a log for Sale + Payment + (time)
 * 
 * Could potentially also have direct values (e.g price) which would then be carried over from the
 * payment (price = payment.getprice()).
 */
public class ReceiptDTO {

    private final long time;
    private final Sale sale;
    private final Payment payment;

    public ReceiptDTO(long time, Sale sale, Payment payment) {

        this.time = time;
        this.sale = sale;
        this.payment = payment;

    }

    public ReceiptDTO(Receipt receipt) {

        this.time = receipt.getReceiptTime();
        this.sale = receipt.getReceiptSale();
        this.payment = receipt.getReceiptPayment();
    }

    public long getReceiptTime() {

        return time;
    }

    public Sale getReceiptSale() {

        return sale;
    }

    public Payment getReceiptPayment() {
        
        return payment;
    }


}
