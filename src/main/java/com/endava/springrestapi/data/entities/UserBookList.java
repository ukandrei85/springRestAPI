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

    public UserBookList(User user, Book book) {
        this.user = user;
        this.book = book;
    }
}
