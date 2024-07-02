package com.nye.myWay.repositories;

import com.nye.myWay.dto.projections.ReservedBookProjection;
import com.nye.myWay.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //when make JOIN: JOIN r.book b
    @Query("SELECT r.book AS book, r.createdAt AS createdAt FROM Reservation r WHERE r.applicationUser.id = ?1")
    List<ReservedBookProjection> getAllReservedBooksByUser(Long userId);
}
