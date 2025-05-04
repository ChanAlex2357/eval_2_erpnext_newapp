package itu.eval_2.newapp.services.frappe.payment;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface PaymentService {
    public PaymentEntry generatePayment(PurchaseInvoice invoice) throws ERPNextIntegrationException;
    public PaymentEntry validatePayment(UserErpNext user, PaymentEntry paymentEntry) throws ERPNextIntegrationException;
}
