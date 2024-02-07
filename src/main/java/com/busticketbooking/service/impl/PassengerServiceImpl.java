package com.busticketbooking.service.impl;

import com.busticketbooking.DTO.PassengerDTO;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.Passenger;
import com.busticketbooking.repository.PassengerRepository;
import com.busticketbooking.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public PassengerDTO getPassengerById(int id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger", "ID", id));
        return convertToPassengerDTO(passenger);
    }

    @Override
    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = convertToPassenger(passengerDTO);
        passenger = passengerRepository.save(passenger);
        return convertToPassengerDTO(passenger);
    }

    @Override
    public PassengerDTO updatePassenger(int id, PassengerDTO updatedPassengerDTO) {
        Passenger existingPassenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger", "ID", id));

        existingPassenger.setName(updatedPassengerDTO.getName());
        existingPassenger.setSurname(updatedPassengerDTO.getSurname());
        existingPassenger.setAge(updatedPassengerDTO.getAge());
        existingPassenger.setDiscount(updatedPassengerDTO.getDiscount());

        existingPassenger.setModifiedAt(LocalDateTime.now());

        existingPassenger = passengerRepository.save(existingPassenger);

        return convertToPassengerDTO(existingPassenger);
    }

    @Override
    public void deletePassenger(int id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger", "ID", id));
        passengerRepository.delete(passenger);
    }

    @Override
    public void deleteAllPassengers() {
        passengerRepository.deleteAll();
    }

    @Override
    public Page<PassengerDTO> getAllPassengers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Passenger> passengers = passengerRepository.findAll(pageable);
        return passengers.map(PassengerServiceImpl::convertToPassengerDTO);
    }

    // Conversion methods

    static PassengerDTO convertToPassengerDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setPassengerId(passenger.getPassengerId());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setSurname(passenger.getSurname());
        passengerDTO.setAge(passenger.getAge());
        passengerDTO.setDiscount(passenger.getDiscount());
        return passengerDTO;
    }

     static Passenger convertToPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setPassengerId(passengerDTO.getPassengerId());
        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setAge(passengerDTO.getAge());
        passenger.setDiscount(passengerDTO.getDiscount());
        return passenger;
    }
}
