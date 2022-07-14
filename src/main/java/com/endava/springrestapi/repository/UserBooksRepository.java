package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entities.UserBookList;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserBooksRepository extends JpaRepositoryImplementation<UserBookList,Integer> {
}
