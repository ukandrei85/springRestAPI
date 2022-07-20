package com.endava.springrestapi.data.entitie;



import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_title")
    @Size(min = 3, max = 30, message
            = "Book title must be between 3 and 30 characters")
    private String title;
    @Size(min = 5, max = 50, message
            = "Book author must be between 5 and 50 characters")
    @Column(name = "book_author")
    private String author;
    @Column(name = "book_isbn")
    @Min(value = 1000000000000L, message = "ISBN should not be less than 1000000000000")
    @Max(value = 9999999999999L, message = "ISBN should not be greater than 1999999999999")
    private Long isbn;
    @Column(name = "is_rented",columnDefinition = "boolean default false")
    private Boolean isRented;
    @Column(name = "is_reserved",columnDefinition = "boolean default false")
    private Boolean isReserved;
    @Column(name = "end_rent_period")
    private LocalDate endRentPeriod;
    @ManyToOne(optional=false)
    private User owner;
    @OneToMany( mappedBy = "user")
    private List<UserBookList> userBookLists;
    @OneToMany( mappedBy = "user")
    private List<Rental> rental;
    @OneToMany( mappedBy = "user")
    private List<Reservation> bookReservations;
}
