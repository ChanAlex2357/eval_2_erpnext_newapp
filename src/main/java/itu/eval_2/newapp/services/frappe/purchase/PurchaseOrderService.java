package itu.eval_2.newapp.services.frappe.purchase;

import java.util.List;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;

public interface PurchaseOrderService {
    public List<PurchaseOrder> getAllPurchaseOrders(UserErpNext user,String supplier) throws ERPNextIntegrationException;
    // public ApiRessourceResponse<PurchaseOrder> getPurchaseOrderById(String id);
}
