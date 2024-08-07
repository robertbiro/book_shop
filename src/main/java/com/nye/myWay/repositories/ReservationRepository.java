package com.nye.myWay.repositories;

import com.nye.myWay.dto.projections.AdminReservedBookProjection;
import com.nye.myWay.dto.projections.ReservedBookProjection;
import com.nye.myWay.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    //when make JOIN: JOIN r.book b
    //@Query("SELECT r.book AS book, r.createdAt AS createdAt FROM Reservation r WHERE r.applicationUser.id = ?1")
    @Query("SELECT r.book AS book, r.createdAt AS createdAt FROM Reservation r JOIN r.applicationUser u WHERE u.id = ?1")
    List<ReservedBookProjection> getAllReservedBooksByUser(Long userId);
    @Query("SELECT r FROM Reservation r WHERE r.id = ?1")
    Optional<Reservation> findReservedBookById(Long reservedBookId);

    @Query("SELECT u.username AS username, u.email AS email, " +
            "b.isbn AS isbn, b.title AS title, " +
            "r.createdAt AS createdAt " +
            "FROM Reservation r " +
            "JOIN r.book b " +
            "JOIN r.applicationUser u " +
            "WHERE b.author = ?1")
    List<AdminReservedBookProjection> findReservationByAuthor(String author);
}
