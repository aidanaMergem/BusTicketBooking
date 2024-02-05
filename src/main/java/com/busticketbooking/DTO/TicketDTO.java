package com.busticketbooking.DTO;

import lombok.Data;

@Data
public class TicketDTO {
    private int ticketId;
    private double price;
    private PassengerDTO passengerDTO;
}
