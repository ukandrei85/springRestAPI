package com.endava.springrestapi.repository;

import com.endava.springrestapi.model.BookReservation;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface BookReservationRepository extends JpaRepositoryImplementation<BookReservation,Integer> {
}
