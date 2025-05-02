package itu.eval_2.newapp.services.frappe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.api.FrappeApi;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FrappeService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ApiConfig apiConfig;

    public String getMethodUrl(String method) {
        return apiConfig.getMethodUrl() + method;
    }

    public String getRessourceUrl(String ressource){
        return apiConfig.getRessourceUrl() + ressource;
    }


    public void call(String endpoint, HttpMethod method, FrappeApi apiData) {
    try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
         
        HttpEntity<Object> requestEntity = new HttpEntity<>(apiData.getRequestModel(), headers);
        
        // var response = restTemplate.postForObject(endpoint, apiData.getRequestModel(), String.class);
        ResponseEntity<String> response = restTemplate.exchange(
            endpoint,
            HttpMethod.POST,
            requestEntity,
            String.class
        );
        
        log.info("== LOGIN RESPONSE ==",response);
        // apiData.setResponseModel(response);
    } catch (HttpServerErrorException e) {
        // Handle 500 errors specifically
        log.error("Server error when calling {}: {}", endpoint, e.getResponseBodyAsString());
        throw new RuntimeException("ERPNext API error: " + e.getResponseBodyAsString(), e);
    } catch (RestClientException e) {
        log.error("Error calling {}: {}", endpoint, e.getMessage());
        throw new RuntimeException("Failed to call ERPNext API", e);
    }
}

    public void callMethod(String endpoint, HttpMethod method , FrappeApi apiData){
        call(getMethodUrl(endpoint),method,apiData);
    }

    public void callRessource(String endpoint, HttpMethod method , FrappeApi apiData){
        call(getRessourceUrl(endpoint),method,apiData);
    }
}
