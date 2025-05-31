package itu.eval_2.newapp.models;

import java.sql.Date;

import lombok.Data;

@Data
public class QuotationFormDataItem {
    private String item_code;
    private Date required_by;
    private double quantite;
    private String warehouse;
}
