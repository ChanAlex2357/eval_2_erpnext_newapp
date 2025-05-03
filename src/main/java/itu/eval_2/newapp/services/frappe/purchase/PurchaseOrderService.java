package itu.eval_2.newapp.services.frappe.purchase;

import java.util.List;

import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface PurchaseOrderService {
    public List<PurchaseOrder> getAllPurchaseOrders(UserErpNext user);
    // public ApiRessourceResponse<PurchaseOrder> getPurchaseOrderById(String id);
}
