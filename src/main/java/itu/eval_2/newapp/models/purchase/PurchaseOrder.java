package itu.eval_2.newapp.models.purchase;
import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends FrappeDocument {
    public PurchaseOrder() {
        super("Purchase Order");
    }
    
    // Dates
    @JsonProperty("transaction_date")
    private String transactionDate;
    @JsonProperty("schedule_date")
    private String scheduleDate;

    // ******************** Custom Fields ************************

    @JsonIgnore
    private String invoiceStatus;

    // ***********************************************************
    
    // Items
    private List<PurchaseOrderItem> items;
    
    @Override
    public void update_cotnrole() {
        if (items != null) {
            items.forEach(PurchaseOrderItem::update_cotnrole);
        }
    }
    @Override
    public void save_controle() {}
}