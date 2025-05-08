package itu.eval_2.newapp.models;
import lombok.Data;

@Data
public class QuotationFormData {
    private String supplier;
    private String date;
    private String purpose;
    private String warehouse;
    
    // items
    private String[] item ;
    private String[] quantite;

    public String getItem(int i){
        return getItem()[i];
    }
}
