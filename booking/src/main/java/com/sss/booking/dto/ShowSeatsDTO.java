package com.sss.booking.dto;

import java.util.List;

public class ShowSeatsDTO {
    private List<Integer> selectedSeats;
    private double cost;

    public List<Integer> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<Integer> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
