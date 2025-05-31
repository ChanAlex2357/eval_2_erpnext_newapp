package itu.eval_2.newapp.models.api.requests;

import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import lombok.Data;

@Data
public class UpdatePriceRequest implements RequestModel{
    private double paid_amount;
    private String doc_name;

    public UpdatePriceRequest(PaymentEntry paymentEntry, PurchaseInvoice invoice){
        setPaid_amount(paymentEntry.getPaidAmount());
        setDoc_name(invoice.getName());
    }
}
