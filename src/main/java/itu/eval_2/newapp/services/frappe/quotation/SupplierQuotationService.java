package itu.eval_2.newapp.services.frappe.quotation;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;

@Service
public class SupplierQuotationService extends FrappeCRUDService<SupplierQuotation> {

    public SupplierQuotationService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
        //TODO Auto-generated constructor stub
    }
    
}
