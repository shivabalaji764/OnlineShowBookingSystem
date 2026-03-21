package com.sss.booking.controller;


import com.sss.booking.dto.ShowDetailsDTO;
import com.sss.booking.dto.ShowSeatsDTO;
import com.sss.booking.service.ShowSeatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/showseats")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @GetMapping("/get")
    public ResponseEntity<ShowDetailsDTO> getSeats(HttpSession session){
        Long showId = (Long) session.getAttribute("showId");
        if(session.getAttribute("userId")==null || showId==null){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(showSeatService.getSeats(showId));
    }

    @PostMapping("/set")
    public ResponseEntity<String> addBookings(@RequestBody ShowSeatsDTO seats, HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if(userId==null || session.getAttribute("showId")==null){
            System.out.println("null");
            return ResponseEntity.status(401).body("login");
        }
        System.out.println("ok");
        boolean available = showSeatService.lock(seats.getSelectedSeats(), userId);
        if(!available){
            return ResponseEntity.status(423).body("unavailable");
        }
        System.out.println("available");
        session.setAttribute("seats", seats.getSelectedSeats());
        session.setAttribute("cost", seats.getCost());

        System.out.println("stored"+" "+session.getAttribute("seats"));
        return ResponseEntity.ok("stored");
    }
}
