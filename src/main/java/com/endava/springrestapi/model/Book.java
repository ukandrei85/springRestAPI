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
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="book_title",nullable = false)
    private String title;
    @Column(name="book_isbn",nullable = false)
    private long isbn;
    @Column(name="book_owner",nullable = false)
    private String owner;
    @Column(name="is_rented")
    private boolean isRented;
    @Column(name="is_reserved")
    private boolean isReserved;
}
