package itu.eval_2.newapp.controllers.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class CommerceController {
    @GetMapping
    public  String shop(){
        return "shop/index";
    }
}
