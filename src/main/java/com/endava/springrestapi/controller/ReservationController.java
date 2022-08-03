package com.endava.springrestapi.controller;

import com.endava.springrestapi.data.api.ReservationDto;
import com.endava.springrestapi.data.response.MessageResponse;
import com.endava.springrestapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> addReservation(@RequestBody ReservationDto reservationDto) {
        MessageResponse message =reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservDto = reservationService.getAllReservation();
        return new ResponseEntity<>(reservDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Integer id) {
        ReservationDto reservationDto= reservationService.getASingleReservation(id);
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<MessageResponse> updateReservation(@PathVariable Integer id, @RequestBody ReservationDto reservationDto) {
        MessageResponse message =reservationService.updateReservation(id,reservationDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Integer id) {
        MessageResponse message = reservationService.deleteReservation(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
