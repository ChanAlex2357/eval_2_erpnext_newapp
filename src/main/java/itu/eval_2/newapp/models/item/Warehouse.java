package itu.eval_2.newapp.models.item;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;

@Data
public class Warehouse extends FrappeDocument{
    public Warehouse () {
        super("Warehouse");
    }

    private String warehouse_name;

    @Override
    public void update_cotnrole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update_cotnrole'");
    }

    @Override
    public void save_controle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save_controle'");
    }
}
