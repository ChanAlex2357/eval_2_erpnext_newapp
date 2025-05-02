package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import itu.eval_2.newapp.models.api.requests.LoginRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/auth")
public class LoginController {

    // @Autowired
    // private FrappeApiService apiService;

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
        // Validation des champs
        if (bindingResult.hasErrors()) {
            return "auth/login";
        }

        // try {
                    
        //     ApiResponseWrapper<? extends ResponseModel> response = apiService.callMethod("/eval_app.api.login","GET", loginRequest, new LoginResponse());
            
        //     if (response.getMessage() != null && response.getMessage().getData() != null) { 
        //         LoginResponse loginResponse = (LoginResponse) response.getMessage().getData();
        //         session.setAttribute("user", loginResponse.getUser());
        //         session.setMaxInactiveInterval(1800); // 30 minutes
                
        //         return "redirect:/supplier/home";
        //     }
            
        //     redirectAttributes.addFlashAttribute("error", "Identifiants incorrects");
        // } catch (HttpClientErrorException e) {
        //     redirectAttributes.addFlashAttribute("error", 
        //         "Erreur de connexion: " + e.getStatusCode().value());
        // } catch (Exception e) {
        //     redirectAttributes.addFlashAttribute("error", 
        //         "Service indisponible. Veuillez réessayer plus tard.");
        // }
        
        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}