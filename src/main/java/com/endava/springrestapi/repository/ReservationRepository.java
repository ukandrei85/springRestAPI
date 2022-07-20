package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface ReservationRepository extends JpaRepositoryImplementation<Reservation,Integer> {
    @Query("select r from Reservation r where r.book.id= :id ")
    List<Reservation> findReservationByBookId(Integer id);
}
