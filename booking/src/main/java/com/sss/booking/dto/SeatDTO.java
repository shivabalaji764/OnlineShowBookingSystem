package com.sss.booking.dto;

import java.util.List;

public class SeatDTO {
    private List<List<Integer>> selectedSeats;

    public List<List<Integer>> getSelectedSeats() {
        return selectedSeats;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "selectedSeats=" + selectedSeats +
                '}';
    }
}
