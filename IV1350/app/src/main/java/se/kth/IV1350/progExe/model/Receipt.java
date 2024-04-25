package se.kth.IV1350.progExe.model;

import se.kth.IV1350.progExe.model.DTO.ReceiptDTO;


/**
 * Receipt class consist of a log for Sale + Payment + (time)
 * 
 * Could potentially also have direct values (e.g price) which would then be carried over from the
 * payment (price = payment.getprice()).
 */
public class Receipt {

    private long time;
    private Sale sale;
    private Payment payment;

    public Receipt(ReceiptDTO receiptDTO) {

        this.time = receiptDTO.getReceiptTime();
        this.sale = receiptDTO.getReceiptSale();
        this.payment = receiptDTO.getReceiptPayment();

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
