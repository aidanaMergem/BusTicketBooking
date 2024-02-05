package com.busticketbooking.service.impl;

import com.busticketbooking.DTO.BookingDTO;
import com.busticketbooking.exception.ResourceNotFoundException;
import com.busticketbooking.model.Booking;
import com.busticketbooking.repository.BookingRepository;
import com.busticketbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

//import static com.busticketbooking.service.impl.UserServiceImpl.convertToUser;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingDTO getBookingById(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "ID", (long) id));
        return convertToBookingDTO(booking);
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = convertToBookingEntity(bookingDTO);
        booking = bookingRepository.save(booking);
        return convertToBookingDTO(booking);
    }

    @Override
    public BookingDTO updateBooking(int id, BookingDTO updatedBookingDTO) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "ID", (long) id));

      //  existingBooking.setUser(convertToUser(updatedBookingDTO.getUserId()));
     //  existingBooking.setTickets(convertToTicket(updatedBookingDTO.getTickets()));
        existingBooking.setBookingDate(updatedBookingDTO.getBookingDate());

        existingBooking.setModifiedAt(LocalDateTime.now());
        existingBooking = bookingRepository.save(existingBooking);

        return convertToBookingDTO(existingBooking);
    }

    @Override
    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "ID", (long) id));
        bookingRepository.delete(booking);
    }

    @Override
    public void deleteAllBookings() {
        bookingRepository.deleteAll();
    }

    @Override
    public Page<BookingDTO> getAllBookings(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        return bookings.map(this::convertToBookingDTO);
    }

    // Conversion methods

    private BookingDTO convertToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
      //  bookingDTO.setUserId(booking.getUser() != null ? booking.getUser().getUserId() : 0);
        return bookingDTO;
    }

    private Booking convertToBookingEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());
        // Set User if needed
        return booking;
    }
}
