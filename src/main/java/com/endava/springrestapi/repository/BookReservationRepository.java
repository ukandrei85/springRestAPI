package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entities.BookReservation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface BookReservationRepository extends JpaRepositoryImplementation<BookReservation,Integer> {
}
