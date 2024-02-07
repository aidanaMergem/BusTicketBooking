package com.busticketbooking.controller;
import com.busticketbooking.DTO.PassengerDTO;
import com.busticketbooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDTO> getPassengerById(@PathVariable int id) {
        PassengerDTO passenger = passengerService.getPassengerById(id);
        return ResponseEntity.ok(passenger);
    }

    @PostMapping
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO passengerDTO) {
        PassengerDTO createdPassenger = passengerService.createPassenger(passengerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPassenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable int id, @RequestBody PassengerDTO updatedPassengerDTO) {
        PassengerDTO passenger = passengerService.updatePassenger(id, updatedPassengerDTO);
        return ResponseEntity.ok(passenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PassengerDTO>> getAllPassengers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<PassengerDTO> passengers = passengerService.getAllPassengers(page, size);
        return ResponseEntity.ok(passengers);
    }
}
