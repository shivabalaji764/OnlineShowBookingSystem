package com.sss.booking.repository;

import com.sss.booking.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    public Optional<Client> findByEmail(String email);

}
