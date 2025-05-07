package itu.eval_2.newapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itu.eval_2.newapp.models.purchase.PurchaseOrder;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.purchase.PurchaseOrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@Slf4j
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;
    // TODO: afficher page list de commande
    @GetMapping
    public String orderList(HttpSession session , Model model , @RequestParam(required = false,name = "supplier") String supplier){
        UserErpNext user = (UserErpNext)session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders(user, supplier);
            log.info("= = = DATA ORDERS = = = ",purchaseOrders);
            model.addAttribute("orders", purchaseOrders);
        } catch (Exception e) {
            model.addAttribute("error", "Error while fetching the purcase order : "+e.getMessage());
        }
        // Fetch list data
        return "/orders/list";
    }
    // TODO: afficher page details de commande
    // TODO: payer une commande => creation d'une facture de payement
    // TODO: recevoir une commande => creation d'une purchase receipt => creation d'un mouvement de stock
}
