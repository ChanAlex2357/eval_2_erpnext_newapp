package itu.eval_2.newapp.controllers;

import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.quotation.QuotationService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/quotations")
@Slf4j
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping
    public String index(
        HttpSession session, 
        Model model,
        @RequestParam(required = false,name = "supplier") String supplier
    ) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            SupplierQuotationFilter filter = new SupplierQuotationFilter(supplier);
            List<SupplierQuotation> quotations = quotationService.getAllQuotations(user,filter);
            model.addAttribute("quotations", quotations);
            model.addAttribute("filter", filter);
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
}