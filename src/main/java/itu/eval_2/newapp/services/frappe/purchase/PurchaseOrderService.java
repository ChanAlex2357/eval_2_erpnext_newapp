package itu.eval_2.newapp.services.frappe.purchase;

import itu.eval_2.newapp.models.api.responses.ApiRessourceResponse;
import itu.eval_2.newapp.models.purchase.PurchaseOrder;

public interface PurchaseOrderService {
    public ApiRessourceResponse<PurchaseOrder> getAllPurchaseOrders();
    // public ApiRessourceResponse<PurchaseOrder> getPurchaseOrderById(String id);
}
