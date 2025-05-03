package itu.eval_2.newapp.models.purchase;
import itu.eval_2.newapp.models.action.FrappeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder extends FrappeModel {
    
    // References
    @JsonProperty("naming_series")
    private String namingSeries;
    private String supplier;
    @JsonProperty("supplier_name")
    private String supplierName;
    private String company;
    
    // Dates
    @JsonProperty("transaction_date")
    private String transactionDate;
    @JsonProperty("schedule_date")
    private String scheduleDate;
    
    // Financials
    private String currency;
    @JsonProperty("conversion_rate")
    private double conversionRate;
    
    // Pricing
    @JsonProperty("buying_price_list")
    private String buyingPriceList;
    
    // Quantities
    @JsonProperty("total_qty")
    private double totalQty;
    @JsonProperty("total_net_weight")
    private double totalNetWeight;
    
    // Amounts
    @JsonProperty("base_total")
    private double baseTotal;
    @JsonProperty("base_net_total")
    private double baseNetTotal;
    private double total;
    @JsonProperty("net_total")
    private double netTotal;
    
    // Taxes
    @JsonProperty("tax_category")
    private String taxCategory;
    
    // Totals
    @JsonProperty("base_grand_total")
    private double baseGrandTotal;
    @JsonProperty("grand_total")
    private double grandTotal;
    
    // Items
    private List<PurchaseOrderItem> items;
    
    @Override
    public void cotnrole() {
        if (items != null) {
            items.forEach(PurchaseOrderItem::cotnrole);
        }
    }
}