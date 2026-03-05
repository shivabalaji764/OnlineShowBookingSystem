package com.sss.booking.repository;

import com.sss.booking.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    List<Theater> findAllByClientClientId(int ClientId);
}
