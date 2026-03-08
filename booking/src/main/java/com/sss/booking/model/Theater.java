package com.sss.booking.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;
    private String theaterName;

    private String address;
    private double latitude;
    private double longitude;

    private int screen_count;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<ScreenLayout> screens;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getScreen_count() {
        return screen_count;
    }

    public void setScreen_count(int screen_count) {
        this.screen_count = screen_count;
    }

    public List<ScreenLayout> getScreens() {
        return screens;
    }

    public void setScreens(List<ScreenLayout> screens) {
        this.screens = screens;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId=" + theaterId +
                ", theaterName='" + theaterName + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", screen_count=" + screen_count +
                ", screens=" + screens +
                '}';
    }
}
