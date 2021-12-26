package com.example.hotelmanagerproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/hotel/home")
    public String getHome(){
        return "home";
    }
}
