package itu.eval_2.newapp.services.frappe.invoice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;


@Service
public class PurchaseInvoiceServiceImpl extends FrappeCRUDService<PurchaseInvoice> implements PurchaseInvoiceService{ 

    public PurchaseInvoiceServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<PurchaseInvoice> getAllInvoices(UserErpNext user) throws ERPNextIntegrationException{
        return getAllDocuments(user, new PurchaseInvoice(),PurchaseInvoice.class);
    }

    @Override
    public PurchaseInvoice getInvoinceById(UserErpNext user, String id) throws ERPNextIntegrationException {
        return getDocumentById(user, id, new PurchaseInvoice(), PurchaseInvoice.class);
    }
    
}
