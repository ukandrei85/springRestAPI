package com.endava.springrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name="user_name",nullable = false)
    private String userName;
    @Column(name="user_address",nullable = false)
    private String userAddress;
    @Column(name="user_account_login")
    private String userAccountLogin;
    @Column(name="user_account_password")
    private String userAccountPassword;
    @Column(name="user_account_email")
    private String userAccountEmail;
}
