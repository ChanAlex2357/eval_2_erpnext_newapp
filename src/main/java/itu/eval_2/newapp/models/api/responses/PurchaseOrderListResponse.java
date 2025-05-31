package itu.eval_2.newapp.models.api.responses;

import java.util.List;

import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import lombok.Data;

@Data
public class PurchaseOrderListResponse implements ResponseModel{
    public List<PurchaseOrder> orders;
}
