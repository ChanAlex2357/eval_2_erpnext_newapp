package itu.eval_2.newapp.services.frappe.crm.leads;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.crm.Leads;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LeadService extends FrappeCRUDService<Leads> {
    public LeadService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    public List<Leads> getLeads(UserErpNext user) throws ERPNextIntegrationException {
        return  getAllDocuments(user, new Leads(), Leads.class);
    }

}
