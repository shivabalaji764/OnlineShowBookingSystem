package com.sss.booking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    private LocalDateTime bookingTime;
    private double cost;

    @ManyToOne
    @JoinColumn(name = "showId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private ShowModel show;

    @ManyToOne
    @JoinColumn(name = "userId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Users user;

    @OneToMany(mappedBy = "booking")
    private ShowSeat[] seats;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ShowModel getShow() {
        return show;
    }

    public void setShow(ShowModel show) {
        this.show = show;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public ShowSeat[] getSeats() {
        return seats;
    }

    public void setSeats(ShowSeat[] seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingTime=" + bookingTime +
                ", cost=" + cost +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }
}
