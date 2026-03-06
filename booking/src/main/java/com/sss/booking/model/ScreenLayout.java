package com.sss.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
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
    private int screenLayoutId;

    private String screenType;
    private int screenNo;
    private int basePrice;
    private int rows;
    private int columns;
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "theaterId")
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Theater theater;

    public int getScreenLayoutId() {
        return screenLayoutId;
    }

    public void setScreenLayoutId(int screenLayoutId) {
        this.screenLayoutId = screenLayoutId;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
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

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(int screenNo) {
        this.screenNo = screenNo;
    }

    @Override
    public String toString() {
        return "ScreenLayout{" +
                "screenLayoutId=" + screenLayoutId +
                ", screenType='" + screenType + '\'' +
                ", screenNo=" + screenNo +
                ", basePrice=" + basePrice +
                ", rows=" + rows +
                ", columns=" + columns +
                ", seats=" + seats +
                '}';
    }
}
