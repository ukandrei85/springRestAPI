package com.endava.springrestapi.repository;

import com.endava.springrestapi.model.Rental;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface RentalRepository extends JpaRepositoryImplementation<Rental,Integer> {
}
