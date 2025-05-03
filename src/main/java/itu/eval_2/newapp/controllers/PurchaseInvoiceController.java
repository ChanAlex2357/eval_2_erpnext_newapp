package itu.eval_2.newapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.invoice.PurchaseInvoiceService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/invoices")
public class PurchaseInvoiceController {
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService;
    @GetMapping
    public String invoicesList(HttpSession session, Model model){
        try {
            List<PurchaseInvoice> invoices = purchaseInvoiceService.getAllInvoices((UserErpNext)session.getAttribute("user")); 

            model.addAttribute("invoices", invoices);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching Purchase Suppliers : "+e.getMessage());
        }

        return "invoice/list";
    }
}
