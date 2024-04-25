package se.kth.IV1350.progExe.model.DTO;

import java.time.LocalDateTime;

/**
 * ReceiptDTO class consist of a log for Sale + Payment + (time)
 * 
 * Could potentially also have direct values (e.g price) which would then be carried over from the
 * payment (price = payment.getprice()).
 */
public class ReceiptDTO {

    private final LocalDateTime time;
    private final SaleDTO saleDTO;
    private final PaymentDTO paymentDTO;

    public ReceiptDTO(SaleDTO saleDTO, PaymentDTO paymentDTO) {

        this.time = LocalDateTime.now();
        this.saleDTO = saleDTO;
        this.paymentDTO = paymentDTO;

    }

    public LocalDateTime getReceiptTime() {

        return time;
    }

    public SaleDTO getReceiptSale() {

        return saleDTO;
    }

    public PaymentDTO getReceiptPayment() {
        
        return paymentDTO;
    }


}
