package com.sss.booking.service;

import com.sss.booking.model.Client;
import com.sss.booking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean login(String email, String password) {
        Optional<Client> client= clientRepository.findByEmail(email);
        if(client.isEmpty()){
            return false;
        }
        Client client1 = client.get();
        return password.equals(client1.getPassword());
    }

    public boolean addClient(Client client) {
        try{
            clientRepository.save(client);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
