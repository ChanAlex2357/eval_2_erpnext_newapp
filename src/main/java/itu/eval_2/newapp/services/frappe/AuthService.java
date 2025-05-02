package itu.eval_2.newapp.services.frappe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.api.requests.LoginRequest;

@Service
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiConfig apiConfig;
    

    public void callLogin(LoginRequest loginRequest){
        HttpHeaders apiHeaders = new HttpHeaders();
        apiHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> apiEntity = new HttpEntity<>(loginRequest,apiHeaders);

        ResponseEntity<String> response =  restTemplate.exchange(
            apiConfig.getMethodUrl("/login"),
            HttpMethod.POST,
            apiEntity,
            String.class
        );
    }
}
