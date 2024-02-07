package com.busticketbooking.model;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String busNumber;

    private String busModel;

    @Enumerated(EnumType.STRING)
    private BusType busType;

    private String busColor;

    @Min(1)
    private int totalSeats;

    public enum BusType {
        AC,
        NON_AC
    }
}
