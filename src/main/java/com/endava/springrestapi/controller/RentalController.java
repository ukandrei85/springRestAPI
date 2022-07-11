package com.endava.springrestapi.controller;

import com.endava.springrestapi.model.Rental;
import com.endava.springrestapi.model.UserBookList;
import com.endava.springrestapi.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
  private  RentalService rentalService;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestParam("userId") int userId,
                       @RequestParam("bookId") int bookId,
                       @RequestParam("startDate") @DateTimeFormat(pattern="dd-MM-yyyy") LocalDate startDate,
                       @DateTimeFormat(pattern="dd-MM-yyyy") @RequestParam("endDate") LocalDate endDate) {
        rentalService.create(userId,bookId,startDate,endDate);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Rental> getAll() {
        return rentalService.getAll();
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.GET)
    public ResponseEntity<Rental> getById(@PathVariable int id) {
        return rentalService.findById(id);
    }

    @RequestMapping(value = ":id={id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        return rentalService.delete(id);
    }
}
