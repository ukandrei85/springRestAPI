package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface BookRepository extends JpaRepositoryImplementation<Book,Integer> {
    @Query("select b from Book  b where b.isRented=false  ")
    List<Book> findByIsNotRented();
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:search% OR b.author LIKE %:search% ")
    List<Book> findBooksByTitleOrAuthor(String search);
}
