package com.sss.booking.controller;

import com.sss.booking.dto.SeatDTO;
import com.sss.booking.service.SeatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        value = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/addseats")
    public ResponseEntity<String> addSeats(@RequestBody SeatDTO request, HttpSession session){
        System.out.println(request);
        Integer screenLayoutId = (Integer) session.getAttribute("screenLayoutId");

        if (screenLayoutId==null){
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No screen found");
        }

        boolean added = seatService.addSeats(request.getSelectedSeats(), screenLayoutId);

        if(!added){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }
        return ResponseEntity.ok("done");
    }


}