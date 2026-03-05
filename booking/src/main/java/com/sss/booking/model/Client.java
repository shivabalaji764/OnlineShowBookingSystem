package com.sss.booking.model;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    private String name;
    private String password;
    private String email;
    private String mobile;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Theater> theaters;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ShowModel> shows;

    public Client() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }

    public List<ShowModel> getShows() {
        return shows;
    }

    public void setShows(List<ShowModel> shows) {
        this.shows = shows;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_id=" + clientId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", theaters=" + theaters +
                '}';
    }
}
