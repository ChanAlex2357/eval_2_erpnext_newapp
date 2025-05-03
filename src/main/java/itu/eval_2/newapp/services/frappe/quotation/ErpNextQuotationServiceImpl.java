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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ErpNextQuotationServiceImpl implements QuotationService {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ErpNextQuotationServiceImpl(ApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public List<SupplierQuotation> getAllQuotations(UserErpNext user) throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl("Supplier Quotation");
            log.debug("Fetching all quotations from URL: {}", url);

            HttpHeaders headers = createHeaders(user);
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
            );

            return parseQuotationList(response.getBody());
        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to fetch quotations from ERPNext : "+e.getMessage(), e);
        }
    }

    @Override
    public SupplierQuotation getQuotationById(UserErpNext user, String id) throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl("Supplier Quotation") + "/" + id;
            log.debug("Fetching quotation by ID from URL: {}", url);

            HttpHeaders headers = createHeaders(user);
            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
            );

            return parseSingleQuotation(response.getBody());
        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to fetch quotation by ID from ERPNext", e);
        }
    }

    @Override
    public void updateQuotationPrice(UserErpNext user, String id, double grandTotal) throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl("Supplier Quotation") + "/" + id;
            log.debug("Updating quotation price at URL: {}", url);

            HttpHeaders headers = createHeaders(user);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = String.format("{\"grand_total\": %.2f}", grandTotal);
            log.debug("Request body for update: {}", requestBody);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(requestBody, headers),
                String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ERPNextIntegrationException("Failed to update quotation. Status: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            throw new ERPNextIntegrationException("Failed to update quotation in ERPNext", e);
        }
    }

    private HttpHeaders createHeaders(UserErpNext user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", user.getAuthToken());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private List<SupplierQuotation> parseQuotationList(String json) throws ERPNextIntegrationException {
        try {
            SupplierQuotationListResponse quotations = objectMapper.readValue(json, SupplierQuotationListResponse.class);
            return quotations.getData();
        } catch (JsonProcessingException e) {
            throw new ERPNextIntegrationException("Failed to parse quotation list", e);
        }
    }

    private SupplierQuotation parseSingleQuotation(String json) throws ERPNextIntegrationException {
        try {
            return objectMapper.readValue(json, SupplierQuotation.class);
        } catch (JsonProcessingException e) {
            throw new ERPNextIntegrationException("Failed to parse single quotation", e);
        }
    }
}