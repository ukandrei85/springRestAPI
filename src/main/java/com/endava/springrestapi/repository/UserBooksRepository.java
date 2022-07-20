package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.UserBookList;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserBooksRepository extends JpaRepositoryImplementation<UserBookList,Integer> {
}
