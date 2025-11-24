package com.example.reservation_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long flightId;
    
    @Column(nullable = false)
    private String passengerName;
    
    @Column(nullable = false)
    private String passengerEmail;
    
    @Column(nullable = false)
    private String passengerPhone;
    
    @Column(nullable = false)
    private Integer numberOfSeats;
    
    @Column(nullable = false)
    private BigDecimal totalPrice;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.PENDING;
    
    @Column(nullable = false)
    private LocalDateTime reservationDate;
    
    // Flight bilgileri (cache amaçlı)
    private String flightNumber;
    private String airline;
    private String departureCity;
    private String arrivalCity;
    private LocalDateTime departureTime;
    
    // Constructor'lar
    public Reservation() {
        this.reservationDate = LocalDateTime.now();
        this.status = ReservationStatus.PENDING;
    }
    
    @PrePersist
    protected void onCreate() {
        if (reservationDate == null) {
            reservationDate = LocalDateTime.now();
        }
        if (status == null) {
            status = ReservationStatus.PENDING;
        }
    }
}