package itu.eval_2.newapp.models.action;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import itu.eval_2.newapp.annotations.date.ErpNextDateTime;
import lombok.Data;

@Data
public abstract class FrappeDocument {
    // Document metadata
    private String name;
    private String owner;
    private String doctype;
    
    public FrappeDocument(String doctype){
        setDoctype(doctype);
    }
    
    @ErpNextDateTime
    private LocalDateTime creation;
    
    @ErpNextDateTime
    private LocalDateTime modified;
    
    @JsonProperty("modified_by")
    private String modifiedBy;
    
    // Document status
    private int docstatus;
    private int idx;
    private String title;
    private String status;

    @JsonProperty("naming_series")
    private String namingSeries;

    abstract public void update_cotnrole();
    abstract public void save_controle();
}
