package com.example.hotelmanagerproject.Repository;

import com.example.hotelmanagerproject.Model.HotelManagerSession;
import org.springframework.data.repository.CrudRepository;

public interface HotelManagerSessionRepository extends CrudRepository<HotelManagerSession, Long> {
    HotelManagerSession findBySessionHashCode(String sessionHashCode);
}
