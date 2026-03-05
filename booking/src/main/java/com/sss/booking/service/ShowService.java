package com.sss.booking.service;

import com.sss.booking.model.ShowModel;
import com.sss.booking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public List<ShowModel> getShows() {
        return showRepository.findUniqueShows();
    }
}
