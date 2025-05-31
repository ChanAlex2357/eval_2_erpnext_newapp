package itu.eval_2.newapp.config;

import lombok.Data;

@Data
public class FrappApiFilter {
    private String fieldname;
    private String operator;
    private String vaule;
    
    public FrappApiFilter(String fieldname, String opt, String value){
        setFieldname(fieldname);
        setOperator(opt);
        setVaule(value);
    }

    public String getFilterStr(){
        return "[\""+getFieldname()+"\", \""+getOperator()+"\", \""+getVaule()+"\" ]";
         // "+get+"
    }
}
