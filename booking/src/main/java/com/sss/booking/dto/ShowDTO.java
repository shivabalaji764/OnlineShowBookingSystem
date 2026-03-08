package com.sss.booking.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowDTO {
    private LocalDateTime showTime;
    private LocalTime showLength;

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public LocalTime getShowLength() {
        return showLength;
    }

    @Override
    public String toString() {
        return "ShowDTO{" +
                "showTime=" + showTime +
                ", showLength=" + showLength +
                '}';
    }
}
