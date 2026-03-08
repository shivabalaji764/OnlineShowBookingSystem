package com.sss.booking.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String mobileNo;
    private String userEmail;

    @ManyToMany
    private List<ShowModel> shows;
}
