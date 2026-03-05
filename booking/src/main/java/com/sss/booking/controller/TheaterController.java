package com.sss.booking.controller;


import com.sss.booking.model.ShowModel;
import com.sss.booking.model.Theater;
import com.sss.booking.service.TheaterService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/set")
    public ResponseEntity<String> setTheater(@RequestBody Theater request, HttpSession session){
        session.setAttribute("theaterId",request.getTheaterId());
        return ResponseEntity.ok("added");
    }

    @PostMapping("/addnew")
    public ResponseEntity<String> addTheater(@RequestBody Theater request, HttpSession session){
        Integer clientId = (Integer)session.getAttribute("clientId");
        if(clientId==null){
            return ResponseEntity.status(401).body("not authorised");
        }
        int theaterId = theaterService.addTheater(request, clientId);
        if(theaterId==-1){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorised");
        }
        session.setAttribute("theaterId", theaterId);
        return ResponseEntity.ok("added successfully");
    }
}
