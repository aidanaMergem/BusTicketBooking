package com.busticketbooking.service;
import com.busticketbooking.DTO.TicketDTO;
import org.springframework.data.domain.Page;

public interface TicketService {
    TicketDTO getTicketById(int id);
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO updateTicket(int id, TicketDTO updatedTicketDTO);
    void deleteTicket(int id);
    void deleteAllTickets();
    Page<TicketDTO> getAllTickets(int page, int size);
}
