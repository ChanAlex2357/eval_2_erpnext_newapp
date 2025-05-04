package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import itu.eval_2.newapp.models.payment.PaymentEntry;
import itu.eval_2.newapp.models.user.UserErpNext;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    
    @GetMapping("{id}")
    public String fichePayment(HttpSession session,@PathParam("id") String id, Model model){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null) {
            return "redirect:/auth/login";
        }

        model.addAttribute("paymentEntry",new PaymentEntry());
        return "payment/details";
    }
}
