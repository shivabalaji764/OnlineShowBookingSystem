package com.sss.booking.controller;

import com.sss.booking.model.Users;
import com.sss.booking.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user, HttpSession session){
        long userId = userService.login(user.getUserEmail(), user.getPassword());
        if(userId!=-1L){
            session.setAttribute("userId", userId);
            return ResponseEntity.ok("valid");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid login");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Users user){
        if(userService.signup(user)){
            return ResponseEntity.ok("valid");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error at user signup");
    }

}
