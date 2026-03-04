package com.sss.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class ShowModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long show_id;

    private String show_name;
    private int theater_id;
    private int screenLayout_id;
    private LocalDateTime show_date_and_time;
    private LocalTime show_length;

    @OneToMany(mappedBy = "showModel", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowSeats> seats;

    @ManyToOne
    @JoinColumn(name="client_id")
    @JsonIgnoreProperties("shows")
    private Client client;



    public long getShow_id() {
        return show_id;
    }

    public void setShow_id(long show_id) {
        this.show_id = show_id;
    }

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public int getTheater_id() {
        return theater_id;
    }

    public void setTheater_id(int theater_id) {
        this.theater_id = theater_id;
    }

    public int getScreenLayout_id() {
        return screenLayout_id;
    }

    public void setScreenLayout_id(int screenLayout_id) {
        this.screenLayout_id = screenLayout_id;
    }

    public LocalDateTime getShow_date_and_time() {
        return show_date_and_time;
    }

    public void setShow_date_and_time(LocalDateTime show_date_and_time) {
        this.show_date_and_time = show_date_and_time;
    }

    public LocalTime getShow_length() {
        return show_length;
    }

    public void setShow_length(LocalTime show_length) {
        this.show_length = show_length;
    }

    public List<ShowSeats> getSeats() {
        return seats;
    }

    public void setSeats(List<ShowSeats> seats) {
        this.seats = seats;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ShowModel{" +
                "show_id=" + show_id +
                ", show_name='" + show_name + '\'' +
                ", theater_id=" + theater_id +
                ", screenLayout_id=" + screenLayout_id +
                ", show_date_and_time=" + show_date_and_time +
                ", show_length=" + show_length +
                ", seats=" + seats +
                '}';
    }
}
