package com.busticketbooking.repository;

import com.busticketbooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

}
