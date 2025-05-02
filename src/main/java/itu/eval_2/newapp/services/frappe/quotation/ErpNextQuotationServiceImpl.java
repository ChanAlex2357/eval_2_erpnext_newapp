package itu.eval_2.newapp.services.frappe.quotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.api.responses.SupplierQuotationListResponse;
import itu.eval_2.newapp.models.quotation.SupplierQuotation;
import itu.eval_2.newapp.models.user.UserErpNext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ErpNextQuotationServiceImpl implements QuotationService {

    @Autowired
    private ApiConfig apiConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<SupplierQuotation> getAllQuotations(UserErpNext user) throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl("SupplierQuotation");
            log.info("Quotation URL: {}", url);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", user.getAuthToken());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
            );

            ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            SupplierQuotationListResponse body = mapper.readValue(response.getBody(), SupplierQuotationListResponse.class);

            return body != null ? body.getData() : Collections.emptyList();

        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to fetch quotations: " + e.getMessage(), e);
        } catch (JsonProcessingException e) {
            throw new ERPNextIntegrationException("Failed to parse quotation data: " + e.getMessage(), e);
        }
    }
}