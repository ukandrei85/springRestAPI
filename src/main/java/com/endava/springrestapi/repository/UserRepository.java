package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.User;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<User,Integer> {
   // @Query("select u from User u where u.username=:userName  ")
    User findUserByUsername(String username);
}
