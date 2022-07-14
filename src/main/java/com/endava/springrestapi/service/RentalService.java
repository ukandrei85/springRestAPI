package com.endava.springrestapi.service;

import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.data.entities.Rental;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.RentalRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Rental create(int userId, int bookId, LocalDate startTime, LocalDate endTime) {
        Rental rental = new Rental(
                userRepository.getReferenceById(userId),
                bookRepository.getReferenceById(bookId), startTime, endTime
        );
        return rentalRepository.save(rental);

    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public ResponseEntity<Rental> findById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rented Book not exist with id=" + id));
        return ResponseEntity.ok(rental);
    }

    public ResponseEntity<HttpStatus> delete(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Rented Book not exist with id=" + id));
        rentalRepository.delete(rental);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Rental> findRentedBooks(int id) {
        return rentalRepository.findBooksReturnToOwnerById(id);
    }

}
