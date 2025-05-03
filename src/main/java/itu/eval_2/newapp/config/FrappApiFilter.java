package itu.eval_2.newapp.config;

import lombok.Data;

@Data
public class FrappApiFilter {
    private String fieldname;
    private String condition;
    private String vaule;
    
    public FrappApiFilter(String fieldname, String condition, String value){
        setFieldname(fieldname);
        setCondition(condition);
        setVaule(value);
    }

    public String getFilterStr(){
        return "[\""+getFieldname()+"\", \""+getCondition()+"\", \""+getVaule()+"\" ]";
         // "+get+"
    }
}
