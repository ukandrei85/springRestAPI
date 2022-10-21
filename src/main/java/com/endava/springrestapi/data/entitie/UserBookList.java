package com.endava.springrestapi.data.entitie;

import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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
