package itu.eval_2.newapp.controllers;

import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.quotation.QuotationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/quotations")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping
    public String index(HttpSession session, Model model) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            List<SupplierQuotation> quotations = quotationService.getAllQuotations(user);
            model.addAttribute("quotations", quotations);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch quotations: " + e.getMessage());
        }

        return "/quotation/list";
    }
}
