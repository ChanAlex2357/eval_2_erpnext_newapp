package itu.eval_2.newapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itu.eval_2.newapp.models.api.requests.LoginRequest;
import itu.eval_2.newapp.models.api.responses.LoginResponse;
import itu.eval_2.newapp.models.api.wrapper.ApiResponseWrapper;
import itu.eval_2.newapp.models.user.UserErpNext;
import itu.eval_2.newapp.services.frappe.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error, 
                              HttpSession session, 
                              Model model) {
        // Redirect if already logged in
        if (session.getAttribute("user") != null) {
            return "redirect:/user/profile";
        }
        
        // Add error message if present
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        
        model.addAttribute("loginForm", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login")
public String processLogin(
    @Validated @ModelAttribute("loginForm") LoginRequest loginRequest,
    BindingResult bindingResult,
    HttpSession session,
    RedirectAttributes redirectAttributes
) {
    if (bindingResult.hasErrors()) {
        return "auth/login";
    }

    try {
        ResponseEntity<ApiResponseWrapper<LoginResponse>> response = authService.callLogin(loginRequest);
        ApiResponseWrapper<LoginResponse> body = response.getBody();

        if (body != null && body.isSuccess()) {
            // Store minimal user data in session
            LoginResponse data = body.getData();
            UserErpNext user = data.getUser();
            
            session.setAttribute("token", user.getAuthToken());
            session.setAttribute("user", user);

            // Set session timeout (30 minutes)
            session.setMaxInactiveInterval(30 * 60);

            if (user.hasRole("Administrator")){
                return "redirect:/user/profile";
            }
            return "redirect:/shop";


        } else {
            redirectAttributes.addFlashAttribute("error", 
                body != null ? body.getMessage() : "Login failed");
            return "redirect:/auth/login";
        }
    } catch (HttpClientErrorException e) {
        log.error("Invalid credentials error", e);
        redirectAttributes.addFlashAttribute("error", "Invalid credentials");
        return "redirect:/auth/login";
    } catch (Exception e) {
        log.error("Login error", e);
        redirectAttributes.addFlashAttribute("error", "Login service unavailable");
        return "redirect:/auth/login";
    }
}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate session and clear cookies
        session.invalidate();
        return "redirect:/auth/login?logout";
    }
}