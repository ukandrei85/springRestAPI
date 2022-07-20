package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.RentalDto;
import com.endava.springrestapi.data.api.UserBookListDto;
import com.endava.springrestapi.data.entitie.Book;
import com.endava.springrestapi.data.entitie.Rental;
import com.endava.springrestapi.data.response.MessageResponse;

import java.util.List;

public interface RentalService {
    MessageResponse createRental(RentalDto rentalDto);
    MessageResponse  updateRental(Integer id, RentalDto rentalDto);
    MessageResponse deleteRental(Integer id);
    RentalDto getASingleRental(Integer id);
    List<RentalDto> getAllRental();

}
