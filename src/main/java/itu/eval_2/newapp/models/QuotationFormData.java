package itu.eval_2.newapp.models;

import java.sql.Date;

import lombok.Data;

@Data
public class QuotationFormData {
    private String supplier_name;
    private Date date;
    private String purpose;
    private QuotationFormDataItem[] items;

}
