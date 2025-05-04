package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.payment.PurchaseInvoicePayment;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.invoice.PurchaseInvoiceService;
import itu.eval_2.newapp.services.frappe.payment.PaymentService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PurchaseInvoiceService invoiceService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{id}")
    public String fichePayment(HttpSession session,@PathVariable("id") String id, Model model){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            PurchaseInvoice invoice = invoiceService.getInvoinceById(user, id);

            PaymentEntry invoicePayment = paymentService.generatePayment(user, invoice);
            log.info(" = = = PAYMENT GENERATED = = = =",invoicePayment);
            
            model.addAttribute("paymentEntry",invoicePayment);
            model.addAttribute("invoiceName", id);
        } catch (Exception e) {
            model.addAttribute("error", "Error while generating the payment : " + e.getMessage());
        }

        return "payment/detail";
    }

    @PostMapping("/{id}/pay")
    public String pay(
        @PathVariable("id") String id,
        RedirectAttributes redirectAttributes,
        HttpSession session
    ){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            PurchaseInvoice invoice = invoiceService.getInvoinceById(user, id);
            // Generate the Payment
            PaymentEntry paymentEntry = paymentService.generatePayment(user, invoice);

            PaymentEntry saved = paymentService.validatePayment(user, paymentEntry);
            redirectAttributes.addFlashAttribute("success" ,"Payemete effectuer"+saved.getName()+" : "+paymentEntry.asStr());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error_message", "Erreur lors de la validation du payment : "+ e.getMessage());
        }
        return "redirect:/invoices";
    }
}
