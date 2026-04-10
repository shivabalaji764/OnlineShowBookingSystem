package com.sss.booking.controller;


import com.sss.booking.dto.BookingDTO;
import com.sss.booking.dto.DetailsDTO;
import com.sss.booking.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public ResponseEntity<DetailsDTO> getDetails(HttpSession session){
        Long showId  = (Long) session.getAttribute("showId");
        @SuppressWarnings("unchecked")
        List<Integer> seats = (List<Integer>) session.getAttribute("seats");
        Double cost = (Double) session.getAttribute("cost");

        if(showId==null || seats==null || cost==null){
            System.out.println(showId+" "+seats+" "+cost);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        DetailsDTO details = bookingService.getDetails(showId, seats, cost);
        if(details==null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(details);
    }


    @PostMapping("/confirm")
    public ResponseEntity<String> confirmBooking(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        Long showId  = (Long) session.getAttribute("showId");
        @SuppressWarnings("unchecked")
        List<Integer> seats = (List<Integer>) session.getAttribute("seats");
        Double cost = (Double) session.getAttribute("cost");
        Boolean confirmedBooking = bookingService.confirmBooking(userId, showId, seats, cost);
        System.out.println(confirmedBooking);
        if(!confirmedBooking) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        return ResponseEntity.ok("Done");
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookingDTO>> getBookings(HttpSession session){
        Long userId = (Long) session.getAttribute("userId");
        if(userId==null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(bookingService.getBookings(userId));
    }
}
