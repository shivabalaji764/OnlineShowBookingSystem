package com.sss.booking.model;


import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
@Entity
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showSeatId;

    private int seatRow;
    private int seatColumn;
    private int status;

    private LocalDateTime lockTime;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "showId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private ShowModel showModel;

    @ManyToOne
    @JoinColumn(name = "booking")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Booking booking;

    public int getShowSeatId() {
        return showSeatId;
    }

    public void setShowSeatId(int showSeatId) {
        this.showSeatId = showSeatId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ShowModel getShowModel() {
        return showModel;
    }

    public void setShowModel(ShowModel showModel) {
        this.showModel = showModel;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "ShowSeats{" +
                "showSeatId=" + showSeatId +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                ", status=" + status +
                '}';
    }
}
