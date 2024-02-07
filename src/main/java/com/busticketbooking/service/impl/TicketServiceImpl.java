package com.busticketbooking.service.impl;

import com.busticketbooking.DTO.TicketDTO;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.Ticket;
import com.busticketbooking.repository.TicketRepository;
import com.busticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.busticketbooking.service.impl.PassengerServiceImpl.convertToPassenger;
import static com.busticketbooking.service.impl.PassengerServiceImpl.convertToPassengerDTO;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketDTO getTicketById(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "ID", (long) id));
        return convertToTicketDTO(ticket);
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = convertToTicket(ticketDTO);
        ticket = ticketRepository.save(ticket);
        return convertToTicketDTO(ticket);
    }

    @Override
    public TicketDTO updateTicket(int id, TicketDTO updatedTicketDTO) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "ID", (long) id));

        existingTicket.setPrice(updatedTicketDTO.getPrice());
        existingTicket.setPassenger(convertToPassenger(updatedTicketDTO.getPassengerDTO()));
        existingTicket.setModifiedAt(LocalDateTime.now());

        existingTicket = ticketRepository.save(existingTicket);

        return convertToTicketDTO(existingTicket);
    }

    @Override
    public void deleteTicket(int id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "ID", (long) id));
        ticketRepository.delete(ticket);
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    @Override
    public Page<TicketDTO> getAllTickets(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Ticket> tickets = ticketRepository.findAll(pageable);
        return tickets.map(TicketServiceImpl::convertToTicketDTO);
    }

    // Conversion methods

    static TicketDTO convertToTicketDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketId(ticket.getTicketId());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setPassengerDTO(convertToPassengerDTO(ticket.getPassenger()));
        return ticketDTO;
    }

    static Ticket convertToTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.getTicketId());
        ticket.setPrice(ticketDTO.getPrice());
        ticket.setPassenger(convertToPassenger(ticketDTO.getPassengerDTO()));
        return ticket;
    }

}

