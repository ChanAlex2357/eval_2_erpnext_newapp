package itu.eval_2.newapp.models.quotation;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestForQuotation extends FrappeDocument{
    private int ref;
    public RequestForQuotation() {
        super("Request for Quotation");
    }

    @Override
    public void update_cotnrole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update_cotnrole'");
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save_controle'");
    }
    
}
