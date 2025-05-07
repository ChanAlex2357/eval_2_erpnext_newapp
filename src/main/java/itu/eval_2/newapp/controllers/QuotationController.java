package itu.eval_2.newapp.controllers;

import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.filter.SupplierQuotationFilter;
import itu.eval_2.newapp.models.quotation.RequestForQuotation;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.quotation.QuotationService;
import itu.eval_2.newapp.services.frappe.quotation.RequestQuotationService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/quotations")
@Slf4j
public class QuotationController {

    @Autowired
    private QuotationService quotationService;
    @Autowired
    private RequestQuotationService requestQuotationService;


    @GetMapping("/requests")
    public String requests(
        HttpSession session, 
        Model model,
        @RequestParam(required = false,name = "supplier") String supplier
    ) {
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        SupplierQuotationFilter filter = new SupplierQuotationFilter(supplier);
        model.addAttribute("filter", filter);
        try {
            List<RequestForQuotation> quotations = requestQuotationService.getAllRequestForQuotation(user,supplier);
            model.addAttribute("quotations", quotations);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch request quotations: " + e.getMessage());
        }
        return "quotation/request_list";
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

    @GetMapping("/requests/{id}/supplier/{supplier}")
    public String viewFromRequest(
        @PathVariable("id") String id,
        @PathVariable("supplier") String supplier,
        Model model,
        RedirectAttributes redirectAttribute,
        HttpSession session
    ){

        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            SupplierQuotation quotation  = quotationService.getQuotationByRequestForQuotation(user, id, supplier);
            model.addAttribute("quotation", quotation);
        } catch (ERPNextIntegrationException e) {
            redirectAttribute.addFlashAttribute("error", "Failed to get quotation: " + e.getMessage());
            return "redirect:/quotations/requests";
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

        } catch (ERPNextIntegrationException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to get quotation: " + e.getMessage());

        }

        return "redirect:/quotations/" + id;
    }
}