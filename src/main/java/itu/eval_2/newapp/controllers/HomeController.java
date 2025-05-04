package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index(HttpSession session){
        if (session.getAttribute("user") == null) {
            return "redirect:/auth/login";
        }
        return "redirect:/user/profile";
    }
}
