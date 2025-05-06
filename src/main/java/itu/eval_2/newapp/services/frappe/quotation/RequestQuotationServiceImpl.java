package itu.eval_2.newapp.services.frappe.quotation;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;

@Service
public class RequestQuotationServiceImpl extends FrappeCRUDService<RequestForQuotation> implements RequestQuotationService{

    public RequestQuotationServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<RequestForQuotation> getAllRequestForQuotation(UserErpNext user, String supplier) throws ERPNextIntegrationException {

        ApiResponse<List<RequestForQuotation>> response = callApiListResponseMethod(user,"eval_app.api.get_request_quotation_list",HttpMethod.GET,null,RequestForQuotation.class);
        return response.getData();
    }
    
}
