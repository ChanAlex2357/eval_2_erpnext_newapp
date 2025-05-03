package itu.eval_2.newapp.services.frappe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.exceptions.ERPNextIntegrationException;
import itu.eval_2.newapp.models.action.FrappeModel;
import itu.eval_2.newapp.models.api.responses.ApiResourceResponse;
import itu.eval_2.newapp.models.api.responses.SingletonApiResourceResponse;
import itu.eval_2.newapp.models.filter.FrappeFilter;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.utils.http.HeadersUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public abstract class FrappeService<T extends FrappeModel> {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    abstract String getDoctype();

    public FrappeService(ApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public List<T> getAllDocuments(UserErpNext user, FrappeFilter filter, Class<T> modelClass) 
        throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceWithAllFieldsUrl(getDoctype(), filter != null ? filter.getFilters().getFilters() : null);
            log.info("Fetching all {} documents from URL: {}", getDoctype(), url);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>( HeadersUtils.createHeaders(user)),
                String.class
            );

            ApiResourceResponse<T> result = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(
                    ApiResourceResponse.class,
                    modelClass
                )
            );
            return result.getData();
        } catch (RestClientException | JsonProcessingException e) {
            throw new ERPNextIntegrationException(
                String.format("Failed to fetch %s documents: %s", getDoctype(), e.getMessage()), 
                e
            );
        }
    }

    public T getDocumentById(UserErpNext user, String id, Class<T> modelClass) 
        throws ERPNextIntegrationException {
        try {
            String url = apiConfig.getResourceUrl(getDoctype(), id);
            log.debug("Fetching {} document by ID from URL: {}", getDoctype(), url);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>( HeadersUtils.createHeaders(user)),
                String.class
            );

            SingletonApiResourceResponse<T> result = objectMapper.readValue(
                response.getBody(),
                objectMapper.getTypeFactory().constructParametricType(
                    SingletonApiResourceResponse.class,
                    modelClass
                )
            );
            return result.getData();
        } catch (RestClientException | JsonProcessingException e) {
            throw new ERPNextIntegrationException(
                String.format("Failed to fetch %s document with ID %s: %s", getDoctype(), id, e.getMessage()),
                e
            );
        }
    }

    public void updateDocument(UserErpNext user, String id, T document) 
        throws ERPNextIntegrationException {
        try {
            // Validate the document before update
            document.cotnrole();

            String url = apiConfig.getResourceUrl(getDoctype(), id);
            log.info("Updating {} document at URL: {}", getDoctype(), url);

            HttpHeaders headers =  HeadersUtils.createHeaders(user);
            headers.setContentType(MediaType.APPLICATION_JSON);

            restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(objectMapper.writeValueAsString(document), headers),
                String.class
            );
        } catch (Exception e) {
            throw new ERPNextIntegrationException(
                String.format("Failed to update %s document: %s", getDoctype(), e.getMessage()),
                e
            );
        }
    }


}