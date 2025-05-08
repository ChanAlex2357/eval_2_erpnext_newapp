package itu.eval_2.newapp.models.item;

import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Item extends FrappeDocument {

    public Item(){
        super("Item");
    }

    private String item_code;
    private String item_name;
    private String item_group;

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
