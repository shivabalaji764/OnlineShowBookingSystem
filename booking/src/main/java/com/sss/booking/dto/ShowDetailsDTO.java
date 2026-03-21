package com.sss.booking.dto;

import com.sss.booking.model.ScreenLayout;
import com.sss.booking.model.ShowModel;
import com.sss.booking.model.ShowSeat;

import java.util.List;

public class ShowDetailsDTO {
    private ShowModel showModel;
    private ScreenLayout screenLayout;
    private List<List<ShowSeat>> showSeats;

    public ShowModel getShowModel() {
        return showModel;
    }

    public void setShowModel(ShowModel showModel) {
        this.showModel = showModel;
    }

    public ScreenLayout getScreenLayout() {
        return screenLayout;
    }

    public void setScreenLayout(ScreenLayout screenLayout) {
        this.screenLayout = screenLayout;
    }

    public List<List<ShowSeat>> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<List<ShowSeat>> showSeats) {
        this.showSeats = showSeats;
    }

    @Override
    public String toString() {
        return "ShowDetailsDTO{" +
                "showModel=" + showModel +
                ", screenLayout=" + screenLayout +
                ", showSeats=" + showSeats +
                '}';
    }
}
