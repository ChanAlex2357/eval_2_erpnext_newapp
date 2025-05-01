package itu.eval_2.newapp.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import itu.eval_2.newapp.models.api.requests.LoginRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to login.html
    }

    @PostMapping("/login")
    public String doLogin(LoginRequest loginRequest) {
        // Set up the RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://erpnext.localhost:8000/api/method/eval_app.api.login";

        // Set headers if needed
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create the request entity
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

        // Send the POST request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // Handle the response (for now, redirect to supplier home regardless of success)
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/supplier/home";
        } else {
            return "redirect:/login?error";
        }
    }
}