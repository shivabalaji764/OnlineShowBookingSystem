package com.sss.booking.service;

import com.sss.booking.model.ShowModel;
import com.sss.booking.model.Users;
import com.sss.booking.repository.ShowRepository;
import com.sss.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;


    public long login(String userEmail, String password){
        Optional<Users> usersOptional = userRepository.findByUserEmail(userEmail);
        if(usersOptional.isEmpty()){
            return -1L;
        }
        Users user = usersOptional.get();
        if(user.getPassword().equals(password)){
            return user.getUserId();
        }
        return -1L;
    }

    public boolean signup(Users user) {
        try{
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<String> getShows() {
        return showRepository.getUniqueShowNames();
    }
}
