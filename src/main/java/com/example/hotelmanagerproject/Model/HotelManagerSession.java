package com.example.hotelmanagerproject.Model;

import javax.persistence.*;

@Entity
public class HotelManagerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sessionHashCode;
    private String ipAddress;

    public String getSessionHashCode() {
        return sessionHashCode;
    }

    public void setSessionHashCode(String sessionHashCode) {
        this.sessionHashCode = sessionHashCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public HotelManager getHotelManager() {
        return hotelManager;
    }

    public void setHotelManager(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private HotelManager hotelManager;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
