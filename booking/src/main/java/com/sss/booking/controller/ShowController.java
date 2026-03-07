package com.sss.booking.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sss.booking.dto.ShowDTO;
import com.sss.booking.model.ShowModel;
import com.sss.booking.service.ShowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;



    @GetMapping("/")
    public ResponseEntity<List<ShowModel>> getShows(){
        List<ShowModel> shows= showService.getShows();
        return ResponseEntity.ok(shows);
    }

    @PostMapping("setshow")
    public ResponseEntity<String> setShow(@RequestBody ShowModel request, HttpSession session){
        session.setAttribute("showName", request.getShowName());
        String showName = (String) session.getAttribute("showName");
        System.out.println(showName+" "+request.getShowName());

        return ResponseEntity.ok("stored");
    }


    @PostMapping("/finalize")
    public ResponseEntity<String> addShow(@RequestBody ShowDTO request, HttpSession session){
        String showName = (String) session.getAttribute("showName");
        Integer clientId = (Integer) session.getAttribute("clientId");
        Integer theaterId = (Integer) session.getAttribute("theaterId");
        Integer screenLayoutId = (Integer) session.getAttribute("screenLayoutId");
        System.out.println(showName+" "+clientId+" "+theaterId+" "+screenLayoutId);
        if( showName==null || clientId==null || theaterId==null || screenLayoutId==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
        boolean added = showService.addShow(showName, clientId, theaterId, screenLayoutId, request.getShowTime(), request.getShowLength());
        if(!added){
            return ResponseEntity.status(402).body("failed");
        }
        return ResponseEntity.ok("Done");
    }
}
