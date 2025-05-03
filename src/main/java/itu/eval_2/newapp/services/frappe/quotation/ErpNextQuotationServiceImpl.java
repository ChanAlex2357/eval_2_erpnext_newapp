package itu.eval_2.newapp.services.frappe.quotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.requests.UpdateQuotationRequest;
import itu.eval_2.newapp.models.api.responses.SingleSupplierQuotationResponse;
import itu.eval_2.newapp.models.api.responses.SupplierQuotationListResponse;
import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;
import itu.eval_2.newapp.utils.http.HeadersUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ErpNextQuotationServiceImpl extends FrappeCRUDService<SupplierQuotation> implements QuotationService {


    public ErpNextQuotationServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig,restTemplate);
    }

    @Override
    public String getDoctype(){
        return "Supplier Quotation";
    }

    @Override
    public List<SupplierQuotation> getAllQuotations(UserErpNext user, SupplierQuotationFilter filter) throws ERPNextIntegrationException {
        return getAllDocuments(user, filter, SupplierQuotation.class);
    }

    public List<SupplierQuotation> getAllQuotations(UserErpNext user) throws ERPNextIntegrationException {
        return getAllQuotations(user, null);
    }

    @Override
    public SupplierQuotation getQuotationById(UserErpNext user, String id) throws ERPNextIntegrationException {
        return getDocumentById(user, id, SupplierQuotation.class);
    }

    @Override

    public void updateQuotation(UserErpNext user, String id, SupplierQuotation quotation) throws ERPNextIntegrationException 
    {

    }
}