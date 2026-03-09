package com.sss.booking.model;

import jakarta.persistence.*;
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
    private long showId;

    private String showName;
    private int theaterId;
    private int screenLayoutId;
    private LocalDateTime showDateAndTime;
    private LocalTime showLength;

    @OneToMany(mappedBy = "showModel", cascade = CascadeType.ALL)
    private List<ShowSeat> seats;

    @ManyToOne
    @JoinColumn(name="clientId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "show")
    private List<Booking> bookings;

    public long getShowId() {
        return showId;
    }

    public void setShowId(long showId) {
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getScreenLayoutId() {
        return screenLayoutId;
    }

    public void setScreenLayoutId(int screenLayoutId) {
        this.screenLayoutId = screenLayoutId;
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

    public List<ShowSeat> getSeats() {
        return seats;
    }

    public void setSeats(List<ShowSeat> seats) {
        this.seats = seats;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    @Override
    public String toString() {
        return "ShowModel{" +
                "showId=" + showId +
                ", showName='" + showName + '\'' +
                ", theaterId=" + theaterId +
                ", screenLayoutId=" + screenLayoutId +
                ", showDateAndTime=" + showDateAndTime +
                ", showLength=" + showLength +
                ", seats=" + seats +
                ", bookings=" + bookings +
                '}';
    }
}
