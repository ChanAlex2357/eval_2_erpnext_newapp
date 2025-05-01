package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itu.eval_2.newapp.config.ApiConfig;
import itu.eval_2.newapp.models.api.requests.LoginRequest;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.responses.LoginResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiRequestWrapper;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private final ApiConfig apiConfig;
    @Autowired
    private final RestTemplate restTemplate;

    public LoginController(ApiConfig apiConfig, RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@Validated LoginRequest apiRequest,
                        BindingResult bindingResult,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        
        // Validate input
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/login";
        }

        try {
            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity
            HttpEntity<LoginRequest> requestEntity = 
                new HttpEntity<>(apiRequest, headers);

            // Make API call
            ResponseEntity<ApiResponseWrapper<LoginResponse>> response = restTemplate.exchange(
                apiConfig.getRessourceUrl(),
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ApiResponseWrapper<LoginResponse>>() {});

            // Handle response
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ApiResponse<LoginResponse> apiResponse = response.getBody().getMessage();
                
                if (apiResponse.isSuccess()) {
                    // Store user session data
                    LoginResponse loginResponse = apiResponse.getData();
                    session.setAttribute("user", loginResponse.getUser());
                    session.setAttribute("token", loginResponse.getToken());
                    
                    return "redirect:/supplier/home";
                }
            }
            
            redirectAttributes.addFlashAttribute("error", "Invalid credentials");
            return "redirect:/login";
            
        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error", "Login failed: " + e.getStatusCode());
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Service unavailable. Please try again later.");
            return "redirect:/login";
        }
    }
}