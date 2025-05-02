package itu.eval_2.newapp.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.responses.SupplierListResponse;
import itu.eval_2.newapp.models.supplier.ErpNextSupplier;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.supplier.SupplierService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierApiController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> getAll(HttpSession session){
        UserErpNext user = (UserErpNext)session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.badRequest().body("{\"message\":\"Unauthorized action\"}");
        }
        try {
            List<ErpNextSupplier> suppliers = supplierService.getAllSuppliers(user);
            return ResponseEntity.ok().body(suppliers);
        } catch (ERPNextIntegrationException e) {
            return ResponseEntity.badRequest().body(e);
        }

    } 
}
