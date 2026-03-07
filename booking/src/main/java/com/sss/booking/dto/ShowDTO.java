package com.sss.booking.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowDTO {
    private LocalDateTime showTime;
    private LocalTime showLength;

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public LocalTime getShowLength() {
        return showLength;
    }

    public void setShowLength(LocalTime showLength) {
        this.showLength = showLength;
    }

    @Override
    public String toString() {
        return "ShowDTO{" +
                "showTime=" + showTime +
                ", showLength=" + showLength +
                '}';
    }
}
