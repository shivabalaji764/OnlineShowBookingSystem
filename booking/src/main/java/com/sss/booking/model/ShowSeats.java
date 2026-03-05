package com.sss.booking.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int show_seat_id;

    private int seatRow;
    private int seatColumn;
    private int status;

    @ManyToOne
    @JoinColumn(name = "showId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private ShowModel showModel;

    public int getShow_seat_id() {
        return show_seat_id;
    }

    public void setShow_seat_id(int show_seat_id) {
        this.show_seat_id = show_seat_id;
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
                "show_seat_id=" + show_seat_id +
                ", seatRow=" + seatRow +
                ", seatColumn=" + seatColumn +
                ", status=" + status +
                '}';
    }
}
