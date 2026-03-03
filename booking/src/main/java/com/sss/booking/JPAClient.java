package com.sss.booking;

import com.sss.booking.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAClient extends JpaRepository<Client, Integer> {

}
