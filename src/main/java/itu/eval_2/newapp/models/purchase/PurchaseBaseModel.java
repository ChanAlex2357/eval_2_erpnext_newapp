package itu.eval_2.newapp.models.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class PurchaseBaseModel extends FrappeDocument {

    private String supplier;
    @JsonProperty("supplier_name")
    private String supplierName;
    private String company;
    
    @JsonProperty("total_qty")
    private int totalQty;

    @JsonProperty("base_total")
    private double baseTotal;

    @JsonProperty("base_net_total")
    private double baseNetTotal;

    private double total;

    @JsonProperty("net_total")
    private double netTotal;

    @JsonProperty("grand_total")
    private double grandTotal;

    @JsonProperty("base_grand_total")
    private double baseGrandTotal;
    
    // Financials
    private String currency;
    @JsonProperty("conversion_rate")
    private double conversionRate;
    
    // Pricing
    @JsonProperty("buying_price_list")
    private String buyingPriceList;
    

    @JsonProperty("total_net_weight")
    private double totalNetWeight;
    
    // Taxes
    @JsonProperty("tax_category")
    private String taxCategory;

    public PurchaseBaseModel(String doctype) {
        super(doctype);
    }
}
