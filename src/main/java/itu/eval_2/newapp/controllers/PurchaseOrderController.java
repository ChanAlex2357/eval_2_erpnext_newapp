package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.models.user.UserErpNext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class PurchaseOrderController {
    // TODO: afficher page list de commande
    public String orderList(HttpSession session , Model model){
        UserErpNext user = (UserErpNext)session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }
        // Fetch list data
        return "/orders/list";
    }
    // TODO: afficher page details de commande
    // TODO: payer une commande => creation d'une facture de payement
    // TODO: recevoir une commande => creation d'une purchase receipt => creation d'un mouvement de stock
}
