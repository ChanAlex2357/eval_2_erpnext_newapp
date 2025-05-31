package itu.eval_2.newapp.services.frappe;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.item.Warehouse;
import itu.eval_2.newapp.models.user.UserErpNext;

@Service
public class WarehouseService extends FrappeCRUDService<Warehouse> {

    public WarehouseService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
        //TODO Auto-generated constructor stub
    }

    public List<Warehouse> fetcWarehouses(UserErpNext user) throws ERPNextIntegrationException {
        List<Warehouse> warehouses = getAllDocuments(user, new Warehouse(), Warehouse.class);
        return warehouses;
    }
    
}
