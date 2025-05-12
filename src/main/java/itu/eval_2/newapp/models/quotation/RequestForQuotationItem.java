package itu.eval_2.newapp.models.quotation;

import itu.eval_2.newapp.models.item.ItemChild;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestForQuotationItem extends ItemChild {
    private int quantite;
    private String warehouse;
    private String uom = "Unit";
    private String conversion_factor = "1";
    public RequestForQuotationItem(){
        super("RequestForQuotationItem");
    }
}
