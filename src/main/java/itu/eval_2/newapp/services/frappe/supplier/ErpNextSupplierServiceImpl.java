package itu.eval_2.newapp.services.frappe.supplier;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
            // String url = apiConfig.getRessourceUrl("Supplier",new String[]{"name","country"});
            log.info("Supplier URL: {}", url);
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", user.getAuthToken());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
            );

            // Parse the JSON manually for better error handling
            ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            SupplierListResponse body = mapper.readValue(response.getBody(), SupplierListResponse.class);
            
            return body != null ? body.getData() : Collections.emptyList();
            
        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to fetch suppliers: " + e.getMessage(),e);
        } catch (JsonProcessingException e) {
            throw new ERPNextIntegrationException("Failed to parse supplier data: " + e.getMessage(),e);
        }
    }
}
