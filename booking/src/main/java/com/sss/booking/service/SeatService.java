package com.sss.booking.service;


import com.sss.booking.dto.SeatDTO;
import com.sss.booking.model.ScreenLayout;
import com.sss.booking.model.Seat;
import com.sss.booking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ScreenService screenService;

    public boolean addSeats(List<List<Integer>> list, Integer screenLayoutId) {
        ScreenLayout screenLayout = screenService.getScreen(screenLayoutId);
        if(screenLayout==null) return false;
        for (int i=0;i<list.size();i++){
            for (int j=0;j<list.get(i).size();j++){
                if(list.get(i).get(j)==1){
                    Seat seat = new Seat();
                    seat.setScreen(screenLayout);
                    seat.setSeatRow(i);
                    seat.setSeatColumn(j);
                    seat.setSeatName(""+(char)(i+'A')+(j+1));

                    seatRepository.save(seat);
                }
            }
        }
        return true;
    }
}
