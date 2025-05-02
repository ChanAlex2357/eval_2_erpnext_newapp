package itu.eval_2.newapp.services.frappe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.api.FrappeApi;
import itu.eval_2.newapp.models.api.responses.ResponseModel;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;

@Service
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


    public ApiResponseWrapper<? extends ResponseModel> call(String endpoint, HttpMethod method, FrappeApi apiData) {
        ResponseEntity<ApiResponseWrapper<? extends ResponseModel>> response = restTemplate.exchange(
            endpoint,
            method,
            new HttpEntity<>(apiData.getRequestModel()),  // Include request body if needed
            new ParameterizedTypeReference<ApiResponseWrapper<? extends ResponseModel>>() {}
        );
        apiData.setResponse(response);
        return response.getBody();
    }
}
