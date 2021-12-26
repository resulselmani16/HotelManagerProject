package com.example.hotelmanagerproject.Repository;

import com.example.hotelmanagerproject.Model.HotelManager;
import org.springframework.data.repository.CrudRepository;

public interface HotelManagerRepository extends CrudRepository<HotelManager, Long> {
    HotelManager findByEmail(String email);

    HotelManager findByEmailAndPassword(String email, String password);
}
