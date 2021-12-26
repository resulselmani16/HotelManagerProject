package com.example.hotelmanagerproject.Controller;

import com.example.hotelmanagerproject.Model.Guest;
import com.example.hotelmanagerproject.Repository.GuestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hotel")
public class DashboardController {
    private final GuestRepository guestRepository;

    public DashboardController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/add-guest")
    public String getAddGuest(Model model) {
        model.addAttribute("guest", new Guest());
        model.addAttribute("addGuest", "/hotel/view-guests");
        return "add-guest";
    }

    @PostMapping("/add-guest")
    public String postGuest(@ModelAttribute Guest guest, Model model) {
        model.addAttribute("guest", guest);
        model.addAttribute("addGuest", "/hotel/view-guests");
        this.guestRepository.save(guest);
        return "view-guests";
    }

    @GetMapping("/view-guests")
    public String viewGuests() {
        return "view-guests";
    }

    @GetMapping("/add-reservation")
    public String getAddReservation() {
        return "add-reservation";
    }

    @GetMapping("/add-room")
    public String getAddRoom() {
        return "add-room";
    }

}
