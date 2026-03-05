package com.sss.booking.controller;


import com.sss.booking.model.Theater;
import com.sss.booking.service.TheaterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(
        value = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping("/")
    public ResponseEntity<List<Theater>> getTheaters(HttpSession session){
        Integer clientId = (Integer) session.getAttribute("clientId");
        if(clientId==null){
            return ResponseEntity.status(401).build();
        }
        List<Theater> theater = theaterService.getTheaters(clientId);
        return ResponseEntity.ok(theater);
    }

}
