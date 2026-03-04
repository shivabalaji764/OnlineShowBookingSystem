package com.sss.booking.service;

import com.sss.booking.model.Client;
import com.sss.booking.model.ShowModel;
import com.sss.booking.repository.ClientRepository;
import com.sss.booking.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShowRepository showRepository;

    public int login(String email, String password) {
        Optional<Client> client= clientRepository.findByEmail(email);
        if(client.isEmpty()){
            return -1;
        }
        Client client1 = client.get();
        if(password.equals(client1.getPassword())){
            return client1.getClientId();
        }
        return -1;
    }

    public boolean addClient(Client client) {
        try{
            clientRepository.save(client);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<ShowModel> getShows(Integer clientId) {
        return showRepository.findAllByClientClientId(clientId);
    }
}
