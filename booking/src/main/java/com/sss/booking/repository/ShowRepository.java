package com.sss.booking.repository;

import com.sss.booking.model.ShowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel, Long> {
    List<ShowModel> findAllByClientClientId(int clientId);

    @Query("""
    SELECT s FROM ShowModel s
    WHERE s.id IN (
        SELECT MIN(s2.id)
        FROM ShowModel s2
        GROUP BY s2.showName
    )
    """)
    List<ShowModel> findUniqueShows();
}
