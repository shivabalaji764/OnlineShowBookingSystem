package com.sss.booking.service;

import com.sss.booking.dto.ShowDetailsDTO;
import com.sss.booking.model.Booking;
import com.sss.booking.model.ScreenLayout;
import com.sss.booking.model.ShowModel;
import com.sss.booking.model.ShowSeat;
import com.sss.booking.repository.ScreenRepository;
import com.sss.booking.repository.ShowRepository;
import com.sss.booking.repository.ShowSeatRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowSeatService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Transactional
    public ShowDetailsDTO getSeats(Long showId) {
        ShowDetailsDTO showDetailsDTO = new ShowDetailsDTO();

        ShowModel showModel = showRepository.findById(showId).orElse(null);
        if(showModel==null) return null;

        showDetailsDTO.setShowModel(showModel);


        Integer screenLayoutId = showModel.getScreenLayoutId();
        ScreenLayout screenLayout = screenRepository.findById(screenLayoutId).orElse(null);
        if(screenLayout==null){
            return null;
        }
        showDetailsDTO.setScreenLayout(screenLayout);


        List<List<ShowSeat>> seats = new ArrayList<>();
        int rows = screenLayout.getRows();
        int columns = screenLayout.getColumns();

        for(int i=0;i<rows;i++){
            seats.add(new ArrayList<>());
            for(int j=0;j<columns;j++){
                seats.get(i).add(null);
            }
        }

        List<ShowSeat> showSeats = showModel.getSeats();

        for(ShowSeat seat: showSeats){
            if(seat.getStatus()!=-1 && seat.getLockTime()!= null
                    && seat.getLockTime().plusMinutes(10).isBefore(LocalDateTime.now())){
                seat.setLockTime(null);
                seat.setUserId(null);
                seat.setStatus(1);
            }
            seats.get(seat.getSeatRow()).set(seat.getSeatColumn(), seat);
        }

        ShowSeat seat;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(seats.get(i).get(j)==null){
                    seat = new ShowSeat();
                    seat.setStatus(2);
                    seats.get(i).set(j, seat);
                }
            }
        }
        showDetailsDTO.setShowSeats(seats);
        System.out.println(showDetailsDTO);
        return showDetailsDTO;
    }

    @Transactional
    public boolean lock(List<Integer> selectedSeats, long userId) {
        List<ShowSeat> showSeats = showSeatRepository.findAllById(selectedSeats);
        System.out.println(showSeats);
        for(ShowSeat seat: showSeats){
            if(seat.getStatus()!=1){
                return false;
            }
        }
        for (ShowSeat seat: showSeats){
            seat.setStatus(0);
            seat.setLockTime(LocalDateTime.now());
            seat.setUserId(userId);
        }
        System.out.println(showSeats);
        return true;
    }

    @Transactional
    public void bookSeats(List<Integer> seats, Long userId, Booking booking) {
        List<ShowSeat> showSeats = showSeatRepository.findAllById(seats);
        for (ShowSeat seat: showSeats){
            seat.setStatus(-1);
            seat.setUserId(userId);
            seat.setBooking(booking);
        }
    }
}
