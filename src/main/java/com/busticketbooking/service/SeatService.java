package com.busticketbooking.service;

import com.busticketbooking.DTO.SeatDTO;
import org.springframework.data.domain.Page;

public interface SeatService {
    SeatDTO getSeatById(int id);
    SeatDTO createSeat(SeatDTO seatDTO);
    SeatDTO updateSeat(int id, SeatDTO updatedSeatDTO);
    void deleteSeat(int id);
    void deleteAllSeats();
    Page<SeatDTO> getAllSeats(int page, int size);
}

