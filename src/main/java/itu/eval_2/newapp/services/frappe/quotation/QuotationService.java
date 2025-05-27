package itu.eval_2.newapp.services.frappe.quotation;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;

import java.util.List;

public interface QuotationService {
    /**
     * Fetch all supplier quotations from ERPNext.
     */
    List<SupplierQuotation> getAllQuotations(UserErpNext user,SupplierQuotationFilter filters) throws ERPNexException;

    /**
     * Fetch a specific Supplier Quotation by ID.
     */
    SupplierQuotation getQuotationById(UserErpNext user, String id) throws ERPNexException;

    // TODO: Update or create a new item price for a supplier
    void updateQuotation(UserErpNext user, String id, SupplierQuotation quotation) throws ERPNexException;

    SupplierQuotation getQuotationByRequestForQuotation(UserErpNext user, String rfq, String supplier) throws ERPNexException;
}