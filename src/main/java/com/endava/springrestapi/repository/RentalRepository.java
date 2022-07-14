package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entities.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface RentalRepository extends JpaRepositoryImplementation<Rental,Integer> {
    @Query("select r from Rental r where r.user.userId= :id and r.endPeriod>= current_date ")
    List<Rental>  findBooksReturnToOwnerById(Integer id);
    @Query("select r from Rental r where r.endPeriod >= current_date ")
    List<Rental> findActiveRents();
}
