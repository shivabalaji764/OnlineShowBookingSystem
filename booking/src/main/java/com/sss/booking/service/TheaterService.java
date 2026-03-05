package com.sss.booking.service;


import com.sss.booking.model.Theater;
import com.sss.booking.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getTheaters(int clientId) {
        return theaterRepository.findAllByClientClientId(clientId);
    }
}
