package itu.eval_2.newapp.models.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ChildModel extends FrappeModel{
        // Document relationship
        private String parent;
        private String parentfield;
        private String parenttype;
}
