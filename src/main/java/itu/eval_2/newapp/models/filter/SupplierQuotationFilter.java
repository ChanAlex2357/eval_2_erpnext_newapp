package itu.eval_2.newapp.models.filter;

import itu.eval_2.newapp.config.FrappeApiFilterList;
import lombok.Data;

@Data
public class SupplierQuotationFilter implements FrappeFilter{
    private String supplier_name;

    public SupplierQuotationFilter(String supplier){
        setSupplier_name(supplier);
    }

    @Override
    public FrappeApiFilterList getFilters() {
        return new FrappeApiFilterList(
                new String[]{"supplier_name"},
                new String[]{"like"},
                new String[]{"%"+getSupplier_name()+"%"}
            );
    }
}
