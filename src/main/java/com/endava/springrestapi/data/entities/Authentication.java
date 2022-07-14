package com.endava.springrestapi.data.entities;

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
@Table(name = "authentications")
public class Authentication {
    @Id
    private int user_id;
    @Column(name = "password")
    private String password;
    @OneToOne(mappedBy = "authentication")
    private User user;

    public Authentication(int user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }
}
