package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.models.user.UserErpNext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    @GetMapping
    public String index(HttpServletRequest request,HttpSession session,Model model){
        UserErpNext u = (UserErpNext) session.getAttribute("user");
        if (u == null) {
            return "redirect:/auth/login";
        }
        // Set suppplier filter if needed
        String supplier_name = request.getParameter("supplier");
        model.addAttribute("supplier_name", supplier_name);
        return "/supplier/list";
    }

}
