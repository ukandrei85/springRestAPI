package com.endava.springrestapi.data.entitie;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "authentications")
public class Authentication {
    @Id
    @Column(name = "user_id")
    @Positive( message = "Id should not be less than 1")
    @Max(value = 2147483647, message = "Id should not be greater than 2147483647")
    private Integer userId;
    @Column(name = "password")
    @Size(min = 6, max = 30, message
            = "Password must be between 6 and 30 characters")
    private String password;
    @OneToOne(mappedBy = "authentication")
    private User user;


}
