package itu.eval_2.newapp.models.purchase;

import itu.eval_2.newapp.models.item.ItemChild;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrderItem extends ItemChild {
    public PurchaseOrderItem(){
        super("Purchase Order Item");
    }
}