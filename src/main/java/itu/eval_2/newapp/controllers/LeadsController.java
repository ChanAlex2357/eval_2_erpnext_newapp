package itu.eval_2.newapp.controllers;

import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.crm.Leads;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.crm.leads.LeadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/crm/leads")
public class LeadsController {

    @Autowired
    private LeadService leadService;

    @GetMapping
    public String list(
            HttpSession session,
            Model model
    ){
        UserErpNext user = (UserErpNext) session.getAttribute("user");
        if (user == null){
            return  "redirect:/";
        }

        List<Leads> leads = new ArrayList<>();
        try {
            leads = leadService.getLeads(user);
        } catch (ERPNexException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("leads",leads);
        return  "crm/leads/list";
    }
}
