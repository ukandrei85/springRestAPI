package com.endava.springrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_books_list")
public class UserBookList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userBookId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    public UserBookList(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
