package com.endava.springrestapi.data.api;

import com.endava.springrestapi.data.entitie.ReservationPeriod;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data

public class ReservationDto {
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer userId;
    @Pattern(regexp = "^[0-9]{10}$")
    private Integer bookId;
    @FutureOrPresent
    private LocalDate startDate;
    private ReservationPeriod period;
    private LocalDate endDate ;

    public ReservationDto(Integer userId, Integer bookId, LocalDate startDate, ReservationPeriod period) {
        this.userId = userId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.period = period;
        this.endDate = startDate.plusWeeks(period.offsetInWeeks);
    }


}
