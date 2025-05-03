package itu.eval_2.newapp.models.api.requests;

import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.quotation.SupplierQuotationItem;

public class UpdateQuotationRequest implements RequestModel{
    private SupplierQuotationItem[] items;

    public UpdateQuotationRequest(SupplierQuotation quotation) {
        setItems(quotation.getItems());   
    }


    public void setItems(SupplierQuotationItem[] items) {
        this.items = items;
    }
    public SupplierQuotationItem[] getItems() {
        return items;
    }
}
