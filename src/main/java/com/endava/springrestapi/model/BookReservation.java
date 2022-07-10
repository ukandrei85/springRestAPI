package com.endava.springrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books_reservation")
public class BookReservation {
    private enum ReservedDatePeriod{
        ONE_WEEK(1),
        TWO_WEEKS(2),
        THREE_WEEKS(3),
        ONE_MONTH(4);
        private final long offsetInWeek;
        private ReservedDatePeriod (long offsetInWeek) {
            this.offsetInWeek = offsetInWeek;
        }

        public LocalDate getStartDate(LocalDate endDate) {
            LocalDate startMonth = endDate.minusMonths(offsetInWeek-1);
            return startMonth.withDayOfMonth(1);
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookReservationId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "start_period")
    private LocalDate startPeriod;

    @Column(name = "end_period")
    private LocalDate endPeriod;
}
