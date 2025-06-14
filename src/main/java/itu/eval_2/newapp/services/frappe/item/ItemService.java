package itu.eval_2.newapp.services.frappe.item;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.item.Item;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;

@Service
public class ItemService extends FrappeCRUDService<Item>{

    public ItemService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    public List<Item> fetchAllItem(UserErpNext user) throws ERPNextIntegrationException {
        List<Item> items = getAllDocuments(user, new Item(), Item.class);
        return items;
    }
    
}
