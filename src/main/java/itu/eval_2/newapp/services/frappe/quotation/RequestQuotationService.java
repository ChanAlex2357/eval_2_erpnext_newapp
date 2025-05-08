package itu.eval_2.newapp.services.frappe.quotation;

import java.util.List;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.QuotationFormData;
import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface RequestQuotationService {
    public List<RequestForQuotation> getAllRequestForQuotation(UserErpNext user, String supplier) throws ERPNextIntegrationException;
    public void createRequestForQuotation(UserErpNext user, QuotationFormData formData) throws ERPNextIntegrationException;
}
