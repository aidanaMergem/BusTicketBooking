package com.busticketbooking.service;

import com.busticketbooking.DTO.BookingDTO;
import org.springframework.data.domain.Page;

public interface BookingService {
    BookingDTO getBookingById(int id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(int id, BookingDTO updatedBookingDTO);
    void deleteBooking(int id);
    void deleteAllBookings();
    Page<BookingDTO> getAllBookings(int page, int size);
}
