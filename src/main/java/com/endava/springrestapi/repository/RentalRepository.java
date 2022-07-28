package com.endava.springrestapi.repository;

import com.endava.springrestapi.data.entitie.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface RentalRepository extends JpaRepositoryImplementation<Rental, Integer> {
    @Query("select r from Rental r where r.book.isRented=true and r.user.id=:id ")
    List<Rental> findBooksRentedByUserId(Integer id);

    @Query(" SELECT r FROM Rental r WHERE r.book.isRented=true and r.book.owner.id=:id")
    List<Rental> findRentedBooksByOwnerId(Integer id);

}
