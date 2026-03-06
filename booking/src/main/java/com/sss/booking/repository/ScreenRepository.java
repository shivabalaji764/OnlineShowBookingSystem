package com.sss.booking.repository;

import com.sss.booking.model.ScreenLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenLayout, Integer> {
    List<ScreenLayout> findAllByTheaterTheaterId(int theaterId);
}
