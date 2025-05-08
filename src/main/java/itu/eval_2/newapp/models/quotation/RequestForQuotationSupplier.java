package itu.eval_2.newapp.models.quotation;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestForQuotationSupplier extends FrappeDocument{

    private String supplier_name;
    private String supplier;


    public RequestForQuotationSupplier() {
        super("Request for Quotation Supplier");
    }

    @Override
    public void update_cotnrole() {
        // TODO Auto-generated method stub
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
    }
     
}
