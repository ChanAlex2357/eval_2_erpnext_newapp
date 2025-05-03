package itu.eval_2.newapp.controllers;

import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.quotation.QuotationService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/quotations")
@Slf4j
public class QuotationController {

    private final QuotationService quotationService;

    @Autowired
    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping
    public String index(HttpSession session, Model model) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            List<SupplierQuotation> quotations = quotationService.getAllQuotations(user);
            log.info("Quotation List",quotations); 
            model.addAttribute("quotations", quotations);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch quotations: " + e.getMessage());
        }

        return "quotation/list";
    }

    @GetMapping("/{id}")
    public String viewQuotation(@PathVariable("id") String id, HttpSession session, Model model) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            SupplierQuotation quotation = quotationService.getQuotationById(user, id);
            model.addAttribute("quotation", quotation);
            model.addAttribute("updateForm", new QuotationUpdateForm(quotation.getGrandTotal()));
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch quotation: " + e.getMessage());
            return "redirect:/edit";
        }

        return "quotation/edit";
    }

    @PostMapping("/{id}/update")
    public String updateQuotation(
            @PathVariable("id") String id,
            @ModelAttribute("quotation") SupplierQuotation quotation,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            quotationService.updateQuotation(user, id, quotation);
            redirectAttributes.addFlashAttribute("success", "Quotation updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update quotation: " + e.getMessage());
        }

        return "redirect:/quotations/" + id;
    }

    // Simple form object for validation
    private static class QuotationUpdateForm {
        private double grandTotal;

        public QuotationUpdateForm(double grandTotal) {
            this.grandTotal = grandTotal;
        }

        // Getters and setters
        public double getGrandTotal() {
            return grandTotal;
        }

        public void setGrandTotal(double grandTotal) {
            this.grandTotal = grandTotal;
        }
    }
}