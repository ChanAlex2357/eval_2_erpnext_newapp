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
        this.setCompany(invoice.getCompany());
        
        // Amount setup
        this.setPaidAmount(invoice.getGrandTotal());
        this.setPaidAmountAfterTax(invoice.getBaseGrandTotal());
        this.setSourceExchangeRate(1);
        this.setBasePaidAmount(invoice.getBaseGrandTotal());

        // Payment reference
        this.setPaidTo(invoice.getCrediTo()); // Example value, adjust as needed
        this.setPaidToAccountCurrency(invoice.getCurrency());
        this.setPaidToAccountType("Payable");
        
        // Add invoice as reference child
        addReference(invoice.getAsPaymentEntryReference());
        
        // Set the payment entry to pay
        setToPayState();
    }
}
