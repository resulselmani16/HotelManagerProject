package com.example.hotelmanagerproject.Repository;

import com.example.hotelmanagerproject.Model.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface GuestRepository extends CrudRepository<Guest, Long> {
    Guest findGuestByName(String name);
    Guest findGuestByNameAndLastName(String name, String lastName);
}
