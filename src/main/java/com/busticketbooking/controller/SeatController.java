package com.busticketbooking.controller;

import com.busticketbooking.DTO.SeatDTO;
import com.busticketbooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable int id) {
        SeatDTO seat = seatService.getSeatById(id);
        return ResponseEntity.ok(seat);
    }

    @PostMapping
    public ResponseEntity<SeatDTO> createSeat(@RequestBody SeatDTO seatDTO) {
        SeatDTO createdSeat = seatService.createSeat(seatDTO);
        return ResponseEntity.ok(createdSeat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDTO> updateSeat(@PathVariable int id, @RequestBody SeatDTO updatedSeatDTO) {
        SeatDTO updatedSeat = seatService.updateSeat(id, updatedSeatDTO);
        return ResponseEntity.ok(updatedSeat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeat(@PathVariable int id) {
        seatService.deleteSeat(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<SeatDTO>> getAllSeats(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<SeatDTO> seats = seatService.getAllSeats(page, size);

        return ResponseEntity.ok(seats);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllSeats() {
        seatService.deleteAllSeats();
        return ResponseEntity.ok().build();
    }
}
