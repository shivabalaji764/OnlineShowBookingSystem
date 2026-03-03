package com.sss.booking.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class ScreenLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screenLayout_id;

    private String screen_type;
    private int base_price;
    private int rows;
    private int columns;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public ScreenLayout() {
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getScreenLayout_id() {
        return screenLayout_id;
    }

    public String getScreen_type() {
        return screen_type;
    }

    public void setScreen_type(String screen_type) {
        this.screen_type = screen_type;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setBase_price(int base_price) {
        this.base_price = base_price;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "ScreenLayout{" +
                "screenLayout_id=" + screenLayout_id +
                ", screen_type='" + screen_type + '\'' +
                ", base_price=" + base_price +
                ", rows=" + rows +
                ", columns=" + columns +
                ", seats=" + seats +
                '}';
    }
}
