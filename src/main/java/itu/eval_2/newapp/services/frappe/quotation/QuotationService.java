package itu.eval_2.newapp.services.frappe.quotation;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;

import java.util.List;

public interface QuotationService {
    /**
     * Fetch all supplier quotations from ERPNext.
     */
    List<SupplierQuotation> getAllQuotations(UserErpNext user,SupplierQuotationFilter filters) throws ERPNextIntegrationException;

    /**
     * Fetch a specific Supplier Quotation by ID.
     */
    SupplierQuotation getQuotationById(UserErpNext user, String id) throws ERPNextIntegrationException;

    // TODO: Update or create a new item price for a supplier
    void updateQuotation(UserErpNext user, String id, SupplierQuotation quotation) throws ERPNextIntegrationException;

    SupplierQuotation getQuotationByRequestForQuotation(UserErpNext user, RequestForQuotation rfq, String supplier) throws ERPNextIntegrationException;
}