package itu.eval_2.newapp.models.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.action.ChildModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemChild extends ChildModel {

    public ItemChild(String doctype){
        super(doctype);
    }
    // Item Information
    @JsonProperty("item_code")
    private String itemCode;
    @JsonProperty("item_name")
    private String itemName;
    private String description;
    @JsonProperty("item_group")
    private String itemGroup;
    private String image;
    
    // Quantity Information
    private int qty;
    @JsonProperty("received_qty")
    private int receivedQty;
    @JsonProperty("rejected_qty")
    private int rejectedQty;
    private String uom;
    @JsonProperty("conversion_factor")
    private double conversionFactor;
    @JsonProperty("stock_uom")
    private String stockUom;
    @JsonProperty("stock_qty")
    private double stockQty;
    
    // Pricing Information
    @JsonProperty("price_list_rate")
    private double priceListRate;
    @JsonProperty("base_price_list_rate")
    private double basePriceListRate;
    private double rate;
    private double amount;
    @JsonProperty("base_rate")
    private double baseRate;
    @JsonProperty("base_amount")
    private double baseAmount;
    @JsonProperty("stock_uom_rate")
    private double stockUomRate;
    
    // Discount Information
    @JsonProperty("discount_percentage")
    private double discountPercentage;
    @JsonProperty("discount_amount")
    private double discountAmount;
    @JsonProperty("distributed_discount_amount")
    private double distributedDiscountAmount;
    
    // Tax Information
    @JsonProperty("item_tax_amount")
    private double itemTaxAmount;
    @JsonProperty("item_tax_rate")
    private String itemTaxRate;
    
    // Financial Settings
    @JsonProperty("is_free_item")
    private int isFreeItem;
    @JsonProperty("apply_tds")
    private int applyTds;
    @JsonProperty("net_rate")
    private double netRate;
    @JsonProperty("net_amount")
    private double netAmount;
    @JsonProperty("base_net_rate")
    private double baseNetRate;
    @JsonProperty("base_net_amount")
    private double baseNetAmount;
    
    // Inventory Information
    private String warehouse;
    @JsonProperty("valuation_rate")
    private double valuationRate;
    @JsonProperty("weight_per_unit")
    private double weightPerUnit;
    @JsonProperty("total_weight")
    private double totalWeight;
    
    // Accounting Information
    @JsonProperty("expense_account")
    private String expenseAccount;
    @JsonProperty("cost_center")
    private String costCenter;
    
    // Document References
    @JsonProperty("purchase_order")
    private String purchaseOrder;
    @JsonProperty("po_detail")
    private String poDetail;
    private String parent;
    private String parentfield;
    private String parenttype;
    
    // Additional Flags
    @JsonProperty("use_serial_batch_fields")
    private int useSerialBatchFields;
    @JsonProperty("is_fixed_asset")
    private int isFixedAsset;
    @JsonProperty("enable_deferred_expense")
    private int enableDeferredExpense;
    @JsonProperty("allow_zero_valuation_rate")
    private int allowZeroValuationRate;
    @JsonProperty("include_exploded_items")
    private int includeExplodedItems;
    @JsonProperty("page_break")
    private int pageBreak;

    @Override
    public void update_cotnrole() {
        updateAmounts();
        validateQuantities();
    }

    private void updateAmounts() {
        this.amount = this.qty * this.rate;
        this.baseAmount = this.qty * this.baseRate;
        this.netAmount = this.qty * this.netRate;
        this.baseNetAmount = this.qty * this.baseNetRate;
    }

    private void validateQuantities() {
        if (this.receivedQty > this.qty) {
            throw new IllegalStateException("Received quantity cannot exceed ordered quantity");
        }
        if (this.rejectedQty > this.receivedQty) {
            throw new IllegalStateException("Rejected quantity cannot exceed received quantity");
        }
    }

    @Override
    public void save_controle() {
        updateAmounts();
        validateQuantities();
    }
}