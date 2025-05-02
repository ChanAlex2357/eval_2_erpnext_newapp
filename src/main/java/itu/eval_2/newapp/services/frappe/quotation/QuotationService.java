package itu.eval_2.newapp.services.frappe.quotation;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;

import java.util.List;

public interface QuotationService {
    /**
     * Fetch all supplier quotations from ERPNext.
     */
    List<SupplierQuotation> getAllQuotations(UserErpNext user) throws ERPNextIntegrationException;
}