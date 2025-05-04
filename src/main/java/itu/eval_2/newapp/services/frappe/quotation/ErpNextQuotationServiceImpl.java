package itu.eval_2.newapp.services.frappe.quotation;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.requests.UpdateQuotationRequest;
import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class ErpNextQuotationServiceImpl extends FrappeCRUDService<SupplierQuotation> implements QuotationService {
    public ErpNextQuotationServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig,restTemplate);
    }

    @Override
    public List<SupplierQuotation> getAllQuotations(UserErpNext user, SupplierQuotationFilter filter) throws ERPNextIntegrationException {
        return getAllDocuments(user, new SupplierQuotation(),filter, SupplierQuotation.class);
    }

    @Override
    public SupplierQuotation getQuotationById(UserErpNext user, String id) throws ERPNextIntegrationException {
        return getDocumentById(user, id, new SupplierQuotation(), SupplierQuotation.class);
    }

    @Override
    public void updateQuotation(UserErpNext user, String id, SupplierQuotation quotation) throws ERPNextIntegrationException 
    {
        updateDocument(user, id, quotation, new UpdateQuotationRequest(quotation) , SupplierQuotation.class);
    }
}