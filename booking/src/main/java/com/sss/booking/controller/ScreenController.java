package com.sss.booking.controller;


import com.sss.booking.model.ScreenLayout;
import com.sss.booking.service.ScreenService;
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
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @GetMapping("/get")
    public ResponseEntity<List<ScreenLayout>> getScreens(HttpSession session){
        Integer theaterId = (Integer) session.getAttribute("theaterId");
        if(theaterId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ScreenLayout> screens= screenService.getScreens(theaterId);

        return ResponseEntity.ok(screens);
    }

    @PostMapping("/set")
    public ResponseEntity<String> setScreen(@RequestBody ScreenLayout request, HttpSession session){
        int screenLayoutId = request.getScreenLayoutId();
        session.setAttribute("screenLayoutId", screenLayoutId);
        return ResponseEntity.ok("stored");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addScreen(@RequestBody ScreenLayout request, HttpSession session){
        Integer theaterId = (Integer) session.getAttribute("theaterId");
        System.out.println(theaterId);
        if(theaterId==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
        int screenLayoutId = screenService.addScreen(request, theaterId);
        if(screenLayoutId==-1){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Can't find Theater");
        }
        session.setAttribute("screenLayoutId", screenLayoutId);
        return ResponseEntity.ok("added successfully");
    }

}
