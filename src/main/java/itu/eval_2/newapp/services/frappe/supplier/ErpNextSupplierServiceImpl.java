package itu.eval_2.newapp.services.frappe.supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.responses.SupplierListResponse;
import itu.eval_2.newapp.models.supplier.ErpNextSupplier;
import itu.eval_2.newapp.models.user.UserErpNext;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ErpNextSupplierServiceImpl implements SupplierService {

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private RestTemplate restTemplate;

   @Override
    public List<ErpNextSupplier> getAllSuppliers(UserErpNext user) throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl("Supplier");
            log.info("Supplier URL : "+url);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", user.getAuthToken());
            
            ResponseEntity<SupplierListResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                SupplierListResponse.class
            );

            if (response.getBody()==null) {
                return new ArrayList<ErpNextSupplier>();
            }
            return response.getBody().getData();
        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to fetch suppliers", e);
        }
    }
}
