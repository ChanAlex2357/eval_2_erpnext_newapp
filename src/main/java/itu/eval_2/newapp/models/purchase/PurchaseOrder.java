package itu.eval_2.newapp.models.purchase;
import itu.eval_2.newapp.models.action.FrappeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends FrappeModel {
    // Dates
    @JsonProperty("transaction_date")
    private String transactionDate;
    @JsonProperty("schedule_date")
    private String scheduleDate;
    
    // Items
    private List<PurchaseOrderItem> items;
    
    @Override
    public void cotnrole() {
        if (items != null) {
            items.forEach(PurchaseOrderItem::cotnrole);
        }
    }
}