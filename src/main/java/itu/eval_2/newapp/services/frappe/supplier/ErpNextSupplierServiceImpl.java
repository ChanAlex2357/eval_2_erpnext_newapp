package itu.eval_2.newapp.services.frappe.supplier;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.supplier.ErpNextSupplier;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.FrappeCRUDService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ErpNextSupplierServiceImpl extends FrappeCRUDService<ErpNextSupplier> implements SupplierService {

    public ErpNextSupplierServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        super(apiConfig, restTemplate);
    }

    @Override
    public List<ErpNextSupplier> getAllSuppliers(UserErpNext user) throws ERPNextIntegrationException {
        return getAllDocuments(user, null, ErpNextSupplier.class);
    }

    @Override
    public String getDoctype() {
        return "Supplier";
    }
}
