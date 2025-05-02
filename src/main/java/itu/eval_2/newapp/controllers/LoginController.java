package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itu.eval_2.newapp.models.api.FrappeApi;
import itu.eval_2.newapp.models.api.requests.LoginRequest;
import itu.eval_2.newapp.models.api.responses.ApiResponse;
import itu.eval_2.newapp.models.api.responses.LoginResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import itu.eval_2.newapp.services.frappe.AuthService;
import itu.eval_2.newapp.services.frappe.FrappeService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private FrappeService apiService;

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error, HttpSession session ,Model model) {
        if (session.getAttribute("user") != null) {
            return "redirect:/supplier/home";
        }
        model.addAttribute("loginForm",new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
    public String processLogin(
        @Validated @ModelAttribute("loginForm") LoginRequest loginRequest,
        BindingResult bindingResult,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {    
        log.info(loginRequest.toString());
        // Validation des champs
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        try {
            // FrappeApi frappeApi = new FrappeApi(loginRequest);
            // apiService.callMethod(
            //     "/login", 
            //     HttpMethod.GET, 
            //     frappeApi
            // );

            // ApiResponseWrapper response = frappeApi.getResponseModel();
            // ApiResponse<LoginResponse> model = (ApiResponse<LoginResponse>)response.getMessage();
            
            // if (response.getMessage() != null && model.getData() != null) { 
            //     LoginResponse loginResponse = (LoginResponse) model.getData();
            //     session.setAttribute("user", loginResponse.getUser());
            //     session.setMaxInactiveInterval(1800); // 30 minutes
            //     return "redirect:/supplier/home";
            // }
            
            // redirectAttributes.addFlashAttribute("error", "Identifiants incorrects");

            authService.callLogin(loginRequest);

        } catch (HttpClientErrorException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Erreur de connexion: " + e.getStatusCode().value());
        } catch (Exception e) {
            log.error("ERROR == ", e);
            redirectAttributes.addFlashAttribute("error", 
                "Service indisponible. Veuillez réessayer plus tard.");
        }
        
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}