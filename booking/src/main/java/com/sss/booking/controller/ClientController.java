package com.sss.booking.controller;


import com.sss.booking.model.Client;
import com.sss.booking.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Client request){
        boolean isValid = clientService.login(request.getEmail(), request.getPassword());

        if(isValid){
            return ResponseEntity.ok("Login successful");
        }else{
            return ResponseEntity.status(401)
                    .body("Wrong credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Client client){
        boolean added = clientService.addClient(client);
        if(added){
            return ResponseEntity.ok("Sign up successful");
        }else{
            return ResponseEntity.status(402)
                    .body("Something went wrong");
        }
    }
}
