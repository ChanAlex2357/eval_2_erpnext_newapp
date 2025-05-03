package itu.eval_2.newapp.services.frappe.purchase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;

@Service
public class PurchaseOrderServiceImpl extends FrappeCRUDService<PurchaseOrder> implements PurchaseOrderService {
    public PurchaseOrderServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders(UserErpNext user) throws ERPNextIntegrationException{
        return getAllDocuments(user, null,PurchaseOrder.class);
    }

    @Override
    public String getDoctype(){
        return "Purchase Order";
    }
}
