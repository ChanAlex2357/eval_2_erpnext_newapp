package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.user.UserErpNext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private ApiConfig apiConfig;

    @GetMapping
    public String index(HttpSession session,Model model){
        UserErpNext u = (UserErpNext) session.getAttribute("user");
        if (u == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("supplier_uri", apiConfig.getRessourceUrl("Supplier"));
        return "/supplier/list";
    }

}
