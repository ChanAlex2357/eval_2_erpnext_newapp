package itu.eval_2.newapp.services.frappe.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeWebService;

@Service
public class PurchaseOrderServiceImpl extends FrappeWebService<PurchaseOrder> implements PurchaseOrderService {

    public PurchaseOrderServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders(UserErpNext user, String supplier) throws ERPNexException {
        String methodPath = "eval_app.api.get_purchase_orders_with_invoices";

        Map<String , String> body = new HashMap<>();
        body.put("supplier_name",supplier);

        ApiResponse<List<PurchaseOrder>> result = callApiListResponseMethod(user, methodPath, HttpMethod.GET, body, PurchaseOrder.class);

        List<PurchaseOrder> resultData = result.getData();
        return resultData;
    }

}
