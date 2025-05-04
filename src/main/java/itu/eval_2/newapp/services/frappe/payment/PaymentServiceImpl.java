package itu.eval_2.newapp.services.frappe.payment;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.payment.PurchaseInvoicePayment;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;

@Service
public class PaymentServiceImpl extends FrappeCRUDService<PaymentEntry> implements PaymentService{

    public PaymentServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public PaymentEntry generatePayment(PurchaseInvoice invoice) throws ERPNextIntegrationException {
        PaymentEntry paymentEntry = new PurchaseInvoicePayment(invoice);
        return paymentEntry;
    }

    @Override
    public PaymentEntry validatePayment(UserErpNext user, PaymentEntry paymentEntry) throws ERPNextIntegrationException {
        return createDocument(user, paymentEntry,paymentEntry, PaymentEntry.class);
    }
    
}
