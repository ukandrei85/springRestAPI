package com.endava.springrestapi.scheduler;

import com.endava.springrestapi.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class RentalReseter {
    @Autowired
    private BookRepository bookRepository;


    @Scheduled(cron = "0 0 0 * * *")
    public void resetRental() {
        LocalDate today = LocalDate.now();

        bookRepository.findAll().stream()
                .filter(b -> b.getEndRentPeriod() != null)
                .filter(b -> b.getEndRentPeriod().isBefore(today))
                .forEach(b -> {
                    b.setEndRentPeriod(null);
                    b.setIsRented(false);
                    bookRepository.save(b);
                    logger.info("Book with id {} is free", b.getId());
                });

    }
}
