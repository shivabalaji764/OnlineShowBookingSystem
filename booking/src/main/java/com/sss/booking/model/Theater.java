package com.sss.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theater_id;

    private String address;
    private double latitude;
    private double longitude;

    private int screen_count;
    private ArrayList<ScreenLayout>  screens;

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

    public ArrayList<ScreenLayout> getScreens() {
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
