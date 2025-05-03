package itu.eval_2.newapp.models.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SupplierQuotationItem {
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
}
