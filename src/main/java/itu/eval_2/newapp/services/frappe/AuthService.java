package itu.eval_2.newapp.services.frappe;

import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.api.requests.LoginRequest;
import itu.eval_2.newapp.models.api.responses.LoginResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;

@Service
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;

    public ResponseEntity<ApiResponseWrapper<LoginResponse>> callLogin(LoginRequest loginRequest) {
        // 1. Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // 3. Create request entity
        HttpEntity<LoginRequest> requestEntity = new HttpEntity<>(loginRequest, headers);
        ParameterizedTypeReference<ApiResponseWrapper<LoginResponse>> responseType = new ParameterizedTypeReference<ApiResponseWrapper<LoginResponse>>(){};
        // 4. Make the request and return response
        return restTemplate.exchange(
            apiConfig.getMethodUrl("/eval_app.api.login"),
            // apiConfig.getMethodUrl("/login"),
            HttpMethod.POST,
            requestEntity,
            responseType
        );
    }
}
