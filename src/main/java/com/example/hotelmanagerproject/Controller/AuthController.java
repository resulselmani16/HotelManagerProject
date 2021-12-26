package com.example.hotelmanagerproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotel")
public class AuthController {
    @GetMapping("/register-manager")
    public String getRegister(){
        return "register";
    }
}
