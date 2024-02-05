package com.busticketbooking.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class BookingDTO {
    private int bookingId;
    private UserDTO userDTO;
    private List<TicketDTO> ticketDTOs;
    private LocalDateTime bookingDate;

}
