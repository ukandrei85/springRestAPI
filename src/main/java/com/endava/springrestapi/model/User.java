package com.endava.springrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value={ "userBookLists" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "user_name", nullable = false)
    @Size(min=5,max =50,message = "Name shold be between 5 and 50 characters")
    private String userName;
    @Size(min=10,max =100,message = "Name shold be between 10 and 100 characters")
    @Column(name = "user_address", nullable = false)
    private String userAddress;
    @Column(name = "user_account_login",unique = true,nullable = false)
    private String userAccountLogin;
    @Column(name = "user_account_password",nullable = false)
    private String userAccountPassword;
    @Column(name = "user_account_email", nullable = false)
    @Email
    private String userAccountEmail;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    List<UserBookList> userBookLists;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    List<Rental> rental;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    List<BookReservation>  bookReservations;



}
