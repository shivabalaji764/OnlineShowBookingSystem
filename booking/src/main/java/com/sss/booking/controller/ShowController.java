package com.sss.booking.controller;

import com.sss.booking.model.ShowModel;
import com.sss.booking.service.ShowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

        return ResponseEntity.ok("stored");
    }

}
