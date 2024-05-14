package se.kth.IV1350.progExe.model.DTO;

import java.time.LocalDateTime;

/**
 * ReceiptDTO class represent the receipt of a Sale and Payment.
 * @time show current date and time of purchase.
 * @saleDTO represents finalized Sale.
 * @paymentDTO represents finalized Payment.
 */
public class ReceiptDTO {

    private final LocalDateTime time;
    private final SaleDTO saleDTO;
    private final PaymentDTO paymentDTO;


    /**
     * Constructs a new ReceiptDTO object.
     * 
     * This constructor initializes the ReceiptDTO with the provided saleDTO and paymentDTO
     * and sets the time to the current time.
     *
     * @param saleDTO The SaleDTO associated with this receipt.
     * @param paymentDTO The PaymentDTO associated with this receipt.
     */
    public ReceiptDTO(SaleDTO saleDTO, PaymentDTO paymentDTO) {

        this.time = LocalDateTime.now();
        this.saleDTO = saleDTO;
        this.paymentDTO = paymentDTO;

    }

    /**
     * Retrieves the time.
     * 
     * @return The time of this ReceiptDTO.
     */
    public LocalDateTime getReceiptTime() { return time; }

    /**
     * Retrieves SaleDTO.
     * 
     * @return The saleDTO of this ReceiptDTO.
     */
    public SaleDTO getReceiptSale() { return saleDTO; }

    /**
     * Retrieves PaymentDTO.
     * 
     * @return The paymentDTO of this ReceiptDTO.
     */
    public PaymentDTO getReceiptPayment() { return paymentDTO; }
    
}
