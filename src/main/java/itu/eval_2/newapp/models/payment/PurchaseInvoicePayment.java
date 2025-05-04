package itu.eval_2.newapp.models.payment;

import java.time.LocalDateTime;

import itu.eval_2.newapp.models.purchase.PurchaseInvoice;

public class PurchaseInvoicePayment extends PaymentEntry {
    public PurchaseInvoicePayment(PurchaseInvoice invoice) {
        super();
        this.setPostingDate(LocalDateTime.now().toString());
        // Party config
        this.setPartyType("Supplier");
        this.setParty(invoice.getSupplier());
        this.setPartyName(invoice.getSupplierName());
        this.setPaidAmount(invoice.getGrandTotal());
    }
}
