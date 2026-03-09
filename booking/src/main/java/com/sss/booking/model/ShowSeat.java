package com.sss.booking.model;


import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    @ManyToOne
    @JoinColumn(name = "showId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private ShowModel showModel;

    @ManyToOne
    @JoinColumn(name = "showSeats")
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
