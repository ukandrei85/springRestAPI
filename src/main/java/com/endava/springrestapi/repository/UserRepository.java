package com.endava.springrestapi.repository;

import com.endava.springrestapi.model.User;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<User,Integer> {
}
