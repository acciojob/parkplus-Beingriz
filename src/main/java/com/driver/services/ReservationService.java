package com.driver.services;

import com.driver.dto.responseDTO.ReservationResponse;
import com.driver.model.Reservation;

public interface ReservationService {
    ReservationResponse reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception;
}
