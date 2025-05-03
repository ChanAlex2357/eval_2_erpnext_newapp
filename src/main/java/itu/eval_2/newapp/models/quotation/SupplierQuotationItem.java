package itu.eval_2.newapp.models.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.models.action.FrappeModel;
import lombok.Data;

@Data
public class SupplierQuotationItem implements FrappeModel{
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

    private double rate;

    private double amount;

    private String warehouse;

    @JsonProperty("parent")
    private String parentQuotation;

    private int idx;

    @Override
    public void cotnrole() {
        updateAmount();
    }

    private void updateAmount(){
        setAmount( getQty() * getRate());
    }
}
