package com.sss.booking.model;


import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;

    private String seatName;
    private int seatRow;
    private int seatColumn;

    @ManyToOne
    @JoinColumn(name = "screenLayout_id")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private ScreenLayout screen;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
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

    public ScreenLayout getScreen() {
        return screen;
    }

    public void setScreen(ScreenLayout screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", seatName='" + seatName + '\'' +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                '}';
    }
}
