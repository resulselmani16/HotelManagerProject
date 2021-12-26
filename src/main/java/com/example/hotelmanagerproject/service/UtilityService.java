package com.example.hotelmanagerproject.service;


import com.example.hotelmanagerproject.Model.HotelManager;
import com.example.hotelmanagerproject.Model.HotelManagerSession;
import com.example.hotelmanagerproject.Repository.HotelManagerRepository;
import com.example.hotelmanagerproject.Repository.HotelManagerSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class UtilityService {
    private HotelManagerSessionRepository hotelManagerSessionRepository;
    private HotelManagerRepository hotelManagerRepository;

    public UtilityService(HotelManagerSessionRepository hotelManagerSessionRepository, HotelManagerRepository hotelManagerRepository){
        this.hotelManagerRepository = hotelManagerRepository;
        this.hotelManagerSessionRepository = hotelManagerSessionRepository;
    }

    public boolean isManagerLoggedIn(Cookie[] cookies){
        return this.getLoggedInManager(cookies) != null;
    }

    private HotelManager getLoggedInManager(Cookie[] cookies) {
        if(cookies == null){
            return null;
        }

        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("logged_in")
                && c.getValue().equals("true"))
                .findFirst();

        if(!cookieOptional.isPresent()){
            return null;
        }

        Optional<Cookie> sessionCookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("session_id"))
                .findFirst();

        if(!sessionCookieOptional.isPresent()){
            return null;
        }

        HotelManagerSession hotelManagerSession = this.hotelManagerSessionRepository.findBySessionHashCode(sessionCookieOptional.get().getValue());
        if(hotelManagerSession == null){
            return null;
        }
        return hotelManagerSession.getHotelManager();
    }

    public String generateRandomString(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
