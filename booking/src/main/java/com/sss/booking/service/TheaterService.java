package com.sss.booking.service;


import com.sss.booking.dto.TheaterDTO;
import com.sss.booking.model.Client;
import com.sss.booking.model.ShowModel;
import com.sss.booking.model.Theater;
import com.sss.booking.repository.ShowRepository;
import com.sss.booking.repository.TheaterRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private ShowRepository showRepository;


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


    public List<TheaterDTO> getTheaters(String showName){
        List<ShowModel> showModels = showRepository.findByShowName(showName);

        List<Integer> theaterIds = showModels
                .stream()
                .map(ShowModel::getTheaterId)
                .distinct()
                .toList();

        List<String> theaterNames =  getTheaters(theaterIds);

        System.out.println(theaterIds);
        System.out.println(theaterNames);

        List<TheaterDTO> theaters = new ArrayList<>();
        TheaterDTO theaterDTO;

        for(int i=0;i<theaterIds.size();i++){
            theaterDTO = new TheaterDTO();
            theaterDTO.setTheaterName(theaterNames.get(i));
            theaterDTO.setShowDate(new ArrayList<>());
            theaterDTO.setShowTime(new ArrayList<>());
            theaterDTO.setShowId(new ArrayList<>());
            for(ShowModel showModel : showModels){
                if(showModel.getTheaterId()==theaterIds.get(i)){
                    theaterDTO.getShowDate().add(showModel.getShowDateAndTime().toLocalDate());
                    theaterDTO.getShowTime().add(showModel.getShowDateAndTime().toLocalTime());
                    theaterDTO.getShowId().add(showModel.getShowId());
                }
            }
            theaters.add(theaterDTO);
        }

        return theaters;
    }

    public List<String> getTheaters(List<Integer> ids) {

        Map<Integer, Theater> map = theaterRepository.findAllById(ids)
                .stream()
                .collect(Collectors.toMap(Theater::getTheaterId, t -> t));

        return ids.stream()
                .map(id -> map.get(id).getTheaterName())
                .toList();
    }
}
