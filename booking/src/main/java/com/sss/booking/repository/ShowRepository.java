package com.sss.booking.repository;

import com.sss.booking.model.ShowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel, Long> {
    List<ShowModel> findAllByClientClientId(int clientId);
}
