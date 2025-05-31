package itu.eval_2.newapp.models.payment;

public interface PaymentEntryReferenceable {
    public PaymentEntryReference getAsPaymentEntryReference();
    public void setPaymenteEntry(PaymentEntry paymentEntry);
}
