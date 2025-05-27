package itu.eval_2.newapp.services.frappe;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNexException;
import itu.eval_2.newapp.models.item.Warehouse;
import itu.eval_2.newapp.models.user.UserErpNext;

@Service
public class WarehouseService extends FrappeWebService<Warehouse> {

    public WarehouseService(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
        //TODO Auto-generated constructor stub
    }

    public List<Warehouse> fetcWarehouses(UserErpNext user) throws ERPNexException {
        List<Warehouse> warehouses = getAllDocuments(user, new Warehouse(), Warehouse.class);
        return warehouses;
    }
    
}
