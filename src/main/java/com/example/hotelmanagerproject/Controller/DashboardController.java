package com.example.hotelmanagerproject.Controller;

import com.example.hotelmanagerproject.Model.Guest;
import com.example.hotelmanagerproject.Model.Room;
import com.example.hotelmanagerproject.Repository.GuestRepository;
import com.example.hotelmanagerproject.Repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/manager")
public class DashboardController {
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public DashboardController(GuestRepository guestRepository, RoomRepository roomRepository) {
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }

    @GetMapping("/add-guest")
    public String getAddGuest(Model model) {
        model.addAttribute("guest", new Guest());
        model.addAttribute("addGuest", "/manager/view-guests");
        return "add-guest";
    }

    @PostMapping("/add-guest")
    public String postGuest(@ModelAttribute Guest guest, Model model) {
        model.addAttribute("guest", guest);
        model.addAttribute("addGuest", "/manager/view-guests");
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
    public String getAddRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("addRoom", "/manager/view-rooms");
        return "add-room";
    }

    @PostMapping("/add-room")
    public String postAddRoom(@ModelAttribute Room room, Model model,
                              HttpServletResponse response, HttpServletRequest request){
        model.addAttribute("room", room);
        model.addAttribute("addRoom", "/manager/view-rooms");
        this.roomRepository.save(room);
        return "view-rooms";
    }

}
