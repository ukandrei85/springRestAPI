package com.endava.springrestapi.data.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentalDto {
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer userId;
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer bookId;
    @FutureOrPresent
    private LocalDate startPeriod;
    @Future
    private LocalDate endPeriod;
}
