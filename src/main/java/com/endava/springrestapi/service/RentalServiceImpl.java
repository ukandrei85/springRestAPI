package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.RentalDto;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Rental;
import com.endava.springrestapi.data.entitie.User;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.RentalRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public MessageResponse createRental(RentalDto rentalDto) throws ResourceNotFoundException {

        Book book = bookRepository.findById(rentalDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Book with id:" + rentalDto.getBookId()));
        if (!book.getIsRented() && book.getEndRentPeriod() == null) {
            User user = userRepository.findById(rentalDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found  User with id:" + rentalDto.getUserId()));
            Rental rental = new Rental();
            rental.setUser(user);
            rental.setBook(book);
            rental.setStartPeriod(rentalDto.getStartPeriod());
            rental.setEndPeriod(rentalDto.getEndPeriod());
            book.setIsRented(true);
            book.setEndRentPeriod(rentalDto.getEndPeriod());
            rentalRepository.save(rental);
            return new MessageResponse("Rental created successfully");
        } else return new MessageResponse("The book cannot be rented");

    }

    @Override
    public MessageResponse updateRental(Integer id, RentalDto rentalDto) throws ResourceNotFoundException {
        Rental updateRental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Rental with id" + id));
        User user = userRepository.findById(rentalDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found  User with id:" + rentalDto.getUserId()));
        Book book = bookRepository.findById(rentalDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Book with id:" + rentalDto.getBookId()));
        updateRental.setBook(book);
        updateRental.setUser(user);
        updateRental.setStartPeriod(rentalDto.getStartPeriod());
        updateRental.setEndPeriod(rentalDto.getEndPeriod());
        rentalRepository.save(updateRental);
        return new MessageResponse("Rental updated successfully");
    }

    @Override
    public MessageResponse deleteRental(Integer id) throws ResourceNotFoundException {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Rental with id:" + id));
        rentalRepository.delete(rental);
        return new MessageResponse("Rental deleted successfully");
    }

    @Override
    public RentalDto getASingleRental(Integer id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Rental with id:" + id));

        return mapEntityToApi(rental);
    }

    @Override
    public List<RentalDto> getAllRental() {
        return rentalRepository.findAll().stream().map(this::mapEntityToApi).toList();
    }

    public RentalDto mapEntityToApi(Rental rental) {
        return new RentalDto(rental.getUser().getId(), rental.getBook().getId(), rental.getStartPeriod(), rental.getEndPeriod());
    }

}
