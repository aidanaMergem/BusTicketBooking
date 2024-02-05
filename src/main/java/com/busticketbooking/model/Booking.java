package com.busticketbooking.model;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   //one Booking have one User, and one User can have multiple Booking

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets; // One Booking can have multiple Tickets, and one Ticket belongs to one Booking
// orphanRemoval = true to automatically remove orphaned Ticket entities when they are no longer associated with a Booking.

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt;
}
