package com.endava.springrestapi.service;

import com.endava.springrestapi.data.api.ReservationDto;
import com.endava.springrestapi.data.response.MessageResponse;
import java.util.List;

public interface ReservationService {
    MessageResponse createReservation(ReservationDto reservationDto);
    MessageResponse  updateReservation(Integer id, ReservationDto reservationDto);
    MessageResponse deleteReservation(Integer id);
    ReservationDto getASingleReservation(Integer id);
    List<ReservationDto> getAllReservation();
}
