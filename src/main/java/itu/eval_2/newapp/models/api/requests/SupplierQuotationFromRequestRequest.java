package itu.eval_2.newapp.models.api.requests;

import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import lombok.Data;

@Data
public class SupplierQuotationFromRequestRequest implements RequestModel{
    private String rfq_name;
    private String supplier;
    public SupplierQuotationFromRequestRequest(RequestForQuotation rfq, String supplier){
        setRfq_name(rfq.getName());
        setSupplier(supplier);
    }
}
