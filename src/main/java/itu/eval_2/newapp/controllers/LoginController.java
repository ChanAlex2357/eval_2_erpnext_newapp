package itu.eval_2.newapp.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.models.api.ApiResponse;
import itu.eval_2.newapp.models.api.ApiResponseWrapper;
import itu.eval_2.newapp.models.api.requests.LoginRequest;
import itu.eval_2.newapp.models.api.responses.LoginResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to login.html
    }

    
    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequest loginRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://erpnext.localhost:8000/api/method/eval_app.api.login";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<ApiResponseWrapper<LoginResponse>> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<ApiResponseWrapper<LoginResponse>>() {}
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            ApiResponse<LoginResponse> loginResponse = response.getBody().getMessage();
            if (loginResponse.isSuccess()) {
                // You can save SID or tokens in session here if needed
                return "redirect:/supplier/home";
            }
        }
        return "redirect:/login?error";
    }

}