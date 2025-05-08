package itu.eval_2.newapp.models.quotation;

import java.sql.Date;

import itu.eval_2.newapp.models.QuotationFormData;
import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RequestForQuotation extends FrappeDocument{
    private int ref;
    private Date trasaction_date;
    public RequestForQuotation() {
        super("Request for Quotation");
    }

    @Override
    public void update_cotnrole() {
        // TODO Auto-generated method stub
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
    }

    private String purpose;
    private String target_warehouse;
    private RequestForQuotationItem[] items;
    private RequestForQuotationSupplier[] suppliers;
    private String message_for_supplier = "a simple message";
    public void init(QuotationFormData formData){
        // setTrasaction_date(formData.getDate());
        setPurpose(formData.getPurpose());
        setTarget_warehouse(formData.getWarehouse());

        int n = formData.getItem().length;
        this.items = new RequestForQuotationItem[n];

        for (int i = 0; i < items.length; i++) {
            items[i] = new RequestForQuotationItem();
            items[i].setItemCode( formData.getItem()[i]);
            items[i].setWarehouse(target_warehouse);
            items[i].setQuantite( Integer.parseInt(formData.getQuantite()[i]));
            items[i].setQty( Integer.parseInt(formData.getQuantite()[i]));
        }

        this.suppliers = new RequestForQuotationSupplier[1];
        suppliers[0] = new RequestForQuotationSupplier();
        suppliers[0].setSupplier_name(formData.getSupplier());
        suppliers[0].setSupplier(formData.getSupplier());
    }
    
}
