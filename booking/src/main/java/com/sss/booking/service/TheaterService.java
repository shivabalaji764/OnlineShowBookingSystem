package com.sss.booking.service;


import com.sss.booking.model.Client;
import com.sss.booking.model.Theater;
import com.sss.booking.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ClientService clientService;

    public List<Theater> getTheaters(int clientId) {
        return theaterRepository.findAllByClientClientId(clientId);
    }


    public int addTheater(Theater request, Integer clientId) {
        Client client = clientService.getClient(clientId);
        if(client==null){
            return -1;
        }
        Theater newTheater = new Theater();
        newTheater.setScreen_count(0);
        newTheater.setTheaterName(request.getTheaterName());
        newTheater.setLongitude(request.getLongitude());
        newTheater.setLatitude(request.getLatitude());
        newTheater.setAddress(request.getAddress());
        newTheater.setClient(client);
        theaterRepository.save(newTheater);
        return newTheater.getTheaterId();
    }
}
