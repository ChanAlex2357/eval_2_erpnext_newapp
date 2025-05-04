package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.purchase.PurchaseInvoice;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.invoice.PurchaseInvoiceService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PurchaseInvoiceService invoiceService;
    
    @GetMapping("/{id}")
    public String fichePayment(HttpSession session,@PathParam("id") String id, Model model){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        try {
            // TODO: get the invoice
            PurchaseInvoice invoice = invoiceService.getInvoinceById(user, id);
            // Generate the Payment
            model.addAttribute("paymentEntry",new PaymentEntry());
        } catch (Exception e) {
            // TODO: handle exception
        }

        return "payment/detail";
    }

    @PostMapping("/{id}/pay")
    public String pay(){
        return "";
    }
}
