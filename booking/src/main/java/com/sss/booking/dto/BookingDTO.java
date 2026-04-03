package com.sss.booking.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingDTO {
    private Long bookingId;
    private String showName;
    private String theaterName;
    private Integer screenNo;
    private String address;
    private LocalDateTime showDateAndTime;
    private LocalTime showLength;
    private String seats;
    private Double totalPrice;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Integer getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(Integer screenNo) {
        this.screenNo = screenNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getShowDateAndTime() {
        return showDateAndTime;
    }

    public void setShowDateAndTime(LocalDateTime showDateAndTime) {
        this.showDateAndTime = showDateAndTime;
    }

    public LocalTime getShowLength() {
        return showLength;
    }

    public void setShowLength(LocalTime showLength) {
        this.showLength = showLength;
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
