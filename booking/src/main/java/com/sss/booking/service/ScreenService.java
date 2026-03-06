package com.sss.booking.service;

import com.sss.booking.model.ScreenLayout;
import com.sss.booking.model.Theater;
import com.sss.booking.repository.ScreenRepository;
import com.sss.booking.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public List<ScreenLayout> getScreens(Integer theaterId) {
        return screenRepository.findAllByTheaterTheaterId(theaterId);
    }

    public int addScreen(ScreenLayout request, int theaterId) {
        Optional<Theater> theater = theaterRepository.findById(theaterId);
        if(theater.isEmpty()){
            return -1;
        }
        ScreenLayout screen = new ScreenLayout();
        screen.setScreenNo(request.getScreenNo());
        screen.setScreenType(request.getScreenType());
        screen.setBasePrice(request.getBasePrice());
        screen.setRows(request.getRows());
        screen.setColumns(request.getColumns());
        screen.setTheater(theater.get());

        screenRepository.save(screen);

        return screen.getScreenLayoutId();
    }

    public ScreenLayout getScreen(Integer screenLayoutId) {
        Optional<ScreenLayout> screenLayout = screenRepository.findById(screenLayoutId);
        return screenLayout.orElse(null);
    }
}
