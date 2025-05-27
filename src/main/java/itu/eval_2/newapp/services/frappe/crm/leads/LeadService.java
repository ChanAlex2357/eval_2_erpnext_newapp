package itu.eval_2.newapp.services.frappe.crm.leads;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.crm.Leads;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeWebService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LeadService extends FrappeWebService<Leads> {
    public LeadService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    public List<Leads> getLeads(UserErpNext user) throws ERPNexException {
        return  getAllDocuments(user, new Leads(), Leads.class);
    }

}
