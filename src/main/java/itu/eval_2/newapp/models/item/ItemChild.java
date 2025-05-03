package itu.eval_2.newapp.models.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.action.FrappeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemChild extends FrappeModel {
    private String name;
    @JsonProperty("item_code")
    private String itemCode;

    @JsonProperty("item_name")
    private String itemName;

    private String description;

    @JsonProperty("item_group")
    private String itemGroup;

    private String image;
    private int qty;

    @JsonProperty("stock_uom")
    private String stockUom;

    private String warehouse;

    // Pricing
    private double rate;
    private double amount;
    @JsonProperty("base_rate")
    private double baseRate;
    @JsonProperty("base_amount")
    private double baseAmount;
    
    // Document relationship
    private String parent;
    private String parentfield;
    private String parenttype;
    private int idx;
        

    @Override
    public void cotnrole() {
        updateAmount();
    }

    private void updateAmount(){
        this.amount = this.qty * this.rate;
        this.baseAmount = this.qty * this.baseRate;
    }
}
