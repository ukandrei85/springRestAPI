package com.endava.springrestapi.data.entities;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user_books_list")
public class UserBookList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userBookId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


}
