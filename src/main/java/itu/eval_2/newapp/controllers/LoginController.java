package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponseWrapper<LoginResponse>> processLogin(
		@Validated @ModelAttribute("loginForm") LoginRequest loginRequest,
        BindingResult bindingResult,
        HttpSession session,
        RedirectAttributes redirectAttributes
	) {
        try {
            ResponseEntity<ApiResponseWrapper<LoginResponse>> response = authService.callLogin(loginRequest);
            
			ApiResponseWrapper<LoginResponse> body = response.getBody();

            return response;
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}