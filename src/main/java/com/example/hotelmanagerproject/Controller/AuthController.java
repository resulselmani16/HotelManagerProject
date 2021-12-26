package com.example.hotelmanagerproject.Controller;

import com.example.hotelmanagerproject.Model.HotelManager;
import com.example.hotelmanagerproject.Model.HotelManagerSession;
import com.example.hotelmanagerproject.Model.ManagerLoginModel;
import com.example.hotelmanagerproject.Repository.HotelManagerRepository;
import com.example.hotelmanagerproject.Repository.HotelManagerSessionRepository;
import com.example.hotelmanagerproject.service.UtilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hotel")
public class AuthController {
    private final HotelManagerRepository hotelManagerRepository;
    private final HotelManagerSessionRepository hotelManagerSessionRepository;
    private final UtilityService utilityService;
    public AuthController(HotelManagerRepository hotelManagerRepository, HotelManagerSessionRepository hotelManagerSessionRepository, UtilityService utilityService){
        this.hotelManagerRepository = hotelManagerRepository;
        this.hotelManagerSessionRepository = hotelManagerSessionRepository;
        this.utilityService = utilityService;
    }


    @GetMapping("/register-manager")
    public String getRegister(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if(utilityService.isManagerLoggedIn(request.getCookies())){
            response.sendRedirect("/hotel/dashboard");
            return null;
        }
        model.addAttribute("manager", new HotelManager());
        return "register";
    }

    @PostMapping("/register-manager")
    public String postRegister(@ModelAttribute HotelManager hotelManager, Model model){
        HotelManager existingHotelManager = this.hotelManagerRepository.findByEmail(hotelManager.getEmail());
        if (existingHotelManager != null){
            model.addAttribute("manager", hotelManager);
            model.addAttribute("error", "The email you entered is already on use");
        }
        this.hotelManagerRepository.save(hotelManager);
        return "dashboard";
    }

    @GetMapping("/manager-login")
    public String getLogin(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (utilityService.isManagerLoggedIn(request.getCookies())){
            response.sendRedirect("/hotel/dashboard");
            return null;
        }
        model.addAttribute("loginModel", new ManagerLoginModel());
        model.addAttribute("loginAction", "/hotel/manager-login");
        return "login";
    }

    @PostMapping("/manager-login")
    public String postLogin(@ModelAttribute ManagerLoginModel managerLoginModel, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        HotelManager loggedManager = this.hotelManagerRepository.findByEmailAndPassword(managerLoginModel.getUsername(), managerLoginModel.getPassword());
        if(loggedManager == null){
            model.addAttribute("error", "Credentials are wrong");
            model.addAttribute("loginModel", new ManagerLoginModel());
            model.addAttribute("loginAction", "/hotel/manager-login");
            return "login";
        }

        Cookie cookie = new Cookie("logged_in", "true");
        cookie.setPath("/");
        response.addCookie(cookie);
        HotelManagerSession hotelManagerSession = new HotelManagerSession();
        hotelManagerSession.setHotelManager(loggedManager);
        hotelManagerSession.setSessionHashCode(utilityService.generateRandomString(20));
        hotelManagerSession.setIpAddress(request.getRemoteAddr());
        hotelManagerSessionRepository.save(hotelManagerSession);
        Cookie sessionCookie = new Cookie("session_id", hotelManagerSession.getSessionHashCode());
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        response.sendRedirect("/hotel/dashboard");
        return null;
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie loggedInCookie = new Cookie("logged_in", "false");
        loggedInCookie.setPath("/");
        loggedInCookie.setMaxAge(0);
        Cookie sessionIdCookie = new Cookie("session_id", "");
        sessionIdCookie.setPath("/");
        sessionIdCookie.setMaxAge(0);
        response.addCookie(loggedInCookie);
        response.addCookie(sessionIdCookie);
        response.sendRedirect("/hotel/manager-login");
    }

}
