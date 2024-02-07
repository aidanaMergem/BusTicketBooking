package com.busticketbooking.service;

import com.busticketbooking.model.Bus;

import java.util.List;

public interface BusService {
    Bus saveBus(Bus bus);
    Bus getBus(Long id);
    List<Bus> getAllBuses();
    Bus updateBus(Long id, Bus busDetails);
    void deleteBus(Long id);
}