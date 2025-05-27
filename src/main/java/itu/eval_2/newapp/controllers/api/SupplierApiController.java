package itu.eval_2.newapp.controllers.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.supplier.ErpNextSupplier;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.supplier.SupplierService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/suppliers")
@Slf4j
public class SupplierApiController {
    
    @Autowired
    private final SupplierService supplierService;

    public SupplierApiController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSuppliers(HttpSession session) {
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
            // 2. Fetch suppliers
            log.info("Fetching suppliers for user: {}", user.getUsername());
            List<ErpNextSupplier> suppliers = supplierService.getAllSuppliers(user);
            
            // 3. Return successful response
            return ResponseEntity.ok()
                .body(Map.of(
                    "status", "success",
                    "data", suppliers,
                    "count", suppliers.size()
                ));
                
        } catch (ERPNexException e) {
            // 4. Handle ERPNext-specific errors
            log.error("ERPNext integration failed for user {}: {}", user.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of(
                    "status", "error",
                    "message", "Supplier service unavailable",
                    "details", e.getMessage()
                ));
        } catch (Exception e) {
            // 5. Handle unexpected errors
            log.error("Unexpected error fetching suppliers for user {}: {}", user.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                    "status", "error",
                    "message", "Internal server error"
                ));
        }
    }
}