package itu.eval_2.newapp.models.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.item.ItemChild;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItem extends ItemChild {
    // Quantities
    @JsonProperty("received_qty")
    private double receivedQty;
}