package itu.eval_2.newapp.models.api.requests;

import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.quotation.SupplierQuotationItem;
import lombok.Data;

@Data
public class UpdateQuotationRequest implements RequestModel{
    private SupplierQuotationItem[] items;
    private int docstatus;
    public UpdateQuotationRequest(SupplierQuotation quotation) {
        setItems(quotation.getItems());
        setDocstatus(1);
    }
}
