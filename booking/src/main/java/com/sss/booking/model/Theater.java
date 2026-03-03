package com.sss.booking.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theater_id;

    private String address;
    private double latitude;
    private double longitude;

    private int screen_count;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<ScreenLayout> screens;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Theater() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
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

    public void setScreens(ArrayList<ScreenLayout> screens) {
        this.screens = screens;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theater_id=" + theater_id +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", screen_count=" + screen_count +
                ", screens=" + screens +
                '}';
    }
}
