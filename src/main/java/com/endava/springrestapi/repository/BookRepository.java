package com.endava.springrestapi.repository;

import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface BookRepository extends JpaRepositoryImplementation<Book,Integer> {
    @Query("select b from Book  b where b.isRented=false  ")
    List<Book> findByIsRented();
}
