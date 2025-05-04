package itu.eval_2.newapp.models.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.item.ItemChild;

public class PurchaseInvoiceItem extends ItemChild{

    @JsonProperty("po_details")
    private String poDetails;

    @JsonProperty("purchase_order")
    private String purchaseOrder;
    
    public PurchaseInvoiceItem() {
        super("Purchase Invoice Item");
    }
    
}
