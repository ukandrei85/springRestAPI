package com.endava.springrestapi.service;

import com.endava.springrestapi.model.BookReservation;
import com.endava.springrestapi.model.ReservationPeriod;
import com.endava.springrestapi.repository.BookReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookReservationService {

    private final  BookReservationRepository bookReservationRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    public void reserveBook(int userId, int bookId, LocalDate startDate, ReservationPeriod reservationPeriod){
        LocalDate endDate = startDate.plusWeeks(reservationPeriod.offsetInWeeks);

      //  bookReservationRepository.save(new BookReservation(null, ));
    }
}
