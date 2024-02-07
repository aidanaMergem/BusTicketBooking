package com.busticketbooking.service.impl;

import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.Bus;
import com.busticketbooking.repository.BusRepository;
import com.busticketbooking.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// BusServiceImpl
@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Bus getBus(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    @Override
    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = getBus(id);
        // update the bus details
        bus.setBusNumber(busDetails.getBusNumber());
        bus.setBusModel(busDetails.getBusModel());
        bus.setBusType(busDetails.getBusType());
        bus.setBusColor(busDetails.getBusColor());
        bus.setTotalSeats(busDetails.getTotalSeats());
        return busRepository.save(bus);
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus = getBus(id);
        busRepository.delete(bus);
    }
}