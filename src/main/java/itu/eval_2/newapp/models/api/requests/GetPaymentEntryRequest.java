package itu.eval_2.newapp.models.api.requests;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;

@Data
public class GetPaymentEntryRequest implements RequestModel{
    private String dt;
    private String dn;

    public GetPaymentEntryRequest(FrappeDocument doc){
        setDt(doc.getDoctype());
        setDn(doc.getName());
    }
}
