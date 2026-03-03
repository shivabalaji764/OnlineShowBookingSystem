package com.sss.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;

@Entity
public class ScreenLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screenLayout_id;

    private String screen_type;
    private int base_price;
    private int rows;
    private int columns;
    private ArrayList<Seat> seats;

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

    public ArrayList<Seat> getSeats() {
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
