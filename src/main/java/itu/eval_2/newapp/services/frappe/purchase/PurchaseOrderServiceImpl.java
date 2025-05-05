package itu.eval_2.newapp.services.frappe.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
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

        String methodPath = "eval_app.api.get_purchase_orders_with_invoices";
        ApiResponse<PurchaseOrder> apiResponse = new ApiResponse<>();
        callApiResponseMethod(user, methodPath, HttpMethod.GET, null, PurchaseOrder.class);
        return getAllDocuments(user, new  PurchaseOrder(),PurchaseOrder.class);
    }

}
