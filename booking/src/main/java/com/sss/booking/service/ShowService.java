package com.sss.booking.service;

import com.sss.booking.model.Client;
import com.sss.booking.model.Seat;
import com.sss.booking.model.ShowModel;
import com.sss.booking.model.ShowSeat;
import com.sss.booking.repository.ClientRepository;
import com.sss.booking.repository.SeatRepository;
import com.sss.booking.repository.ShowRepository;
import com.sss.booking.repository.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public List<ShowModel> getShows() {
        return showRepository.findUniqueShows();
    }

    public boolean addShow(String showName, Integer clientId, Integer theaterId, Integer screenLayoutId, LocalDateTime showTime, LocalTime showLength) {
        ShowModel showModel = new ShowModel();
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(optionalClient.isEmpty()){
            return false;
        }
        showModel.setShowName(showName);
        showModel.setClient(optionalClient.get());
        showModel.setScreenLayoutId(screenLayoutId);
        showModel.setTheaterId(theaterId);
        showModel.setShowDateAndTime(showTime);
        showModel.setShowLength(showLength);
        List<Seat> seats = seatRepository.findAllByScreenScreenLayoutId(screenLayoutId);
        if(seats==null){
            return false;
        }
        showRepository.save(showModel);
        for(Seat i: seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShowModel(showModel);
            showSeat.setSeatRow(i.getSeatRow());
            showSeat.setSeatColumn(i.getSeatColumn());
            showSeat.setStatus(1);

            showSeatRepository.save(showSeat);
        }
        return true;
    }
}
