package com.sss.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TheaterDTO {
    private List<LocalDate> showDate;
    private List<LocalTime> showTime;

    private List<Long> showId;

    private String theaterName;

    public List<LocalDate> getShowDate() {
        return showDate;
    }

    public void setShowDate(List<LocalDate> showDate) {
        this.showDate = showDate;
    }

    public List<LocalTime> getShowTime() {
        return showTime;
    }

    public void setShowTime(List<LocalTime> showTime) {
        this.showTime = showTime;
    }

    public List<Long> getShowId() {
        return showId;
    }

    public void setShowId(List<Long> showId) {
        this.showId = showId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    @Override
    public String toString() {
        return "TheaterDTO{" +
                "showDate=" + showDate +
                ", showTime=" + showTime +
                ", showId=" + showId +
                ", theaterName='" + theaterName + '\'' +
                '}';
    }
}
