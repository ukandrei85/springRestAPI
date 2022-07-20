package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.ReservationDto;
import com.endava.springrestapi.data.entitie.*;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.exception.ResourceNotFoundException;
import com.endava.springrestapi.repository.BookRepository;
import com.endava.springrestapi.repository.ReservationRepository;
import com.endava.springrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public MessageResponse createReservation(ReservationDto reservationDto) throws ResourceNotFoundException {
   if(checkToReserve(reservationDto)){
          Book book = bookRepository.findById(reservationDto.getBookId())
                  .orElseThrow(() -> new ResourceNotFoundException("Not found  Book with id:" + reservationDto.getBookId()));
          User user = userRepository.findById(reservationDto.getUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("Not found  User with id:" + reservationDto.getUserId()));
          Reservation reservation=new Reservation();
          reservation.setUser(user);
          reservation.setBook(book);
          reservation.setStartDate(reservationDto.getStartDate());
          reservation.setPeriod(reservationDto.getPeriod());
          reservation.setEndDate(reservationDto.getStartDate().plusWeeks(reservationDto.getPeriod().offsetInWeeks));
          book.setIsReserved(true);
          reservationRepository.saveAndFlush(reservation);
          return new MessageResponse("Reservation created successfully");
  }
       return new MessageResponse("The book cannot be reserved on this period");
    }

    @Override
    public MessageResponse updateReservation(Integer id, ReservationDto reservationDto) throws ResourceNotFoundException{
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Reservation with id:" + id));
        Book book = bookRepository.findById(reservationDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Book with id:" + reservationDto.getBookId()));
        User user = userRepository.findById(reservationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found  User with id:" + reservationDto.getUserId()));
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setStartDate(reservationDto.getStartDate());
        reservation.setPeriod(reservationDto.getPeriod());
        book.setIsReserved(true);
        reservationRepository.saveAndFlush(reservation);
        return new MessageResponse("Reservation updated successfully");
    }

    @Override
    public MessageResponse deleteReservation(Integer id) throws ResourceNotFoundException {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found  Reservation with id:" + id));
        reservationRepository.delete(reservation);
       return new MessageResponse("Reservation deleted successfully");
    }

    @Override
    public ReservationDto getASingleReservation(Integer id) {
        Reservation reservation=reservationRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Not found  Reservation with id:" + id));
        return mapEntityToApi(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservation() {
        return reservationRepository.findAll().stream().map(this::mapEntityToApi).toList();
    }
    public boolean checkToReserve(ReservationDto reservationDto){
   //     int count=0;
     return reservationRepository.findReservationByBookId(reservationDto.getBookId()).stream()
               .noneMatch(b->(reservationDto.getStartDate().isAfter(b.getStartDate()))&&(reservationDto.getEndDate().isBefore(b.getEndDate())));
//        for (  Reservation reservation:reservationRepository.findReservationByBookId(reservationDto.getBookId())) {
//            if(!(reservationDto.getStartDate().isAfter(reservation.getStartDate()))
//                    &&(!(reservationDto.getEndDate().isBefore(reservation.getEndDate())))) {
//                count++;
//            }
//        }
//        return (count > 0)? false : true;
    }

    public ReservationDto mapEntityToApi(Reservation reservation) {
        return new ReservationDto(reservation.getUser().getId(),reservation.getBook().getId(),
                reservation.getStartDate(),reservation.getPeriod());
    }
}