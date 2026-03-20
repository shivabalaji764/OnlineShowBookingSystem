package com.sss.booking.controller;

import com.sss.booking.dto.TheaterDTO;
import com.sss.booking.model.ShowModel;
import com.sss.booking.model.Theater;
import com.sss.booking.service.TheaterService;
import com.sss.booking.service.UserService;
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
@RequestMapping("/usershows")
public class UserShowsController {

    @Autowired
    private UserService userService;

    @Autowired
    private TheaterService theaterService;

    @GetMapping("/shows")
    public ResponseEntity<List<String>> getShows(){
        List<String> shows = userService.getShows();
        if(shows.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(shows);
    }

    @PostMapping("/setshow")
    public ResponseEntity<String> setShow(@RequestBody ShowModel showModel, HttpSession session){
        if(session.getAttribute("userId")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login first");
        }
        session.setAttribute("showName", showModel.getShowName());
        System.out.println(showModel.getShowName());
        return ResponseEntity.ok().body("stored");
    }

    @GetMapping("/gettheaters")
    public ResponseEntity<List<TheaterDTO>> getTheaters(HttpSession session){
        String showName = (String) session.getAttribute("showName");
        if(session.getAttribute("userId")==null || showName==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(theaterService.getTheaters(showName));
    }

    @PostMapping("/settheater")
    public ResponseEntity<String> setTheater(@RequestBody ShowModel showModel, HttpSession session){
        if(session.getAttribute("userId")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login first");
        }
        session.setAttribute("showId", showModel.getShowId());
        System.out.println((Long) session.getAttribute("showId"));
        return ResponseEntity.ok("successful");
    }
}
