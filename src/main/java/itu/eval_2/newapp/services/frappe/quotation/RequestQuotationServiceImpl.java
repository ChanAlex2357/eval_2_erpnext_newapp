package itu.eval_2.newapp.services.frappe.quotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.QuotationFormData;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeWebService;

@Service
public class RequestQuotationServiceImpl extends FrappeWebService<RequestForQuotation> implements RequestQuotationService{

    public RequestQuotationServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<RequestForQuotation> getAllRequestForQuotation(UserErpNext user, String supplier) throws ERPNexException {
        Map<String,String> body = new HashMap<>();
        body.put("supplier", supplier);
        ApiResponse<List<RequestForQuotation>> response = callApiListResponseMethod(user,"eval_app.api.get_request_quotation_list",HttpMethod.GET,body,RequestForQuotation.class);
        return response.getData();
    }

    @Override
    public void createRequestForQuotation(UserErpNext user, QuotationFormData formData) throws ERPNexException {
        RequestForQuotation rfq = new RequestForQuotation();
        rfq.init(formData);
        rfq.setDocstatus(1);
        createDocument(user, rfq, RequestForQuotation.class);
    }
    
}
