package com.busticketbooking.DTO;

import com.busticketbooking.model.Ticket;
import com.busticketbooking.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class BookingDTO {
    private int bookingId;
    private User user;
    private List<Ticket> tickets;
    private LocalDateTime bookingDate;

}
