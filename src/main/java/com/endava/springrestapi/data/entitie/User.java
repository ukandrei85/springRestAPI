package com.endava.springrestapi.data.entitie;


import com.endava.springrestapi.data.api.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    @Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
    private String lastName;
    @Column(name = "city")
    @Size(min = 3, max = 30, message = "City must be between 3 and 30 characters")
    private String city;
    @Size(min = 5, max = 30, message = "Account login must be between 5 and 30 characters")
    @Column(name = "user_name", unique = true)
    private String username;
    @Column(name = "account_email", unique = true)
    @Email(message = "Should have email format")
    private String accountEmail;
    @Column(name = "active")
    private boolean active;
    @Column(name = "password", length = 1000)
    private String password;
    @ManyToMany(fetch =EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> booksList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBookList> userBookLists;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Rental> rental;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Reservation> bookReservations;

    public User(Integer id, String firstName, String lastName, String city, String username, String accountEmail, boolean active, String password, Collection<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.username = username;
        this.accountEmail = accountEmail;
        this.active = active;
        this.password = password;
        this.roles = roles;
    }
    public UserDto mapEntityToApi() {
        return new UserDto(this.getFirstName(), this.getLastName(), this.getCity(), this.getUsername(), this.getAccountEmail(), this.getPassword(), this.getRoles());
    }
}
