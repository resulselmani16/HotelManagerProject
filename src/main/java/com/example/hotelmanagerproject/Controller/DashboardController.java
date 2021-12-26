package com.example.hotelmanagerproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String getDashboard(){
        return "dashboard";
    }
}
