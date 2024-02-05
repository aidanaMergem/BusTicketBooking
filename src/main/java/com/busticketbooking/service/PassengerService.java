package com.busticketbooking.service;

import com.busticketbooking.DTO.PassengerDTO;
import org.springframework.data.domain.Page;

public interface PassengerService {
    PassengerDTO getPassengerById(int id);
    PassengerDTO createPassenger(PassengerDTO passengerDTO);
    PassengerDTO updatePassenger(int id, PassengerDTO updatedPassengerDTO);
    void deletePassenger(int id);
    void deleteAllPassengers();
    Page<PassengerDTO> getAllPassengers(int page, int size);
}
