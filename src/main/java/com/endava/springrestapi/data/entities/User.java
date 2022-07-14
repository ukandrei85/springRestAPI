package com.endava.springrestapi.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "city")
    private String city;
    @Column(name = "account_login",unique = true)
    private String accountLogin;
    @Column(name = "account_email",unique = true)
    private String accountEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authentication_id")
    private Authentication authentication;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<UserBookList> userBookLists;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Rental> rental;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<BookReservation> bookReservations;


}
