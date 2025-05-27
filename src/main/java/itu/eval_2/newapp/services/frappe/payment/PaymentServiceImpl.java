package itu.eval_2.newapp.services.frappe.payment;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.api.requests.GetPaymentEntryRequest;
import itu.eval_2.newapp.models.api.requests.UpdatePriceRequest;
import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeWebService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl extends FrappeWebService<PaymentEntry> implements PaymentService{

    public PaymentServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public PaymentEntry generatePayment(UserErpNext user, PurchaseInvoice invoice) throws ERPNexException {
        String methodPath = "erpnext.accounts.doctype.payment_entry.payment_entry.get_payment_entry";
        PaymentEntry paymentEntry = callMethod(user, methodPath, HttpMethod.GET, new GetPaymentEntryRequest(invoice), PaymentEntry.class);
        return paymentEntry;
    }

    @Override
    public PaymentEntry validatePayment(UserErpNext user, PaymentEntry paymentEntry, PurchaseInvoice invoice) throws ERPNexException {
        paymentEntry.setDocstatus(1);
        String methodPath = "eval_app.api.make_payment_entry";
        log.info("STARTING SAVING PAYMENT WITH AMOUNT : "+paymentEntry.getPaidAmount());

        UpdatePriceRequest updatePriceRequest = new UpdatePriceRequest(paymentEntry, invoice);
        return callMethod(user, methodPath, HttpMethod.POST, updatePriceRequest, PaymentEntry.class);
        // return createDocument(user, paymentEntry,paymentEntry, PaymentEntry.class);
    }
}
