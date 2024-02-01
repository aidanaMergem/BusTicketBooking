package com.busticketbooking.service.impl;

import com.busticketbooking.DTO.SeatDTO;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.Seat;
import com.busticketbooking.repository.SeatRepository;
import com.busticketbooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepo;


        @Override
        public SeatDTO getSeatById(int id) {
            Seat seat = seatRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat", "ID", (long) id));
            return convertToSeatDTO(seat);
        }

        @Override
        public SeatDTO createSeat(SeatDTO seatDTO) {
            Seat seat = convertToSeatEntity(seatDTO);
            seat = seatRepo.save(seat);
            return convertToSeatDTO(seat);
        }

        @Override
        public SeatDTO updateSeat(int id, SeatDTO updatedSeatDTO) {
            Seat existingSeat = seatRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat", "ID", (long) id));

            // Update existingSeat fields if needed

            existingSeat.setModifiedAt(LocalDateTime.now());
            existingSeat = seatRepo.save(existingSeat);

            return convertToSeatDTO(existingSeat);
        }

        @Override
        public void deleteSeat(int id) {
            Seat seat = seatRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Seat", "ID", (long) id));
            seatRepo.delete(seat);
        }

        @Override
        public void deleteAllSeats() {
            seatRepo.deleteAll();
        }

        @Override
        public Page<SeatDTO> getAllSeats(int page, int size) {
            PageRequest pageable = PageRequest.of(page, size);
            Page<Seat> seats = seatRepo.findAll(pageable);
            return seats.map(this::convertToSeatDTO);
        }

        // Conversion methods

        private SeatDTO convertToSeatDTO(Seat seat) {
            SeatDTO seatDTO = new SeatDTO();
            seatDTO.setSeatId(seat.getSeatId());
            seatDTO.setSeatNumber(seat.getSeatNumber());
          //  seatDTO.setBusId(seat.getBus() != null ? seat.getBus().getBusId() : 0);
            return seatDTO;
        }

        private Seat convertToSeatEntity(SeatDTO seatDTO) {
            Seat seat = new Seat();
            seat.setSeatId(seatDTO.getSeatId());
            seat.setSeatNumber(seatDTO.getSeatNumber());
            return seat;
        }
    }