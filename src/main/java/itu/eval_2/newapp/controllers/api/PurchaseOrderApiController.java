package itu.eval_2.newapp.controllers.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.purchase.PurchaseOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class PurchaseOrderApiController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<?> fetchPurchaseOrders(@RequestParam("supplier") String supplier,HttpSession session){
        // 1. Authentication check
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null || !user.isAuthenticated()) {
            log.warn("Unauthorized access attempt to suppliers API");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                    "status", "error",
                    "message", "Authentication required"
                ));
        }
        try {
            List<PurchaseOrder> orders = purchaseOrderService.getAllPurchaseOrders(user,supplier);
            Map<String,Object> body = new HashMap<>();
            body.put("data", orders);
            return ResponseEntity.ok().body(body);
        } catch (ERPNexException e) {
            if (e.getResponse() != null) {
                return e.getResponse();
            }
            return ResponseEntity.badRequest().body(e);
        }
    }
}
