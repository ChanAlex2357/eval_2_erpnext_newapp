package itu.eval_2.newapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/profile")
    public String getMethodName(HttpSession session,Model model) {
        if (session.getAttribute("user") == null) {
            return "/auth/login";
        }

        return "/user/profile";
    }
    
}
