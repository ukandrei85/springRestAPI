package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.RentalDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
  private RentalService rentalService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addRental(@RequestBody RentalDto rentalDto) {
        MessageResponse message =rentalService.createRental(rentalDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<RentalDto>> getAllRental() {
        List<RentalDto> rentalDto = rentalService.getAllRental();
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<RentalDto> getRentalById(@PathVariable("id") Integer id) {
        RentalDto rentalDto = rentalService.getASingleRental(id);
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Integer id, @RequestBody RentalDto rentalDto) {
        MessageResponse message =rentalService.updateRental(id,rentalDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Integer id) {
        MessageResponse message = rentalService.deleteRental(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
