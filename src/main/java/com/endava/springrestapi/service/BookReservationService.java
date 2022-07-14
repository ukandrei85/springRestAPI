package com.endava.springrestapi.service;

import com.endava.springrestapi.data.entities.ReservationPeriod;
import com.endava.springrestapi.repository.BookReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookReservationService {

    private final BookReservationRepository bookReservationRepository;

    public BookReservationService(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }

    public void reserveBook(int userId, int bookId, LocalDate startDate, ReservationPeriod reservationPeriod) {
        LocalDate endDate = startDate.plusWeeks(reservationPeriod.offsetInWeeks);

    }
}
