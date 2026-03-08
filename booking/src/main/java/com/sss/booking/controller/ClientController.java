package com.sss.booking.controller;


import com.sss.booking.model.Client;
import com.sss.booking.model.ShowModel;
import com.sss.booking.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Client request, HttpSession session){
        int clientId = clientService.login(request.getEmail(), request.getPassword());

        if(clientId!=-1){
            session.setAttribute("clientId", clientId);
            System.out.println(clientId);
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

    @GetMapping("/shows")
    public ResponseEntity<List<ShowModel>> getShows(HttpSession session){
        Integer client_id = (Integer)session.getAttribute("clientId");
        if(client_id==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ShowModel> shows = clientService.getShows(client_id);
        return ResponseEntity.ok(shows);
    }
}
