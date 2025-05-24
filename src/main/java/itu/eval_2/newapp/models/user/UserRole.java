package itu.eval_2.newapp.models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import itu.eval_2.newapp.models.action.FrappeDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends FrappeDocument {

    private String role;
    private String parent;
    @JsonProperty("parentfield")
    private String parentField;
    @JsonProperty("parenttype")
    private String parentType;

    public UserRole(){
        super("Has Role");
    }

    @Override
    public void save_controle() {}

    @Override
    public void update_cotnrole() {}
}
