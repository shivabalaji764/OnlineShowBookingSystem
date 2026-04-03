package com.sss.booking.dto;

import java.time.LocalDateTime;

public class DetailsDTO {
    private String showName;
    private String theater;
    private String address;
    private Integer screen;

    private LocalDateTime date;
    private String seats;
    private Double totalPrice;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getScreen() {
        return screen;
    }

    public void setScreen(Integer screen) {
        this.screen = screen;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
