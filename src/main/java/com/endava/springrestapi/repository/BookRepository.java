package com.endava.springrestapi.repository;

import com.endava.springrestapi.model.Book;
import com.endava.springrestapi.model.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface BookRepository extends JpaRepositoryImplementation<Book,Integer> {
}
