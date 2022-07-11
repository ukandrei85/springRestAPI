package com.endava.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rentalId;
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

    public Rental(User user, Book book, LocalDate startPeriod, LocalDate endPeriod) {
        this.user = user;
        this.book = book;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }
}
