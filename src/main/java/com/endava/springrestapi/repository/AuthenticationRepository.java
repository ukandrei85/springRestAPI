package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entities.Authentication;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface AuthenticationRepository extends JpaRepositoryImplementation<Authentication,Integer> {
}
