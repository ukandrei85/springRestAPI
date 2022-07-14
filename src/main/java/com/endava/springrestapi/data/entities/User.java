package com.endava.springrestapi.data.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "first_name")
    @Size(min = 3, max = 30, message
            = "First name must be between 3 and 30 characters")
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 3, max = 30, message
            = "Last name must be between 3 and 30 characters")
    private String lastName;
    @Column(name = "city")
    @Size(min = 3, max = 30, message
            = "City must be between 3 and 30 characters")
    private String city;
    @Size(min = 5, max = 30, message
            = "Account login must be between 5 and 30 characters")
    @Column(name = "account_login",unique = true)
    private String accountLogin;
    @Column(name = "account_email",unique = true)
    @Email(message = "Should have email format")
    private String accountEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Authentication authentication;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Book book;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<UserBookList> userBookLists;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Rental> rental;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<BookReservation> bookReservations;


}
