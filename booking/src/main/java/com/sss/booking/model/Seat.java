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
    private int seat_id;

    private String seat_name;
    private int seatRow;
    private int seatColumn;

    @ManyToOne
    @JoinColumn(name = "screenLayout_id")
    private ScreenLayout screen;

    public Seat() {
    }

    public ScreenLayout getScreen() {
        return screen;
    }

    public void setScreen(ScreenLayout screen) {
        this.screen = screen;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public String getSeat_name() {
        return seat_name;
    }

    public void setSeat_name(String seat_name) {
        this.seat_name = seat_name;
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

    @Override
    public String toString() {
        return "Seat{" +
                "seat_id=" + seat_id +
                ", seat_name='" + seat_name + '\'' +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                '}';
    }
}
