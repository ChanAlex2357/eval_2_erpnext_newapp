package itu.eval_2.newapp.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FrappeApiFilterList {
    private FrappApiFilter[] filters;
    public FrappeApiFilterList(String[] fieldnames,String[] operators,String[] values){
        List<FrappApiFilter> filters = new ArrayList<>();

        for (int i = 0; i < fieldnames.length; i++) {
            if (values[i] == null || values[i] == "") {
                continue;
            }
            filters.add( new FrappApiFilter(fieldnames[i], operators[i], values[i]));
        }
        
        setFilters( filters.toArray(new FrappApiFilter[0]));   
    }
}